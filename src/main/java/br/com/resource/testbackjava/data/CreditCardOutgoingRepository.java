package br.com.resource.testbackjava.data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface CreditCardOutgoingRepository extends CassandraRepository<CreditCardOutgoing, UUID>{

	@Query("select * from CreditCardOutgoing where codigoUsuario=?0")
	public List<CreditCardOutgoing> findByUserCode(Integer codigoUsuario);
	
	@Query("select * from CreditCardOutgoing where codigoUsuario=?0 and data>=?1 and data<=?2  allow filtering")
	public List<CreditCardOutgoing> findByDate(Integer codigoUsuario, Date begin, Date end);

}
