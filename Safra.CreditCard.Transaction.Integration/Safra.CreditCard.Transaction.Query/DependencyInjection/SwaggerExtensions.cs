using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;
using Microsoft.OpenApi.Models;
using Swashbuckle.AspNetCore.SwaggerGen;
using Swashbuckle.AspNetCore.SwaggerUI;
using System.Collections.Generic;

namespace Safra.CreditCard.Transaction.Query.DependencyInjection
{
    public static class SwaggerExtensions
    {
        public static IServiceCollection AddSwaggerConfiguration(this IServiceCollection services)
        {
            services.AddTransient<IConfigureOptions<SwaggerGenOptions>, ConfigureSwaggerOptions>();


            services.AddSwaggerGen(
                          options =>
                          {
                              options.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
                              {
                                  Description = @"JWT Authorization header using the Bearer scheme. \r\n\r\n
                                                          Enter 'Bearer' [space] and then your token in the text input below.
                                                          \r\n\r\nExample: 'Bearer 12345abcdef'",
                                  Name = "Authorization",
                                  In = ParameterLocation.Header,
                                  Type = SecuritySchemeType.ApiKey,
                                  Scheme = "Bearer"
                              });

                              options.AddSecurityRequirement(new OpenApiSecurityRequirement()
                              {
                                                {
                                                new OpenApiSecurityScheme
                                                {
                                                    Reference = new OpenApiReference
                                                    {
                                                        Type = ReferenceType.SecurityScheme,
                                                        Id = "Bearer"
                                                    },
                                                    Scheme = "oauth2",
                                                    Name = "Bearer",
                                                    In = ParameterLocation.Header,
                                                    },
                                                    new List<string>()
                                                }
                                  });
                          });

            return services;
        }

        public static IApplicationBuilder UseSwaggerConfiguration(this IApplicationBuilder app)
        {
            app.UseSwagger(c =>
            {
                c.PreSerializeFilters.Add((document, request) =>
                {
                    document.Servers = new List<OpenApiServer>
                    {
                         new OpenApiServer {Url = $"http://localhost:5001"},
                    };
                });
            });

            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("./v1/swagger.json", "v1");
                c.DocExpansion(DocExpansion.List);
            });

            return app;
        }
    }
}
