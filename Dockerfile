# Use the GraalVM image with Python support
FROM ghcr.io/graalvm/graalvm-community:latest

# Set the working directory inside the container
WORKDIR /app

RUN gu install python
RUN gu install js

# Copy the Java source file and compile it
COPY . /app/
RUN javac Main.java

# Set the entrypoint to run the Java application
ENTRYPOINT ["java", "-cp", ".", "Main"]