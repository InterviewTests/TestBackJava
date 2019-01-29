cd config-service
mvn clean compile package -DskipTests 
cd ..

cd discovery-service
mvn clean compile package -DskipTests 
cd ..

cd gateway-service
mvn clean compile package -DskipTests
cd ..

docker-compose up --no-start