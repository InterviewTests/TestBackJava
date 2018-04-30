CREATE TABLE IF NOT EXISTS  sample.gastos (
	codigoGasto UUID,
	numerocartao bigint,
	descricao varchar,
	valor decimal,
	codigoUsuario bigint,
	data timestamp,
	primary key((codigoGasto))
);
