# SoundsUpAPI
SoundsUpAPI is a Java based RESTful Service (JAX-RS) implemented with Jersey. 
The API serves a social media platform called SoundsUp and has useful endpoints for managing collections such as users, posts and comments.

## Workstation setup

### Prerequisites
* Git installed
* Basic knowledge of Java, an installed version of the JVM, JDK, JRE
* An installed version of Maven.

### Clone the repository

    $ git clone https://github.com/mancristiana/SoundsUpAPI
   
Once you have downloaded the repository locally, you can import it as a maven project in a java IDE such as IntelliJ or Netbeans.

### Build 
To build the application simply run:

    $ mvn package
    
### Run the application
And then run your app using the `java` command:
  
    $ java -jar webapp-runner.jar application.war
    
If you need your WAR file to be expanded before launching add the `--expand-war` option

    $ java -jar target/dependency/webapp-runner.jar --expand-war target/*.war
    
Webapp Runner will then launch a Tomcat instance with the given war deployed to it. 

>Note: 
Maven is configured to download the Webapp Runner plugin which is is just a jar file that can be executed and configured using the `java` command.
No previous steps to install Tomcat are required when using Webapp Runner. 


That's it. Your application should start up on `localhost:8080`

### Generate documentation
To generate documentation use `apidoc` command. 

    
    $ apidoc -i src/main/java -o src/main/webapp
    
>Note: Instructions for the tool can be founds here http://apidocjs.com/


### Deploy to Heroku
To deploy the app we simply need to push our changes to master.     

    $ git checkout master
    $ git merge feature/my-feature
    $ git add . 
    $ git commit -m "Ready to deploy"
    $ git push

From thereon we have set up an automated deployment on Heroku.

#### Procfile

You declare how you want your application executed in `Procfile` in the project root. 

    web:    java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
