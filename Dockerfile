FROM java:8

WORKDIR /deployments
CMD mkdir ssl 
COPY ssl/keystore.p12 ssl/keystore.p12    
COPY target/stub-template.jar /deployments/app.jar

CMD java -jar app.jar
