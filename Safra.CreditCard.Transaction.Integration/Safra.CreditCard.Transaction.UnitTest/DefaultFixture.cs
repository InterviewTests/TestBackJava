using AutoFixture;
using DocumentFormat.OpenXml.Drawing.Diagrams;
using MassTransit;
using MediatR;
using Moq;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Repositories;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Models;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Interfaces;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System;
using System.Collections.Generic;

namespace Safra.CreditCard.Transaction.UnitTest
{
    public class DefaultFixture
    {
        public Fixture Fixture = new();

        public readonly Mock<IMediator> MediatorMock;
        public readonly Mock<IUpdateCategoryIntegrationTransactionRepository> _updateCategoryIntegrationTransactionRepository = new();
        public readonly Mock<IInsertCategoryIntegrationTransactionRepository> _insertCategoryIntegrationTransactionRepository = new();
        public readonly Mock<IIntegrationTransactionRepository> _integrationTransactionRepository = new();
        public readonly Mock<IGetAllCategoryIntegrationTransactionRepository> _getAllCategoryIntegrationTransactionRepository = new();
        public readonly Mock<IGetAllIntegrationTransactionRepository> _getAllIntegrationTransactionRepository = new();
        public readonly Mock<IGetIntegrationTransactionByIdRepository> _getIntegrationTransactionByIdRepository = new();
        public readonly Mock<IBus> _bus = new();

        public DateTime DateNow = DateTime.Now.Date;

        public IntegrationTransactionInput CreateIntegrationTransactionInput()
        => new()
        {
            CustomerCode = 10,
            DateTransaction = DateNow,
            Description = "Cartao de Credito",
            Value = 100
        };       
        public TransactionDto CreateTransactionDto()
        => new()
        {
            CustomerCode = 10,
            DateTransaction = DateNow,
            Description = "Cartao de Credito",
            Value = 100
        };

        public GetAllCategoryIntegrationTransactionInput CreateGetAllCategoryIntegrationTransactionInput()
        => new();       
        
        public GetAllIntegrationTransactionInput CreateGetAllIntegrationTransactionInput()
        => new()
        {
            TransactionDate =DateNow,
        };
        public GetIntegrationTransactionByIdInput CreateGetIntegrationTransactionByIdInput()
        => new("654321");        
        
        public InsertCategoryIntegrationTransactionInput CreateInsertCategoryIntegrationTransactionInput()
        => new()
        {
            Description = "Cartao"
        };  
        
        public UpdateCategoryIntegrationTransactionInput CreateUpdateCategoryIntegrationTransactionInput()
        => new()
        {
            Description = "Cartao",
            Category = "Cartao"            
        };
    }
}