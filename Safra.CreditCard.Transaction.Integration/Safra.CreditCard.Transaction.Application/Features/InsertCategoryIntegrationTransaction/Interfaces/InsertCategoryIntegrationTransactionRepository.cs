using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Interfaces
{
    public interface IInsertCategoryIntegrationTransactionRepository
    {
        Task<string> GetDescriptionAsync(string description);
        Task UpdateCategoryByIdAsync(InsertCategoryIntegrationTransactionInput input, string category);
    }
}
