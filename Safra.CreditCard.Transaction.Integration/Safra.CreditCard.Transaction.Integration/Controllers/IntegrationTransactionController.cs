using MediatR;
using Microsoft.AspNetCore.Mvc;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Models;
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
    }
}