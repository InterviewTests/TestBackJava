using Microsoft.VisualStudio.TestTools.UnitTesting;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.UseCase;
using System;
using System.Threading.Tasks;
using Xunit;

namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class IntegrationTransactionUseCaseTest
    {
        private readonly DefaultFixture _defaultFixture = new();
        private readonly IntegrationTransactionUseCase _sut;

        public IntegrationTransactionUseCaseTest()
        {
            _sut = new(
                    _defaultFixture._integrationTransactionRepository.Object,
                    _defaultFixture._bus.Object
                   );
        }

        [Fact]
        public async Task IntegrationTransactionUseCase_ComSucesso()
        {
            // Arrange
            var input = _defaultFixture.CreateIntegrationTransactionInput();

            // Act
            var result = await _sut.Handle(input, default);

            // Assert
            Assert.IsNotNull(result);
            Assert.IsTrue(result);

            Assert.Equals("Cartao de Credito", input.Description);
            Assert.Equals(10, input.CustomerCode);
            Assert.Equals(DateTime.Now.Date, input.DateTransaction);
            Assert.Equals(100, input.Value);

            Assert.IsNull(input.CreateTrasactionDto().Category);
            Assert.Equals(10, input.CreateTrasactionDto().CustomerCode);
            Assert.Equals(DateTime.Now.Date, input.CreateTrasactionDto().DateInsert.Date);
            Assert.IsNull(input.CreateTrasactionDto().Id);
            Assert.Equals(100, input.CreateTrasactionDto().Value);
            Assert.Equals("Cartao de Credito", input.CreateTrasactionDto().Description);
            Assert.Equals(DateTime.Now.Date, input.CreateTrasactionDto().DateTransaction);
        }
    }
}
