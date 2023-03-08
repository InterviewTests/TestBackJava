using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;

namespace Safra.CreditCard.Transaction.Application.Shared.Domain
{
    public class TransactionDto
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string? Id { get; set; }

        public string Description { get; set; }
        public string Category { get; set; }
        public decimal Value { get; set; }
        public int CustomerCode { get; set; }
        public DateTime DateTransaction { get; set; }
        public DateTime DateInsert => DateTime.Now;
    }
}
