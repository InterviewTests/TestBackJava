using Microsoft.Extensions.Options;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Shared.Configuration;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Repositories
{
    public class GetAllCategoryIntegrationTransactionRepository : IGetAllCategoryIntegrationTransactionRepository
    {
        private readonly IMongoCollection<TransactionDto> _transactionsCollection;
        public GetAllCategoryIntegrationTransactionRepository(IOptions<DataBaseConfiguration> dataBaseConfiguration)
        {
            var client = new MongoClient(dataBaseConfiguration.Value.ConnectionStringMongo);
            var database = client.GetDatabase(dataBaseConfiguration.Value.DataBaseMongo);
            _transactionsCollection = database.GetCollection<TransactionDto>(dataBaseConfiguration.Value.CollectionName);
        }

        public async Task<IEnumerable<string>> GetAllCategoryIntegrationTransactionAsync() => 
            (await _transactionsCollection.Find(x=> x.Category != null).ToListAsync())
            .Select(x=> x.Category)
            .Distinct();

    }
}
