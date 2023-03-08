using Microsoft.Extensions.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Repositories;

namespace Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.DependencyInjection
{
    public static class IntegrationTransactionExtensions
    {
        public static IServiceCollection AddIntegrationTransactionFeature(this IServiceCollection services)
            => services.AddScoped<IIntegrationTransactionRepository, IntegrationTransactionRepository>();
    }
}
