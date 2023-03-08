using MediatR;
using Microsoft.AspNetCore.Mvc;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Models;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models;
using System.Threading;
using System.Threading.Tasks;

namespace Safra.CreditCard.Transaction.Integration.Controllers
{
    [ApiController]
    [Route("integration-transaction")]
    public class IntegrationTransactionController : ControllerBase
    {
        private readonly IMediator _mediator;

        public IntegrationTransactionController(
            IMediator mediator)
        {
            _mediator = mediator;
        }

        [HttpPost()]
        public async Task<IActionResult> InsertTransactionAsync(
            [FromBody] IntegrationTransactionInput command,
            CancellationToken cancellationToken)
        {
            await _mediator.Send(command, cancellationToken);

            return Ok();
        }

        [HttpGet()]
        public async Task<IActionResult> GetAllTransactionAsync(
            [FromQuery] GetAllIntegrationTransactionInput command,
            CancellationToken cancellationToken)
        {
            var result = await _mediator.Send(command, cancellationToken);

            return new OkObjectResult(result);
        }

        [HttpGet("{transactionId}")]
        public async Task<IActionResult> GetTransactionByIdAsync(
            [FromRoute] string transactionId,
            CancellationToken cancellationToken)
        {
            var result =  await _mediator.Send(new GetIntegrationTransactionByIdInput(transactionId), cancellationToken);

            return new OkObjectResult(result);
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