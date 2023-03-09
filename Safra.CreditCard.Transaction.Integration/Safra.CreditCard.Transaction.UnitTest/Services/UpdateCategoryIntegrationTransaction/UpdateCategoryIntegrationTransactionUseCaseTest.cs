using Microsoft.VisualStudio.TestTools.UnitTesting;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.UseCase;
using System.Threading.Tasks;
using Xunit;

namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class UpdateCategoryIntegrationTransactionUseCaseTest
    {
        private readonly DefaultFixture _defaultFixture = new();
        private readonly UpdateCategoryIntegrationTransactionUseCase _sut;

        public UpdateCategoryIntegrationTransactionUseCaseTest()
        {
            _sut = new(
                    _defaultFixture._updateCategoryIntegrationTransactionRepository.Object
                   );
        }

        [Fact]
        public async Task UpdateCategoryIntegrationTransactionUseCase_ComSucesso()
        {
            // Arrange
            var input = _defaultFixture.CreateUpdateCategoryIntegrationTransactionInput();

            // Act
            var result = await _sut.Handle(input, default);

            // Assert
            Assert.IsNotNull(result);
            Assert.IsTrue(result);

            Assert.Equals("Cartao", input.Description);
            Assert.Equals("Cartao", input.Category);
        }
    }
}
