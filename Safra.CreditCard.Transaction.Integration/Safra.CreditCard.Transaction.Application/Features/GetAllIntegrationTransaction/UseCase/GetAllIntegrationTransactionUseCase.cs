using MediatR;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.UseCase
{
    public class GetAllIntegrationTransactionUseCase : IRequestHandler<GetAllIntegrationTransactionInput, List<TransactionDto>>
    {
        private readonly IGetAllIntegrationTransactionRepository _GetAllIntegrationTransactionRepository;
        public GetAllIntegrationTransactionUseCase(IGetAllIntegrationTransactionRepository GetAllIntegrationTransactionRepository) 
        {
            _GetAllIntegrationTransactionRepository = GetAllIntegrationTransactionRepository;
        }

        public async Task<List<TransactionDto>> Handle(GetAllIntegrationTransactionInput request, CancellationToken cancellationToken)
            => await _GetAllIntegrationTransactionRepository.GetAllIntegrationTransactionAsync(request.Filter());
           
    }
}
