using MediatR;
using Microsoft.Extensions.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.DependencyInjection;
using System.Reflection;

namespace Safra.CreditCard.Transaction.Application.DependencyInjection
{
    public static class ApplicationExtensions
    {

        public static IServiceCollection AddApplication(this IServiceCollection services)
            => services.AddMediatR(cfg => cfg.RegisterServicesFromAssemblies(Assembly.GetExecutingAssembly()));

        public static IServiceCollection AddFeatures(this IServiceCollection services)
            => services.AddIntegrationTransactionFeature();
    }
}
