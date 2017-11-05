FROM java

# Define working directory
WORKDIR /opt/bot/config

# Deploy core
ADD build/libs/send-text-1.0-SNAPSHOT.jar /opt/bot/jar/app.jar

ENTRYPOINT java -jar /opt/bot/jar/app.jar