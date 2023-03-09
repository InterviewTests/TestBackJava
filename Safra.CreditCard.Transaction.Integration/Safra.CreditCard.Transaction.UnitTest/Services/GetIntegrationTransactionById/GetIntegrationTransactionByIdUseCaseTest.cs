using Microsoft.VisualStudio.TestTools.UnitTesting;
using MongoDB.Driver;
using Moq;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.UseCase;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.UseCase;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.UseCase;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.UseCase;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Xunit;

namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class GetIntegrationTransactionByIdUseCaseTest
    {
        private readonly DefaultFixture _defaultFixture = new();
        private readonly GetIntegrationTransactionByIdUseCase _sut;

        public GetIntegrationTransactionByIdUseCaseTest()
        {
            _sut = new(
                    _defaultFixture._getIntegrationTransactionByIdRepository.Object
                   );
        }

        [Fact]
        public async Task GetIntegrationTransactionByIdUseCase_ComSucesso()
        {
            // Arrange
            var input = _defaultFixture.CreateGetIntegrationTransactionByIdInput();
            var output = _defaultFixture.CreateTransactionDto() ;

            _defaultFixture._getIntegrationTransactionByIdRepository
                .Setup(x => x.GetIntegrationTransactionByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(output);

            // Act
            var result = await _sut.Handle(input, default);

            // Assert
            Assert.IsNotNull(result);
            Assert.Equals(output, result);
            Assert.Equals("654321", input.TransactionId);
        }
    }
}
