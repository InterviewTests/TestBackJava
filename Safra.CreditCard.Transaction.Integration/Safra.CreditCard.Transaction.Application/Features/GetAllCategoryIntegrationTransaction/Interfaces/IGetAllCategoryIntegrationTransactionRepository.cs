using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Interfaces
{
    public interface IGetAllCategoryIntegrationTransactionRepository
    {
        Task<IEnumerable<string>> GetAllCategoryIntegrationTransactionAsync();
    }
}
