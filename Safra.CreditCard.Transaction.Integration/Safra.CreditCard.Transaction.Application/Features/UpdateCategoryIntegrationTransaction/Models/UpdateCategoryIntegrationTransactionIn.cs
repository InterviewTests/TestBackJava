using MediatR;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Text.Json.Serialization;

namespace Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models
{
    public class UpdateCategoryIntegrationTransactionIn : IRequest<bool>
    {
        public UpdateCategoryIntegrationTransactionIn(string description, string category) 
        {
            Description = description;
            Category = category;
        }

        public string Description { get; set; }
        public string Category { get; set; }

        [JsonIgnore]
        public FilterDefinition<TransactionDto> Filter => Builders<TransactionDto>.Filter.Eq("Description", Description);
        [JsonIgnore]
        public UpdateDefinition<TransactionDto> Update => Builders<TransactionDto>.Update.Set("Category", Category);
    }
}
