using Microsoft.VisualStudio.TestTools.UnitTesting;
using MongoDB.Driver;
using Moq;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.UseCase;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.UseCase;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.UseCase;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Xunit;

namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class GetAllIntegrationTransactionUseCaseTest
    {
        private readonly DefaultFixture _defaultFixture = new();
        private readonly GetAllIntegrationTransactionUseCase _sut;

        public GetAllIntegrationTransactionUseCaseTest()
        {
            _sut = new(
                    _defaultFixture._getAllIntegrationTransactionRepository.Object
                   );
        }

        [Fact]
        public async Task GetAllIntegrationTransactionUseCase_ComSucesso()
        {
            // Arrange
            var input = _defaultFixture.CreateGetAllIntegrationTransactionInput();
            var output = new List<TransactionDto> { _defaultFixture.CreateTransactionDto() };

            _defaultFixture._getAllIntegrationTransactionRepository
                .Setup(x => x.GetAllIntegrationTransactionAsync(It.IsAny<FilterDefinition<TransactionDto>>()))
                .ReturnsAsync(output);

            // Act
            var result = await _sut.Handle(input, default);

            // Assert
            Assert.IsNotNull(result);
            Assert.Equals(output, result);
            Assert.Equals(DateTime.Now.Date, input.TransactionDate);
        }
    }
}
