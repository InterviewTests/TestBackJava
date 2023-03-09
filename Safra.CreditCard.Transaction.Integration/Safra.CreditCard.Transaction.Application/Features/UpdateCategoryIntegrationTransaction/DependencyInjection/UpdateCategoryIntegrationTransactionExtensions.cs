using Microsoft.Extensions.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Repositories;

namespace Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.DependencyInjection
{
    public static class UpdateCategoryIntegrationTransactionExtensions
    {
        public static IServiceCollection AddUpdateCategoryIntegrationTransactionFeature(this IServiceCollection services)
            => services.AddScoped<IUpdateCategoryIntegrationTransactionRepository, UpdateCategoryIntegrationTransactionRepository>();
    }
}
