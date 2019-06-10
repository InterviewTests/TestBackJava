FROM maven:3-jdk-8

RUN mkdir data && cd /data && mkdir gastos-api && cd gastos-api

WORKDIR /data/gastos-api

ADD . /data/gastos-api

CMD ["mvn","spring-boot:run"]
