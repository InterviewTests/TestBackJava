using DocumentFormat.OpenXml.Office2010.Excel;
using Microsoft.Extensions.Options;
using MongoDB.Bson;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Shared.Configuration;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Repositories
{
    public class UpdateCategoryIntegrationTransactionRepository : IUpdateCategoryIntegrationTransactionRepository
    {
        private readonly IMongoCollection<TransactionDto> _transactionsCollection;
        public UpdateCategoryIntegrationTransactionRepository(IOptions<DataBaseConfiguration> dataBaseConfiguration)
        {
            var client = new MongoClient(dataBaseConfiguration.Value.ConnectionStringMongo);
            var database = client.GetDatabase(dataBaseConfiguration.Value.DataBaseMongo);
            _transactionsCollection = database.GetCollection<TransactionDto>(dataBaseConfiguration.Value.CollectionName);
        }

        public async Task UpdateCategoryIntegrationTransactionInput(UpdateCategoryIntegrationTransactionIn input) 
            => await _transactionsCollection.UpdateManyAsync(input.Filter, input.Update);
    }
}
