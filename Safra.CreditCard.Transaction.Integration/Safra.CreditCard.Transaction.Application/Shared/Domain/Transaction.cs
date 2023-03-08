using System;

namespace Safra.CreditCard.Transaction.Application.Shared.Domain
{
    public class TransactionDto
    {
        public string Description { get; set; }
        public decimal Value { get; set; }
        public int CustomerCode { get; set; }
        public DateTime DateTransaction { get; set; }
    }
}
