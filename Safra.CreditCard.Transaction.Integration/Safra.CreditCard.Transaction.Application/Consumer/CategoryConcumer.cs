using MassTransit;
using MediatR;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models;
using Safra.Event;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Consumer
{
    public class CategoryConsumer : IConsumer<TransactionCreatedEvent>
    {
        private readonly IMediator _mediator;

        public CategoryConsumer(
             IMediator mediator)
        {
            _mediator = mediator;
        }

        public async Task Consume(ConsumeContext<TransactionCreatedEvent> context)
        {
            await _mediator.Send(
                new InsertCategoryIntegrationTransactionInput
                {
                    Description = context.Message.Description
                }, context.CancellationToken);
        }
    }
}
