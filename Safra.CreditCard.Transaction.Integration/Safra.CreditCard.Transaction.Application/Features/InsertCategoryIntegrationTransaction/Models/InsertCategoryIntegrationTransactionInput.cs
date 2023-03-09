using MediatR;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Shared.Domain;

namespace Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models
{
    public class InsertCategoryIntegrationTransactionInput : IRequest<bool>
    {
        public string Description { get; set; }

        public FilterDefinition<TransactionDto> Filter() => Builders<TransactionDto>.Filter.Eq("Description", Description);
        public UpdateDefinition<TransactionDto> Update(string category) => Builders<TransactionDto>.Update.Set("Category", category);

    }
}
