using Microsoft.Extensions.Options;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Shared.Configuration;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Repositories
{
    public class GetAllIntegrationTransactionRepository : IGetAllIntegrationTransactionRepository
    {
        private readonly IMongoCollection<TransactionDto> _transactionsCollection;
        public GetAllIntegrationTransactionRepository(IOptions<DataBaseConfiguration> dataBaseConfiguration)
        {
            var client = new MongoClient(dataBaseConfiguration.Value.ConnectionStringMongo);
            var database = client.GetDatabase(dataBaseConfiguration.Value.DataBaseMongo);
            _transactionsCollection = database.GetCollection<TransactionDto>(dataBaseConfiguration.Value.CollectionName);
        }

        public async Task<List<TransactionDto>> GetAllIntegrationTransactionAsync(FilterDefinition<TransactionDto> filter) => await _transactionsCollection.Find(filter).ToListAsync();

    }
}
