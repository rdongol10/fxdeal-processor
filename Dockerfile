FROM openjdk:11
COPY target/*.jar fxDealApp.jar
ENTRYPOINT ["java","-jar","/fxDealApp.jar"]