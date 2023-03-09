using MediatR;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Collections.Generic;

namespace Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Models
{
    public class GetIntegrationTransactionByIdInput : IRequest<TransactionDto>
    {
        public GetIntegrationTransactionByIdInput(string transactionId)
        {
            TransactionId = transactionId;
        }

        public string TransactionId { get; set; }
    }
}
