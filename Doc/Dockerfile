# Use Ubuntu as the base image for wider package support
FROM ubuntu:latest

# Install OpenJDK-17, Maven, and necessary tools for Docker installation
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven git curl gnupg-agent software-properties-common

# Add Docker’s official GPG key
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -

# Set up the Docker stable repository
RUN add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

# Install Docker CLI
RUN apt-get update && apt-get install -y docker-ce-cli

# Verify installations
RUN java -version
RUN mvn -version
RUN git --version
RUN docker --version

# Define environment variables for Java and Maven
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
ENV MAVEN_HOME /usr/share/maven

# Optionally: Set a working directory
WORKDIR /app

# Optionally: Start a shell or another specific command by default
CMD ["/bin/bash"]
