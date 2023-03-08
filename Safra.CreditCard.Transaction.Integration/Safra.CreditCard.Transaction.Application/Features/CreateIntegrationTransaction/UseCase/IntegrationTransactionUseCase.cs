using MediatR;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Models;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.UseCase
{
    public class IntegrationTransactionUseCase : IRequestHandler<IntegrationTransactionInput, bool>
    {
        private readonly IIntegrationTransactionRepository _integrationTransactionRepository;
        public IntegrationTransactionUseCase(IIntegrationTransactionRepository integrationTransactionRepository) 
        {
            _integrationTransactionRepository = integrationTransactionRepository;
        }

        public async Task<bool> Handle(IntegrationTransactionInput request, CancellationToken cancellationToken)
        {
            await _integrationTransactionRepository.InsertTransactionAsync(request.CreateTrasactionDto());
            return true;
        }
    }
}
