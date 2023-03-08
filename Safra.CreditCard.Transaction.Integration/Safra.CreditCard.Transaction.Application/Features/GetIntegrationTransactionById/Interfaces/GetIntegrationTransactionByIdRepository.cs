using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Interfaces
{
    public interface IGetIntegrationTransactionByIdRepository
    {
        Task<TransactionDto> GetIntegrationTransactionByIdAsync(string transactionId);
    }
}
