using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
using Safra.CreditCard.Transaction.Integration.DependencyInjection;
using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;

namespace Safra.CreditCard.Transaction.Integration
{
    public class Startup
    {
      //  public IConfiguration Configuration { get; }
        //public Startup(IConfiguration configuration) => Configuration = configuration;


        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();
            services.AddEndpointsApiExplorer();
            services.AddSwaggerConfiguration();
            services.AddLogging();
            //services.AddOptions();
           // services.AddVersioning();
            //services.AddCustomConfiguration(Configuration);
            //services.AddApplication(Configuration);
            //services.AddFeatures(Configuration);
            //services.AddHttpClient(Configuration);
            //services.AddApiHealthChecks(Configuration);
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
