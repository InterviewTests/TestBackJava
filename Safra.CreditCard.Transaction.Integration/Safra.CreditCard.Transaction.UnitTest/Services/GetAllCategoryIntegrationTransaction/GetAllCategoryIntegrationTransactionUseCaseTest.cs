using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.UseCase;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.UseCase;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Xunit;

namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class GetAllCategoryIntegrationTransactionUseCaseTest
    {
        private readonly DefaultFixture _defaultFixture = new();
        private readonly GetAllCategoryIntegrationTransactionUseCase _sut;

        public GetAllCategoryIntegrationTransactionUseCaseTest()
        {
            _sut = new(
                    _defaultFixture._getAllCategoryIntegrationTransactionRepository.Object
                   );
        }

        [Fact]
        public async Task GetAllCategoryIntegrationTransactionUseCase_ComSucesso()
        {
            // Arrange
            var input = _defaultFixture.CreateGetAllCategoryIntegrationTransactionInput();
            var output = new List<string> { "Cartão de crédito", "Conta de Luz", "Conta de Agua" };

            _defaultFixture._getAllCategoryIntegrationTransactionRepository
                .Setup(x => x.GetAllCategoryIntegrationTransactionAsync())
                .ReturnsAsync(output);

            // Act
            var result = await _sut.Handle(input, default);

            // Assert
            Assert.IsNotNull(result);
            Assert.Equals(output, result);
        }
    }
}
