using Microsoft.AspNetCore.Mvc;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models;
using System.Threading;
using System.Threading.Tasks;
using MediatR;

namespace Safra.CreditCard.Transaction.Integration.Controllers
{
    [ApiController]
    [Route("transaction-category")]
    public class IntegrationTransactionController : ControllerBase
    {
        private readonly IMediator _mediator;

        public IntegrationTransactionController(
            IMediator mediator)
        {
            _mediator = mediator;
        }

       [HttpPatch("category")]
        public async Task<IActionResult> UpdateCategoryTransactionAsync(
            [FromBody] UpdateCategoryIntegrationTransactionInput command,
            CancellationToken cancellationToken)
        {
            var result = await _mediator.Send(command, cancellationToken);

            return new OkObjectResult(result);
        }

        [HttpGet("category")]
        public async Task<IActionResult> GetAllCategoryAsync(
            CancellationToken cancellationToken)
        {
            var result = await _mediator.Send(new GetAllCategoryIntegrationTransactionInput(), cancellationToken);

            return new OkObjectResult(result);
        }


        [HttpPatch("event-category")]
        public async Task<IActionResult> UpcateCategoryAsync(
            [FromBody] InsertCategoryIntegrationTransactionInput command,
            CancellationToken cancellationToken)
        {
            var result = await _mediator.Send(command, cancellationToken);

            return new OkObjectResult(result);
        }
    }
}