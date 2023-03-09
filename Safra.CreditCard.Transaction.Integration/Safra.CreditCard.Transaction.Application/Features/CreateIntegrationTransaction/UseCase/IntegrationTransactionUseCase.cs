using MassTransit;
using MediatR;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Models;
using Safra.Event;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.UseCase
{
    public class IntegrationTransactionUseCase : IRequestHandler<IntegrationTransactionInput, bool>
    {
        private readonly IIntegrationTransactionRepository _integrationTransactionRepository;
        private readonly IBus _bus;

        public IntegrationTransactionUseCase(
            IIntegrationTransactionRepository integrationTransactionRepository,
            IBus bus) 
        {
            _integrationTransactionRepository = integrationTransactionRepository;
            _bus = bus;
        }

        public async Task<bool> Handle(IntegrationTransactionInput request, CancellationToken cancellationToken)
        {
            await _integrationTransactionRepository.InsertTransactionAsync(request.CreateTrasactionDto());
            await _bus.Publish(new TransactionCreatedEvent { Description = request.Description });
            return true;
        }
    }
}
