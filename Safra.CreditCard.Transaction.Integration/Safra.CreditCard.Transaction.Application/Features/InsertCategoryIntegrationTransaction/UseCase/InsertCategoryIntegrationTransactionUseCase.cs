using MediatR;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.UseCase
{
    public class InsertCategoryIntegrationTransactionUseCase : IRequestHandler<InsertCategoryIntegrationTransactionInput, bool>
    {
        private readonly IInsertCategoryIntegrationTransactionRepository _InsertCategoryIntegrationTransactionRepository;
        public InsertCategoryIntegrationTransactionUseCase(IInsertCategoryIntegrationTransactionRepository InsertCategoryIntegrationTransactionRepository) 
        {
            _InsertCategoryIntegrationTransactionRepository = InsertCategoryIntegrationTransactionRepository;
        }

        public async Task<bool> Handle(InsertCategoryIntegrationTransactionInput request, CancellationToken cancellationToken)
        {
            var category = await _InsertCategoryIntegrationTransactionRepository.GetDescriptionAsync(request.Description);

            if(!string.IsNullOrWhiteSpace(category))
            {
                await _InsertCategoryIntegrationTransactionRepository.UpdateCategoryByIdAsync(request, category);
            }
            return true;
        }
    }
}
