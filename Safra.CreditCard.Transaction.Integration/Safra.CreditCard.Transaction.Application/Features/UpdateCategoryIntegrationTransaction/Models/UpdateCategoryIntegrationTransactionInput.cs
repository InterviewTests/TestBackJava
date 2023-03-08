using MediatR;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Text.Json.Serialization;

namespace Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models
{
    public class UpdateCategoryIntegrationTransactionInput : IRequest<bool>
    {
        public string Description { get; set; }
        public string Category { get; set; }

        public UpdateCategoryIntegrationTransactionIn CreateUpdateCategoryIntegrationTransactionIn() => new(Description, Category);
    }
}
