ARG VENV_DIR="/app/graal-env/bin/"

# Use the GraalVM image with Python support
FROM ghcr.io/graalvm/graalvm-community:20

ENV VENV=${VENV_DIR}
VOLUME . /app
WORKDIR /app

# Operating System dependencies
RUN microdnf install unzip patch git wget

# GraalVM language implementations
RUN gu install python
RUN gu install js
RUN gu upgrade --edition ee
COPY ./requirements.txt /app

# Install python dependencies and activate the virtual environment
RUN python -m venv $VENV
WORKDIR $VENV
RUN source activate
RUN pip install -r requirements.txt

# Copy the Java source file and compile it
WORKDIR /app
COPY . /app
RUN javac Main.java

# Set the entrypoint to run the Java application
ENTRYPOINT ["java", "-cp", ".", "Main"]
# ENTRYPOINT ["polyglot", "--jvm", "--shell"]