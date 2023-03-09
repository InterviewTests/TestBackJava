using Microsoft.Extensions.Options;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Shared.Configuration;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Repositories
{
    public class InsertCategoryIntegrationTransactionRepository : IInsertCategoryIntegrationTransactionRepository
    {
        private readonly IMongoCollection<TransactionDto> _transactionsCollection;
        public InsertCategoryIntegrationTransactionRepository(IOptions<DataBaseConfiguration> dataBaseConfiguration)
        {
            var client = new MongoClient(dataBaseConfiguration.Value.ConnectionStringMongo);
            var database = client.GetDatabase(dataBaseConfiguration.Value.DataBaseMongo);
            _transactionsCollection = database.GetCollection<TransactionDto>(dataBaseConfiguration.Value.CollectionName);
        }

        public async Task<string> GetDescriptionAsync(string description) 
            =>  (await _transactionsCollection.FindAsync(x=> x.Description == description && x.Category != null)).First().Category;

        public async Task UpdateCategoryByIdAsync(InsertCategoryIntegrationTransactionInput input, string category) 
            => await _transactionsCollection.UpdateOneAsync(input.Filter(), input.Update(category));
    }
}
