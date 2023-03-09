using Microsoft.VisualStudio.TestTools.UnitTesting;
using Safra.CreditCard.Transaction.Application.Features.InsertCategoryIntegrationTransaction.UseCase;
using System.Threading.Tasks;
using Xunit;

namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class InsertCategoryIntegrationTransactionUseCaseTest
    {
        private readonly DefaultFixture _defaultFixture = new();
        private readonly InsertCategoryIntegrationTransactionUseCase _sut;

        public InsertCategoryIntegrationTransactionUseCaseTest()
        {
            _sut = new(
                    _defaultFixture._insertCategoryIntegrationTransactionRepository.Object
                   );
        }

        [Fact]
        public async Task InsertCategoryIntegrationTransactionUseCase_ComSucesso()
        {
            // Arrange
            var input = _defaultFixture.CreateInsertCategoryIntegrationTransactionInput();

            // Act
            var result = await _sut.Handle(input, default);

            // Assert
            Assert.IsNotNull(result);
            Assert.IsTrue(result);

            Assert.Equals("Cartao", input.Description);
        }
    }
}
