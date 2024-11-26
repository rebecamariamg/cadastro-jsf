FROM openliberty/open-liberty:kernel-java11-openj9-ubi

COPY --chown=1001:0  target/demo.war /config/dropins/
COPY --chown=1001:0  server.xml /config/

# Copy the database file into a writable directory
USER root
COPY src/main/resources/db/sqlite/login.db /opt/login.db

# Ensure the /opt directory is writable by all
RUN chmod -R 777 /opt

# Switch back to non-root user for security
USER 1001

RUN configure.sh