using Microsoft.Extensions.Options;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Shared.Configuration;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Repositories
{
    public class IntegrationTransactionRepository : IIntegrationTransactionRepository
    {
        private readonly IMongoCollection<TransactionDto> _transactionsCollection;
        public IntegrationTransactionRepository(IOptions<DataBaseConfiguration> dataBaseConfiguration)
        {
            var client = new MongoClient(dataBaseConfiguration.Value.ConnectionStringMongo);
            var database = client.GetDatabase(dataBaseConfiguration.Value.DataBaseMongo);
            _transactionsCollection = database.GetCollection<TransactionDto>(dataBaseConfiguration.Value.CollectionName);
        }

        public async Task InsertTransactionAsync(TransactionDto input) =>  await _transactionsCollection.InsertOneAsync(input);

    }
}
