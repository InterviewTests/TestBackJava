using MediatR;
using MongoDB.Driver;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Models
{
    public class GetAllCategoryIntegrationTransactionInput : IRequest<IEnumerable<string>>
    {
    }
}
