using MediatR;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System;
using System.Text.Json.Serialization;

namespace Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models
{
    public class InsertCategoryIntegrationTransactionInput : IRequest<bool>
    {
        public string Description { get; set; }
        public string Id { get; set; }

        public FilterDefinition<TransactionDto> Filter() => Builders<TransactionDto>.Filter.Eq("Id", Id);
        public UpdateDefinition<TransactionDto> Update(string category) => Builders<TransactionDto>.Update.Set("Category", category);

    }
}
