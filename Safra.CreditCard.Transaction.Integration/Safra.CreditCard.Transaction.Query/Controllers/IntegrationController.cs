using Microsoft.AspNetCore.Mvc;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Models;
using System.Threading;
using System.Threading.Tasks;
using MediatR;

namespace Safra.CreditCard.Transaction.Query.Controllers
{
    [ApiController]
    [Route("transaction")]
    public class IntegrationTransactionController : ControllerBase
    {
        private readonly IMediator _mediator;
        
        public IntegrationTransactionController(
            IMediator mediator)
        {
            _mediator = mediator;
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
    }
}