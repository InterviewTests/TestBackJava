using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Interfaces
{
    public interface IIntegrationTransactionRepository
    {
        Task InsertTransactionAsync(TransactionDto input);
    }
}
