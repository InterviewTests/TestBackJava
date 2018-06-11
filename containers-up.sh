docker-compose up .

cd app/client
docker-compose up . -scale=1

cd ../sale
docker-compose up . scale=10

cd ../sale-worker
docker-compose up . scale=5
