using Microsoft.Extensions.Options;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Interfaces;
using Safra.CreditCard.Transaction.Application.Shared.Configuration;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Repositories
{
    public class GetIntegrationTransactionByIdRepository : IGetIntegrationTransactionByIdRepository
    {
        private readonly IMongoCollection<TransactionDto> _transactionsCollection;
        public GetIntegrationTransactionByIdRepository(IOptions<DataBaseConfiguration> dataBaseConfiguration)
        {
            var client = new MongoClient(dataBaseConfiguration.Value.ConnectionStringMongo);
            var database = client.GetDatabase(dataBaseConfiguration.Value.DataBaseMongo);
            _transactionsCollection = database.GetCollection<TransactionDto>(dataBaseConfiguration.Value.CollectionName);
        }

        public async Task<TransactionDto> GetIntegrationTransactionByIdAsync(string transactionId) => (await _transactionsCollection.FindAsync(t => t.Id == transactionId)).Single();

    }
}
