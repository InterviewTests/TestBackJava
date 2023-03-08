using MediatR;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Models;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.UseCase
{
    public class GetAllCategoryIntegrationTransactionUseCase : IRequestHandler<GetAllCategoryIntegrationTransactionInput, IEnumerable<string>>
    {
        private readonly IGetAllCategoryIntegrationTransactionRepository _GetAllCategoryIntegrationTransactionRepository;
        public GetAllCategoryIntegrationTransactionUseCase(IGetAllCategoryIntegrationTransactionRepository GetAllCategoryIntegrationTransactionRepository) 
        {
            _GetAllCategoryIntegrationTransactionRepository = GetAllCategoryIntegrationTransactionRepository;
        }

        public async Task<IEnumerable<string>> Handle(GetAllCategoryIntegrationTransactionInput request, CancellationToken cancellationToken)
            => await _GetAllCategoryIntegrationTransactionRepository.GetAllCategoryIntegrationTransactionAsync();
           
    }
}
