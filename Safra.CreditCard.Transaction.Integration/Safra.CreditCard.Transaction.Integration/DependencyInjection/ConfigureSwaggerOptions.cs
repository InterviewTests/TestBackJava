using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;
using Microsoft.OpenApi.Models;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace Safra.CreditCard.Transaction.Integration.DependencyInjection
{
    public class ConfigureSwaggerOptions : IConfigureOptions<SwaggerGenOptions>
    {
        public void Configure(SwaggerGenOptions options)
        {
           options.SwaggerDoc("v1",
                     new OpenApiInfo
                     {
                         Title = "Integrações de movimentações de cartão de crédito",
                         Description = "Serviço responsável por integrar movimentações de cartão de crédito",
                     });
        }
    }
}
