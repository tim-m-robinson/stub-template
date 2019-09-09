FROM openjdk:11-jre-slim

LABEL maintainer="Tim Robinson (tim.robinson@atos.net)"

# Install ca-bundle
RUN apt-get update && apt-get install ca-certificates && apt-get clean all

# Set the user to non-root
USER 1001

# Expose the application tcp port
EXPOSE 8443

WORKDIR /deployments
CMD mkdir ssl
COPY ssl/keystore.p12 ssl/keystore.p12
COPY target/stub-template.jar /deployments/app.jar

CMD java -jar app.jar
