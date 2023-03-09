namespace Safra.CreditCard.Transaction.Application.Shared.Configuration
{
    public class DataBaseConfiguration
    {
        public string ConnectionStringMongo { get; set; } = null!;
        public string DataBaseMongo { get; set; } = null!;
        public string CollectionName { get; set; } = null!;
    }
}
