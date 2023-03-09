using AutoFixture;
using Microsoft.AspNetCore.Mvc.Infrastructure;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Safra.CreditCard.Transaction.Application.Features.GetAllCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.UpdateCategoryIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Integration.Controllers;
using System.Collections.Generic;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using Xunit;

namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class CategoryIntegrationTransactionControllerTest
    {
        private readonly DefaultFixture _fixture = new();
        private readonly CategoryIntegrationTransactionController _sut;

        public CategoryIntegrationTransactionControllerTest()
        {
            _sut = new(_fixture.MediatorMock.Object);
        }


        [Fact]
        public async Task UpdateCategoryTransactionAsync_ExecutadoComSucesso_200()
        {
            // Arrange
            var input = _fixture.Fixture
                .Build<UpdateCategoryIntegrationTransactionInput>()
                .Create();

            _fixture.MediatorMock
                .Setup(x => x.Send(It.IsAny<UpdateCategoryIntegrationTransactionInput>(), It.IsAny<CancellationToken>()))
                .ReturnsAsync(true);

            //Act
            var response = await _sut.UpdateCategoryTransactionAsync(input, default) as IStatusCodeActionResult;

            //Assert
            Assert.Equals(HttpStatusCode.OK, response.StatusCode);
        }

        [Fact]
        public async Task GetAllCategoryAsync_ExecutadoComSucesso_200()
        {
            // Arrange
            var input = _fixture.Fixture
                .Build<UpdateCategoryIntegrationTransactionInput>()
                .Create();

            var output = _fixture.Fixture
                .Build<IEnumerable<string>>()
                .Create();

            _fixture.MediatorMock
                .Setup(x => x.Send(It.IsAny<GetAllCategoryIntegrationTransactionInput>(), It.IsAny<CancellationToken>()))
                .ReturnsAsync(output);

            //Act
            var response = await _sut.GetAllCategoryAsync(default) as IStatusCodeActionResult;

            //Assert
            Assert.Equals(HttpStatusCode.OK, response.StatusCode);
        }
    }
}
