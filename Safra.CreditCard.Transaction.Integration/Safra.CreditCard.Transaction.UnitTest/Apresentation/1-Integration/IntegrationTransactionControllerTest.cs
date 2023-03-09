
using AutoFixture;
using Microsoft.AspNetCore.Mvc.Infrastructure;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Safra.CreditCard.Transaction.Application.Features.IntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Integration.Controllers;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using Xunit;

namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class IntegrationTransactionControllerTest
    {
        private readonly DefaultFixture _fixture = new();
        private readonly IntegrationTransactionController _sut;

        public IntegrationTransactionControllerTest()
        {
            _sut = new(_fixture.MediatorMock.Object);
        }


        [Fact]
        public async Task InsertTransactionAsync_ExecutadoComSucesso_200()
        {
            // Arrange
            var input = _fixture.Fixture
                .Build<IntegrationTransactionInput>()
                .Create();

            _fixture.MediatorMock
                .Setup(x => x.Send(It.IsAny<IntegrationTransactionInput>(), It.IsAny<CancellationToken>()))
                .ReturnsAsync(true);

            //Act
            var response = await _sut.InsertTransactionAsync(input, default) as IStatusCodeActionResult;

            //Assert
            Assert.Equals(HttpStatusCode.OK, response.StatusCode);
        }
    }
}
