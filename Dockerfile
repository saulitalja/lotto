# Vaihe 1: Käännä Java-ohjelma
FROM openjdk:11 AS build
WORKDIR /app
COPY MainClass.java /app/
RUN javac MainClass.java && jar cfe MyJavaApp.jar MainClass MainClass.class

# Vaihe 2: Suorita Java-ohjelma pienemmällä kuvalla
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/MyJavaApp.jar /app/
CMD ["java", "-jar", "MyJavaApp.jar"]
