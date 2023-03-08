using Microsoft.Extensions.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Repositories;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.DependencyInjection
{
    public static class GetAllIntegrationTransactionExtensions
    {
        public static IServiceCollection AddGetAllIntegrationTransactionFeature(this IServiceCollection services)
            => services.AddScoped<IGetAllIntegrationTransactionRepository, GetAllIntegrationTransactionRepository>();
    }
}
