using System.Collections.Generic;

namespace Safra.CreditCard.Transaction.Application.Shared.Configuration
{
    public class RabbitMQOptions
    {
        public ushort ConnectionRabbitMQPort { get; set; }

        public string ConnectionRabbitMQPassword { get; set; }

        public string ConnectionRabbitMQUserName { get; set; }

        public string ConnectionRabbitMQVirtualhost { get; set; }

        public IList<string> ConnectionRabbitMQHosts { get; set; }
    }
}
