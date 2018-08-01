#
# Test AutomationFramework Dockerfile
# Based on Ubuntu
#

# Pull base image
FROM ubuntu:16.04

# File Author / Maintainer
MAINTAINER Oscar Calderin (ocalderin14@gmail.com)

# Update and upgrade repository sources list
RUN apt-get update && \
    apt-get install -y default-jdk maven

# Set environment variables.
ENV HOME /root
ENV HUB_TCP_ADDR http://selenium-hub
ENV HUB_TCP_PORT 4444

# Define working directory.
WORKDIR /root/automationFramework

# Prepare by downloading dependencies
ADD pom.xml /root/automationFramework/pom.xml
RUN ["mvn", "dependency:resolve"]

# Adding sources
ADD src /root/automationFramework/src
ADD execute_test.sh /root/automationFramework

# Creating folder for reports
RUN mkdir -p /root/automationFramework/reports && \
    chmod +x /root/automationFramework/execute_test.sh

# Adding volume for reports
VOLUME ["/root/automationFramework/reports"]

# Execute tests
ENTRYPOINT ["/root/automationFramework/execute_test.sh"]
