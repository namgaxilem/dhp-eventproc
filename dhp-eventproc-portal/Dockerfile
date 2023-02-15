FROM adoptopenjdk/openjdk11:jre

# Switch to non-root user
RUN useradd --create-home appuser
WORKDIR /home/appuser

USER appuser

VOLUME /tmp
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]