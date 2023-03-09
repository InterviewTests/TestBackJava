using MediatR;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.UseCase
{
    public class UpdateCategoryIntegrationTransactionUseCase : IRequestHandler<UpdateCategoryIntegrationTransactionInput, bool>
    {
        private readonly IUpdateCategoryIntegrationTransactionRepository _UpdateCategoryIntegrationTransactionRepository;
        public UpdateCategoryIntegrationTransactionUseCase(IUpdateCategoryIntegrationTransactionRepository UpdateCategoryIntegrationTransactionRepository) 
        {
            _UpdateCategoryIntegrationTransactionRepository = UpdateCategoryIntegrationTransactionRepository;
        }

        public async Task<bool> Handle(UpdateCategoryIntegrationTransactionInput request, CancellationToken cancellationToken)
        {
            await _UpdateCategoryIntegrationTransactionRepository.UpdateCategoryIntegrationTransactionInput(request.CreateUpdateCategoryIntegrationTransactionIn());
            return true;
        }
    }
}
