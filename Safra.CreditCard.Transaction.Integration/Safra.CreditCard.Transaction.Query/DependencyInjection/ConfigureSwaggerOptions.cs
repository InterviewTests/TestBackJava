using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;
using Microsoft.OpenApi.Models;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace Safra.CreditCard.Transaction.Query.DependencyInjection
{
    public class ConfigureSwaggerOptions : IConfigureOptions<SwaggerGenOptions>
    {
        public void Configure(SwaggerGenOptions options)
        {
           options.SwaggerDoc("v1",
                     new OpenApiInfo
                     {
                         Title = "Consuulta movimentações de cartão de crédito",
                         Description = "Serviço responsável por consultar movimentações de cartão de crédito",
                     });
        }
    }
}
