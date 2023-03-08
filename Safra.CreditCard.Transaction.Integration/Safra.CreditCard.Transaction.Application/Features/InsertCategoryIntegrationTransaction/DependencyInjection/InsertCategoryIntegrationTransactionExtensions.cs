using Microsoft.Extensions.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Repositories;

namespace Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.DependencyInjection
{
    public static class InsertCategoryIntegrationTransactionExtensions
    {
        public static IServiceCollection AddInsertCategoryIntegrationTransactionFeature(this IServiceCollection services)
            => services.AddScoped<IInsertCategoryIntegrationTransactionRepository, InsertCategoryIntegrationTransactionRepository>();
    }
}
