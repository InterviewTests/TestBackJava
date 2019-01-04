cd gastos-query-api
call mvn clean compile package -DskipTests 
docker build -t gastos-query-api .
cd ..

cd gastos-command-api
call mvn clean compile package -DskipTests 
docker build -t gastos-command-api .
cd ..

cd categorias-api
call mvn clean compile package -DskipTests 
docker build -t categorias-api .
cd ..

cd gateway
call mvn clean compile package -DskipTests 
docker build -t gateway .
cd ..