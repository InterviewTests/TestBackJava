using Microsoft.Extensions.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Repositories;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.DependencyInjection
{
    public static class GetAllCategoryIntegrationTransactionExtensions
    {
        public static IServiceCollection AddGetAllCategoryIntegrationTransactionFeature(this IServiceCollection services)
            => services.AddScoped<IGetAllCategoryIntegrationTransactionRepository, GetAllCategoryIntegrationTransactionRepository>();
    }
}
