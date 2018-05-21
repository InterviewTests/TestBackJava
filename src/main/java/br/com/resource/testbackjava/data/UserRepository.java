package br.com.resource.testbackjava.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
public interface UserRepository  extends CassandraRepository<User, UUID>{

	@Query("select * from USER where codigoUsuario=?0")
	public User findByUserCode(Integer codigoUsuario);

}
