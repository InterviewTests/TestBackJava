using Microsoft.Extensions.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Repositories;

namespace Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.DependencyInjection
{
    public static class GetIntegrationTransactionByIdExtensions
    {
        public static IServiceCollection AddGetIntegrationTransactionByIdFeature(this IServiceCollection services)
            => services.AddScoped<IGetIntegrationTransactionByIdRepository, GetIntegrationTransactionByIdRepository>();
    }
}
