"# testbackjava" 
Testes para candidatos vaga de desenvolver Java! 
Requerimentos:

JDK 1.8
Cassandra 3.0.9
Em ambiente Windows, instalar: http://downloads.datastax.com/datastax-ddc/datastax-ddc-64bit-3.9.0.msi
RabbitMQ 3.7.5 (não implementado)

Utilizar classe TestbackjavaApplication para iciar a aplicação
Irá iniciar  mwebserver para acesso a página de visualização  do sistema

Será acionada a classe CassandraRun que irá adicionar a base dados com registros de testes

Front-end

tecnologias utilizadas:
html5
AngularJs em acessos do ao controler para login do usuário, filtro de dados e categorização dos dados;
Bootstrap - criação de menu, formtação da tela, inclsuive como modal;
Bootstrap-table - formatação e operações na tabela no lado cliente;
jQuery - funções complementares

Back-end
Spring-boot - inicializar a aplicação
Spring-aspects - manipulação de mensagens de erro
DevTools
Cassandra
JSon/jackson
Criptografia - algoritmos providos peça tecnologia java

Funcionalidades:
Entrar:
Login - usuário - serão criados usuarios 0,1, e 2, com respectivas senhas. CAda um com gastos e categorias para teste.
Senha: senha criptografada. Os dados são armazendas noa tabela "User", criada automaticamnete pela aplicação.

Sair:
Reira a sessão do usuário, recarregando a página

Listagem de gastos:
Através do menu "Funcionalides", item "Listegem de Gastos"
Lista todos os gastos do usuário armazenado, ordenando por data de modo decrescente.
Aparecerá um filtro de data, para especificar a data que o usuario deseja

Filtro:
Através do menu "Funcionalides", item "Filtro", o usuário deverá informar  a data que deseja visualizar os gastos

Categorizar
Gastos não categorizados, terão o lnk "-Categorizar-", que usuará uma tela de detalhes do gasto.
Um vombo, com as categorias mais utilizadas pelos demais usuários aparecerá, na ordem de significância.
Essas opções estarão presentes no autocompletar na hora de entrar com uma nova descrição.

Salvar
Salva o registro de gasto, com  a categoria informada.
Inclui ou ayualiza o contador da categoria utilizada.








