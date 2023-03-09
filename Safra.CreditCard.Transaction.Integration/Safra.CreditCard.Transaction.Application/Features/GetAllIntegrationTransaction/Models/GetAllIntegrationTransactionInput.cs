using MediatR;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Models
{
    public class GetAllIntegrationTransactionInput : IRequest<List<TransactionDto>>
    {
        public DateTime? TransactionDate{ get; set; }

        public FilterDefinition<TransactionDto> Filter()
            => !TransactionDate.HasValue ? Builders<TransactionDto>.Filter.Gt("DateTransaction", DateTime.Now.AddSeconds(-5)) : Builders<TransactionDto>.Filter.Gt("DateTransaction", TransactionDate?.Date) & Builders<TransactionDto>.Filter.Lt("DateTransaction", TransactionDate?.AddDays(1).Date);
    }
}
