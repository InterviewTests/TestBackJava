using MediatR;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Models;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.UseCase
{
    public class GetIntegrationTransactionByIdUseCase : IRequestHandler<GetIntegrationTransactionByIdInput, TransactionDto>
    {
        private readonly IGetIntegrationTransactionByIdRepository _GetIntegrationTransactionByIdRepository;
        public GetIntegrationTransactionByIdUseCase(IGetIntegrationTransactionByIdRepository GetIntegrationTransactionByIdRepository) 
        {
            _GetIntegrationTransactionByIdRepository = GetIntegrationTransactionByIdRepository;
        }

        public async Task<TransactionDto> Handle(GetIntegrationTransactionByIdInput request, CancellationToken cancellationToken)
            => await _GetIntegrationTransactionByIdRepository.GetIntegrationTransactionByIdAsync(request.TransactionId);
           
    }
}
