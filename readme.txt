
Development Setup :: Project Hibernate Console
===============================================================================

Prerequisites
-------------

1. Get Mercurial from "http://mercurial.selenic.com/" and execute the
   following statement to get a local clone:

   > hg clone http://hg.code.sf.net/p/hibernate-jcons/code hibernate-jcons-code

2. Download and install a JDK version >=  "1.6.xx":
   http://www.oracle.com/technetwork/java/javase/downloads/index.html

   Set up your environment JAVA_HOME, e.g. with:
   set "JAVA_HOME=c:\Program Files\Java\jdk1.6.0"

3. Download and install Maven 3.0.4:
   http://maven.apache.org/docs/3.0.4/release-notes.html

   Note: JAVA_HOME must point to your local JDK-installation.

   Verify with:
   > mvn -v

   Apache Maven 3.0.4
   Maven home: c:\Program Files\Apache\apache-maven-3.0.4
   Java version: 1.6.0, vendor: Sun Microsystems Inc.
   Java home: c:\Program Files\Java\jdk1.6.0\jre

4. (Optional) Download an IDE, e.g.:

   - Eclipse / Spring Tool Suite
     url: http://www.springsource.org/eclipse-downloads

     Open the project with:

        File > Import > Maven > Existing Maven Projects

   - IntelliJ IDEA
     url: http://www.jetbrains.com/idea/

     Open the project with:

        File > Open > "ROOT/pom.xml".


   Create Run Configuration

      - Find/Open HibernateContextTester.java
      - Open context menu (right click on editor window)
      - Context menu: Run / Run As > Java Application


Building & Running
------------------

1. Building using Commandline

   Make with:
     > mvn
   or
     > mvn clean install
   or including local site
     > mvn clean install site
  
   Deploy (including site to SF.net):
     - mvn clean install deploy site-deploy

2. Running

   - Execute the Jconsole Plugin Hibernate Console:

   > jconsole -pluginpath target/hibernate-jconsole-1.0.7-SNAPSHOT.jar -J-Xdebug\
   -J-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000\
   -J-Dhibernate.searchpath=modules/hibernate-tester/3-6-tester/target/lib

   Optional Logging Setup:
   -J-Djava.util.logging.config.file=logging.properties


   - Execute Test Application (that can be monitored)

   > java -jar target/hibernate-3-6-tester-1.0.8-SNAPSHOT-cli.jar

   Open process connection in the running jconsole choose
   "HibernateContextTester" from the given selection click the last
   tab "Hibernate Monitor"