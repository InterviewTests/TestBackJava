using MassTransit;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;
using Safra.CreditCard.Transaction.Application.Consumer;
using Safra.CreditCard.Transaction.Application.Shared.Configuration;
using Safra.Event;
using System;
using System.Linq;

namespace Safra.CreditCard.Transaction.Integration.DependencyInjection
{
    public static class RabbitMqExtensions
    {
        public static IServiceCollection AddRabbitMq(this IServiceCollection services)
        {
            services.AddMassTransit<IBus>(eventBus =>
            {
                eventBus.AddDelayedMessageScheduler();
                eventBus.AddConsumer<CategoryConsumer>();

                eventBus.UsingRabbitMq((context, config) =>
                {
                    var options = context.GetRequiredService<IOptions<RabbitMQOptions>>();

                    config.Host(
                        options.Value.ConnectionRabbitMQHosts.First(),
                        options.Value.ConnectionRabbitMQPort,
                        options.Value.ConnectionRabbitMQVirtualhost,
                        options.Value.ConnectionRabbitMQVirtualhost,
                        configurator =>
                        {
                            configurator.UseCluster(cluster =>
                            {
                                options.Value.ConnectionRabbitMQHosts.ToList().ForEach(connection =>
                                {
                                    cluster.Node(connection);
                                });
                            });

                            configurator.Username(options.Value.ConnectionRabbitMQUserName);
                            configurator.Password(options.Value.ConnectionRabbitMQPassword);
                        });

                    config.UseDelayedMessageScheduler();

                    config.ReceiveEndpoint("safra.on.transaction.created.category", endpoint =>
                    {
                        endpoint.UseScheduledRedelivery(r => r.Intervals(TimeSpan.FromMinutes(1), TimeSpan.FromMinutes(1), TimeSpan.FromMinutes(1), TimeSpan.FromMinutes(1), TimeSpan.FromMinutes(1)));
                        endpoint.UseRetry(x => x.Interval(5, TimeSpan.FromMilliseconds(100)));
                        endpoint.Bind("safra.on.transaction.created");
                        endpoint.Consumer<CategoryConsumer>(context);
                    });
                });


                eventBus.AddRequestClient<TransactionCreatedEvent>(new Uri("exchange:safra.on.transaction.created"), TimeSpan.FromSeconds(3));
            });

            services.AddMassTransitHostedService();

            return services;

        }
    }
}