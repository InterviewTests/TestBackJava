using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Interfaces
{
    public interface IUpdateCategoryIntegrationTransactionRepository
    {
        Task UpdateCategoryIntegrationTransactionInput(UpdateCategoryIntegrationTransactionIn input);
    }
}
