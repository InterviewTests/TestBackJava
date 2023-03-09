using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Interfaces
{
    public interface IGetAllIntegrationTransactionRepository
    {
        Task<List<TransactionDto>> GetAllIntegrationTransactionAsync(FilterDefinition<TransactionDto> filter);
    }
}
