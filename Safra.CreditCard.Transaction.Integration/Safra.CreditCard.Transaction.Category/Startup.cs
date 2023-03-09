using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Safra.CreditCard.Transaction.Application.DependencyInjection;
using Safra.CreditCard.Transaction.Application.Shared.Configuration;
using Safra.CreditCard.Transaction.Integration.DependencyInjection;

namespace Safra.CreditCard.Transaction.Integration
{
    public class Startup
    {
        public IConfiguration Configuration { get; }

        public Startup(IConfiguration configuration) => Configuration = configuration;

         public void ConfigureServices(IServiceCollection services)
        {
            services.Configure<RabbitMQOptions>(Configuration.GetSection("RabbitMq"));
            services.Configure<DataBaseConfiguration>(Configuration.GetSection("ConnectionString"));
            services.AddControllers();
            services.AddEndpointsApiExplorer();
            services.AddSwaggerConfiguration();
            services.AddLogging();
            services.AddApplication();
            services.AddFeatures();
            services.AddRabbitMq();
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseRouting();
            app.UseHttpsRedirection();
            app.UseResponseCaching();
            app.UseSwaggerConfiguration();
            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
