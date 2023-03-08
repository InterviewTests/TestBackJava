using MediatR;
using Microsoft.Extensions.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.DependencyInjection;
using System.Reflection;

namespace Safra.CreditCard.Transaction.Application.DependencyInjection
{
    public static class ApplicationExtensions
    {

        public static IServiceCollection AddApplication(this IServiceCollection services)
            => services.AddMediatR(cfg => cfg.RegisterServicesFromAssemblies(Assembly.GetExecutingAssembly()));

        public static IServiceCollection AddFeatures(this IServiceCollection services)
        {
            services.AddIntegrationTransactionFeature();
            services.AddGetAllIntegrationTransactionFeature();
            services.AddGetIntegrationTransactionByIdFeature();
            services.AddUpdateCategoryIntegrationTransactionFeature();
            services.AddGetAllCategoryIntegrationTransactionFeature();
            services.AddInsertCategoryIntegrationTransactionFeature();

            return services;
        }
    }
}
