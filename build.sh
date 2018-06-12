cd core/core.registry
mvn clean compile package -DskipTests                                                    


cd ../core.gateway                                                                               
mvn clean compile package -DskipTests


cd ../../app/sale-worker
mvn clean compile package -DskipTests


cd ../sale-api
mvn clean compile package -DskipTests


cd ../client-api
mvn clean compile package -DskipTests

cd ../..

docker-compose up --no-start