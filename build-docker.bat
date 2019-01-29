cd config-service
call mvn clean compile package 
docker build -t config-service .
cd ..

cd discovery-service
call mvn clean compile package 
docker build -t discovery-service .
cd ..

docker-compose up --no-start