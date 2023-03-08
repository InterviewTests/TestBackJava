using MediatR;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System;

namespace Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Models
{
    public class IntegrationTransactionInput : IRequest<bool>
    {
        public string Description { get; set; }
        public decimal Value { get; set; }
        public int CustomerCode { get; set; }
        public DateTime DateTransaction { get; set; }

        public TransactionDto CreateTrasactionDto()
            => new()
            {
                Value = Value,
                CustomerCode = CustomerCode,
                DateTransaction = DateTransaction,
                Description = Description
            };

    }


}
