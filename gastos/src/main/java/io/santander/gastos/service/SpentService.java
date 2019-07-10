package io.santander.gastos.service;

import io.santander.gastos.domain.Spent;
import io.santander.gastos.dto.CardSpentDTO;
import io.santander.gastos.dto.ClassificationDTO;
import io.santander.gastos.dto.CreditCardDTO;
import io.santander.gastos.dto.SpentDTO;
import io.santander.gastos.exceptions.InvalidHolderException;
import io.santander.gastos.exceptions.MissingCardException;
import io.santander.gastos.exceptions.NonexistentCardException;
import io.santander.gastos.mapper.ClassificationMapper;
import io.santander.gastos.mapper.SpentMapper;
import io.santander.gastos.repository.SpentRepository;
import io.santander.gastos.vo.GastoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SpentService {

    private final SpentRepository spentRepository;
    private final SpentMapper spentMapper;
    private final CardService cardService;
    private final CardSpentService cardSpentService;
    private final ClientService clientService;
    private final ClientCardService clientCardService;
    private final ClassificationService classificationService;
    private final DateUTCParser utcParser;

    private final ClassificationMapper classificationMapper;


    private List<Long> getCarHolder(final Long userCode, final String cardNumber) {
        List<Long> cards = clientCardService.getClientsCard(userCode, cardNumber);
        if (cards.isEmpty()) {
            throw new MissingCardException(userCode.toString());
        } else
            return cards;
    }

    @Transactional
    public PageImpl<SpentDTO> buscaTodosOsGastoPorCliente(final Long userCode, String cardNumber, final GastoVO vo, final Pageable pageable) {
        Page<Spent> spentPage;

        List<Long> cards = getCarHolder(userCode, cardNumber);
        spentPage = spentRepository.findAllWithFilters(
                cards,
                Optional.ofNullable(vo.getDescricao()).orElse(null),
                Optional.ofNullable(vo.getValor()).orElse(null),
                Optional.ofNullable(utcParser.toDate(vo.getData())).orElse(null),
                pageable);
        return new PageImpl<>(this.spentMapper.toDTOList(spentPage.getContent()), pageable, spentPage.getTotalElements());
    }

    @Transactional
    public ClassificationDTO getSpentClassification(final Long userCode, final String decription) {
        List<Long> cards = clientCardService.getCardsByClient(userCode);
        return classificationService.getClassification(cards, decription);
    }

    @Transactional
    public String saveSpent(String numeroCartao, GastoVO vo) {

        if (clientService.verifyUser(vo.getCodigoUsuario())) {
            CreditCardDTO cardDTO = cardService.findCard(numeroCartao);
            if (cardDTO == null) {
                throw new NonexistentCardException(numeroCartao);
            } else {
                if (clientCardService.verifyCardHolder(vo.getCodigoUsuario(), cardDTO.getId())) {
                    cardSpentService.save(CardSpentDTO
                            .builder()
                            .creditCard(cardDTO)
                            .spent(spentMapper.toDTO(spentRepository.save(Spent
                                    .builder()
                                    .description(vo.getDescricao())
                                    .spentDate(utcParser.toDate(vo.getData()))
                                    .spentValue(vo.getValor())
                                    .classification(Optional.ofNullable(classificationMapper.toEntity(getSpentClassification(vo.getCodigoUsuario(), vo.getDescricao()))).orElse(null))
                                    .build())))
                            .build());
                    log.info("gasto registrado");
                    return "gasto registrado";
                } else {
                    throw new InvalidHolderException(vo.getCodigoUsuario().toString());
                }
            }

        } else {
            //TODO corrigir exeption
            throw new RuntimeException("Usuario inexistente");
        }

    }

    @Transactional
    public void updateSpendDatail(final Long userCode, final Long spentId, String cardNumber, final String description, final Long classificationId) {

        SpentDTO spentDTO = getSpentDetail(userCode, spentId, cardNumber);

        Optional.ofNullable(description).ifPresent(s -> spentDTO.setDescription(s));
        Optional.of(classificationId).ifPresent(aLong -> spentDTO.setClassification(classificationService.getById(aLong)));

        spentRepository.save(spentMapper.toEntity(spentDTO));

    }

    @Transactional
    public SpentDTO getSpentDetail(Long userCode, Long codigoGasto, String cardNumber) {
        List<Long> cards = getCarHolder(userCode, cardNumber);
        //TODO: corrigir retorno
        return spentMapper.toDTO(spentRepository.findById(codigoGasto).get());
    }
}
