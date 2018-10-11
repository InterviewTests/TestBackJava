OBSERVAÇÕES

<=> RESUMO DO DESENVOLVIMENTO
	* Iniciei o desenvolvimento com a criação da ROLE para configuração do SPRING MVC e do JPA-HIBERNATE
	* ROLE: criação do Model, JSP, DAO (métodos: buscar todas e gravar), Controller e Validation
	* Depois fui para o desenvolvimento da parte de segurança com autenticação e autorização, tela de login e configuração do SPRING Security
	* Criação dos Models Usuário e Cliente, do JSP do Login, dos DAOs de Usuário (método: buscar por ID) e Cliente (método: buscar por loadUserByUsername, devido ao spring 
	  security) e do Controller para o Login
	* Cliente é um Usuário e pode ter Um ou Mais Roles
	* Login através de E-mail e Senha (padrão: BCryptPasswordEncoder, do próprio Spring Security)
	* Depois fui para o desenvolvimento da Gestão de Gastos
	* Criação dos Models Gasto e GastoLista, do DTO para o cadastro de Gasto, dos JSP para Listar e Detalhar Gasto, do DAO (métodos: buscar todos, buscar por ID, gravar,
	  alterar, buscar gasto do cliente com mesma descrição para categorizar automaticamente, buscar categoria do cliente para o autocomplete), Controller, Validation e 
	  Serviços JSON (GastoRestController e GastoService)
	* Gasto tem descrição, valor, usuário, data e categoria

<=> Candidatura
	* A candidatura é através da empresa RESOURCE IT SOLUTIONS

<=> Banco de Dados
	MySQL (versão: mysql-connector-java-5.1.46-bin)
	Database: gestaodegastos
	Usuário: root
	Senha: "definida na instalação no mysql"
	
<=> Servidor
	Tomcat (versão: apache-tomcat-7.0.47)

<=> Carga de Usuário 
	(Role: ADMIN)
	insert into Role (nome) values ('ROLE_ADMIN');
	(Usuário Administrador)
	insert into Usuario (nome) values ('Administrador');
	(Cliente Administrador com Senha 123456)
	insert into Cliente (usuario_id, email, senha) values (1, 'admin@gestaodegastos.com.br', '$2a$10$JrH2NC0WgpKTTqssWBYIc.jJAqHPCSRqe5cK1mXQ6EJtQS2sPm8cW');
	(Cliente Administrador com o papel ROLE_ADMIN)
	insert into Cliente_Role (Cliente_usuario_id, roles_id) values (1,1);

<=> Login na Aplicação
	* E-mail
	* Senha

<=> Exemplos de alguns Serviços
	(Cadastrar Novo Gasto)
		http://localhost:8080/gestaodegastos/servico/gastos/
		Estrutura do Json:
		{"descricao": "", "valor": "", "codigoUsuario": "", "data": ""}
		* Método: POST
		* A data no formato "dd/MM/yyyy HH:mm:ss"
	(Detalhar um Gasto)
		http://localhost:8080/gestaodegastos/servico/gastos/{id}
		* Método: GET
		* {id}: informar o ID do Gasto
	(Listar Todos os Gastos do Cliente Autenticado: gastos realizados 5 segundos atrás)
		http://localhost:8080/gestaodegastos/servico/gastos/cliente/{codigoDoCliente}
		* Método: GET
		* {codigoDoCliente}: informar o ID do Cliente
	

SHOW ME THE CODE

# DESAFIO:

API REST para Gestão de Gastos!

=> Funcionalidade: Integração de gastos por cartão
   Apenas sistemas credenciados poderão incluir novos gastos
   É esperado um volume de 100.000 inclusões por segundo
   Os gastos, serão informados atraves do protoloco JSON, seguindo padrão:
   { "descricao": "alfanumerico", "valor": double americano, "codigousuario": numerico, "data": Data dem formato UTC }

=> Funcionalidade: Listagem de gastos*
   Dado que acesso como um cliente autenticado que pode visualizar os gastos do cartão
   Quando acesso a interface de listagem de gastos
   Então gostaria de ver meus gastos mais atuais.
   *Para esta funcionalidade é esperado 2.000 acessos por segundo.
   *O cliente espera ver gastos realizados a 5 segundos atrás.

=> Funcionalidade: Filtro de gastos
   Dado que acesso como um cliente autenticado
   E acessei a interface de listagem de gastos
   E configure o filtro de data igual a 27/03/1992
   Então gostaria de ver meus gastos apenas deste dia.
   
=> Funcionalidade: Categorização de gastos
   Dado que acesso como um cliente autenticado
   Quando acesso o detalhe de um gasto
   E este não possui uma categoria
   Então devo conseguir incluir uma categoria para este

=> Funcionalidade: Sugestão de categoria
   Dado que acesso como um cliente autenticado
   Quando acesso o detalhe do gasto que não possui categoria
   E começo a digitar a categoria que desejo
   Então uma lista de sugestões de categoria deve ser exibida, estas baseadas em categorias já informadas por outro usuários.

=> Funcionalidade: Categorização automatica de gasto
   No processo de integração de gastos, a categoria deve ser incluida automaticamente 
   caso a descrição de um gasto seja igual a descrição de qualquer outro gasto já categorizado pelo cliente
   o mesmo deve receber esta categoria no momento da inclusão do mesmo



# Avaliação

Você será avaliado pela usabilidade, por respeitar o design e pela arquitetura da API. É esperado que você consiga explicar as decisões que tomou durante o desenvolvimento através de commits.

Springboot - Java - Maven (preferêncialmente) (https://projects.spring.io/spring-boot/)
RESTFul (https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/)
DDD (https://airbrake.io/blog/software-design/domain-driven-design)
Microservices (https://martinfowler.com/microservices/)
Testes unitários, teste o que achar importante (De preferência JUnit + Mockito). Mas pode usar o que você tem mais experiência, só nos explique o que ele tem de bom.
SOAPUI para testes de carga (https://www.soapui.org/load-testing/concept.html)
Uso de diferentes formas de armazenamento de dados (REDIS, Cassandra, Solr/Lucene)
Uso do git

Diferencial: Criptografia de comunicação, com troca de chaves. (http://noiseprotocol.org/)
Diferencial: CQRS (https://martinfowler.com/bliki/CQRS.html)
Diferencial: Docker File + Docker Compose (com dbs) para rodar seus jars.



# Observações gerais

Adicione um arquivo README.md com os procedimentos para executar o projeto. Pedimos que trabalhe sozinho e não divulgue o resultado na internet.

Faça um fork desse desse repositório em seu Github e nos envie um Pull Request com o resultado, por favor informe por qual empresa você esta se candidatando.




# Importante: não há prazo de entrega, faça com qualidade!

BOA SORTE!