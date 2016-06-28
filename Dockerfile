FROM dockerfile/java:oracle-java8
ENTRYPOINT ["java", "-jar", "/cucumber-and-pipeline.jar"]
CMD []
ADD target/cucumber-and-pipeline.jar /
ENV spring.profiles.active dev  
EXPOSE 8082