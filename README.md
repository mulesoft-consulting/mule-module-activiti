Mule Activiti Module
===================

Supported Activiti Versions
-----------------------

This module is based on the Activiti 5.0 and 5.1 versions


Features
--------

- Poll the process definitions
- Poll the candidate tasks of a user
- Create a process
- Perform an operation over a task


Integration Testing
-------------------

Run:

    mvn -Pit clean verify

The integration tests rely on a locally running Activiti server

Maven Support
-----

Add the following repository:

    <repository>
      <id>muleforge-repo</id>
      <name>MuleForge Repository</name>
      <url>http://repository.muleforge.org</url>
      <layout>default</layout>
    </repository>

To add the Mule Activiti module to a Maven project add the following dependency:

    <dependency>
      <groupId>org.mule.modules</groupId>
      <artifactId>mule-module-activiti</artifactId>
      <version>3.1.0</version>
    </dependency>


Not (Yet) Supported
-------------------

- XA transactions