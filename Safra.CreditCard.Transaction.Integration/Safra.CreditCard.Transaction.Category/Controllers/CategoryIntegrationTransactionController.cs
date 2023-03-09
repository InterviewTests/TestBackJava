using MediatR;
using Microsoft.AspNetCore.Mvc;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Integration.Controllers
{
    [ApiController]
    [Route("transaction-category")]
    public class CategoryIntegrationTransactionController : ControllerBase
    {
        private readonly IMediator _mediator;

        public CategoryIntegrationTransactionController(
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
    }
}