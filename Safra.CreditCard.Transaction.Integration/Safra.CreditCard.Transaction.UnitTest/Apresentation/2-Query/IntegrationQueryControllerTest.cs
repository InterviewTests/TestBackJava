using AutoFixture;
using Microsoft.AspNetCore.Mvc.Infrastructure;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Safra.CreditCard.Transaction.Application.Features.GetAllIntegrationTransaction.Models;
using Safra.CreditCard.Transaction.Application.Features.GetIntegrationTransactionById.Models;
using Safra.CreditCard.Transaction.Application.Shared.Domain;
using Safra.CreditCard.Transaction.Query.Controllers;
using System.Collections.Generic;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using Xunit;


namespace Safra.CreditCard.Transaction.UnitTest.Apresentation._1_Integration
{
    public class IntegrationQueryControllerTest
    {
        private readonly DefaultFixture _fixture = new();
        private readonly IntegrationQueryController _sut;

        public IntegrationQueryControllerTest()
        {
            _sut = new(_fixture.MediatorMock.Object);
        }


        [Fact]
        public async Task GetAllTransactionAsync_ExecutadoComSucesso_200()
        {
            // Arrange
            var input = _fixture.Fixture
                .Build<GetAllIntegrationTransactionInput>()
                .Create();

            var output = _fixture.Fixture
                .Build<List<TransactionDto>>()
                .Create();

            _fixture.MediatorMock
                .Setup(x => x.Send(It.IsAny<GetAllIntegrationTransactionInput>(), It.IsAny<CancellationToken>()))
                .ReturnsAsync(output);

            //Act
            var response = await _sut.GetAllTransactionAsync(input, default) as IStatusCodeActionResult;

            //Assert
            Assert.Equals(HttpStatusCode.OK, response.StatusCode);
        }

        [Fact]
        public async Task GetTransactionByIdAsync_ExecutadoComSucesso_200()
        {
            var output = _fixture.Fixture
                .Build<TransactionDto>()
                .Create();

            _fixture.MediatorMock
                .Setup(x => x.Send(It.IsAny<GetIntegrationTransactionByIdInput>(), It.IsAny<CancellationToken>()))
                .ReturnsAsync(output);

            //Act
            var response = await _sut.GetTransactionByIdAsync("104554", default) as IStatusCodeActionResult;

            //Assert
            Assert.Equals(HttpStatusCode.OK, response.StatusCode);
        }
    }
}
