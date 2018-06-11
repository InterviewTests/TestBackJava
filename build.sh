cd app/client
mvn clean compile package -DskipTests

cd ../sale
mvn clean compile package -DskipTests

cd ../sale-worker
mvn clean compile package -DskipTests

cd ../../core/core.registry
mvn clean compile package -DskipTests

cd ../core.gateway
mvn clean compile package -DskipTests
