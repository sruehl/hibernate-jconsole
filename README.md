[![Build Status](https://travis-ci.org/sruehl/hibernate-jconsole.svg?branch=master)](https://travis-ci.org/sruehl/hibernate-jconsole)

Development Setup :: Project Hibernate Console
===============================================================================

Prerequisites
-------------

1. Get Git from "https://git-scm.com" and execute the
   following statement to get a local clone:

   > git clone git@github.com:sruehl/hibernate-jconsole.git

2. Download and install a JDK version >=  "1.7.xx":
   http://www.oracle.com/technetwork/java/javase/downloads/index.html

   Set up your environment JAVA_HOME, e.g. with:
   set "JAVA_HOME=$(/usr/libexec/java_home -v 1.7)"

3. Download and install Maven 3.3.9:
   http://maven.apache.org/docs/3.3.9/release-notes.html

   Note: JAVA_HOME must point to your local JDK-installation.

   Verify with:
   > mvn -v

   Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T17:41:47+01:00)
   Maven home: /usr/local/Cellar/maven/3.3.9/libexec
   Java version: 1.8.0_45, vendor: Oracle Corporation
   Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
   Default locale: de_DE, platform encoding: UTF-8
   OS name: "mac os x", version: "10.11.2", arch: "x86_64", family: "mac"

4. (Optional) Download an IDE, e.g.:

   - IntelliJ IDEA
     url: http://www.jetbrains.com/idea/

     Open the project with:

        File > Open > "ROOT/pom.xml".

   - As for the delegate Lombok is used
     url: https://projectlombok.org

     You can install the plugin in IntelliJ IDEA with:

     Preferences > Plugins > Browse repositories... > Lombok Plugin > Install

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

   > jconsole -pluginpath target/hibernate-jconsole-*-SNAPSHOT.jar -J-Xdebug\
   -J-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000\
   -J-Dhibernate.searchpath=modules/hibernate-tester/5-1-tester/target/lib

   Optional Logging Setup:
   -J-Djava.util.logging.config.file=logging.properties


   - Execute Test Application (that can be monitored)

   > java -jar target/hibernate-5-1-tester-*-SNAPSHOT-cli.jar

   Open process connection in the running jconsole choose
   "HibernateContextTester" from the given selection click the last
   tab "Hibernate Monitor"
