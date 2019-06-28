package io.santander.gastos.service;

import io.santander.gastos.domain.Spent;
import io.santander.gastos.dto.CardSpentDTO;
import io.santander.gastos.dto.CreditCardDTO;
import io.santander.gastos.dto.SpentDTO;
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
import java.text.ParseException;
import java.util.List;

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
    private final DateUTCParser utcParser;

    public PageImpl<SpentDTO> buscaTodosOsGastoPorCliente(final Long userCode, String cardNumber, final GastoVO vo, final Pageable pageable) {
        Page<Spent> spentPage = null;
//        spentPage = spentRepository.findAllWithFilters(
//                userCode,
//                cardNumber,
//                Optional.ofNullable(vo.getDescricao()).orElse(null),
//                Optional.ofNullable(vo.getValor()).orElse(null),
//                Optional.ofNullable(vo.getData()).orElse(null),
//                pageable);
//        return new PageImpl<>(this.spentMapper.toDTOList(spentPage.getContent()), pageable, spentPage.getTotalElements());
        return null;
    }

    public List<SpentDTO> getAllspents() {
        return spentMapper.toDTOList(spentRepository.findAll());
    }

    @Transactional
    public String saveSpent(String numeroCartão, GastoVO vo) {

        if (clientService.verifyUser(vo.getCodigoUsuario())) {
            CreditCardDTO cardDTO = cardService.findCard(numeroCartão);
            if (cardDTO == null) {
                //TODO corrigir exeption
                throw new RuntimeException("Cartão inexistente");
            } else {
                if (clientCardService.verifiCardWoner(vo.getCodigoUsuario(), cardDTO.getId())) {
                    try {
                        cardSpentService.save(CardSpentDTO
                                .builder()
                                .creditCard(cardDTO)
                                .spent(spentMapper.toDTO(spentRepository.save(Spent
                                        .builder()
                                        .description(vo.getDescricao())
                                        .spentDate(utcParser.toDate(vo.getData()))
                                        .spentValue(vo.getValor())
                                        .build())))
                                .build());
                    } catch (ParseException e) {
                        //TODO corrigir exeption
                        e.printStackTrace();
                    }
                    log.info("gasto registrado");
                    return "gasto registrado";
                } else {
                    //TODO corrigir exeption
                    throw new RuntimeException("Este cartão não pertence ou esta autorizado para o usuário");
                }
            }

        } else {
            //TODO corrigir exeption
            throw new RuntimeException("Usuario inexistente");

        }

    }
}
