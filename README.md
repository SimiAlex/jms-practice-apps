# jms-practice-apps

Practice JMS between two Java EE applications.

### Masterplan

<img src="images/masterplan.jpg" alt="Masterplan" width="600">

### Setup environment

1. The app will be deployed to a local `JBoss Wildfly 24.0.1.Final` application server.

2. The message broker is `Apache ActiveMQ` provided by Wildfly (internal topology of the JMS provider). The JMS component 
   is activated only if you run the application server with the `full` profile (use `standalone-full.xml` configuration) 
   when starting it:

   ```bash
   # from WILDFLY_HOME\bin
   $ standalone.bat -c standalone-full.xml
   ```
   
3. Create JMS queues or topics using JBoss Wildfly CLI:

   ```bash
   # ./jboss-cli.sh
   $ jms-queue add --queue-address=myQueue --entries=queue/myQueue,java:jboss/exported/jms/queue/myQueue
   # check if queue was created
   $ /subsystem=messaging-activemq/server=default/jms-queue=myQueue:read-resource
   ```

4. Configure a datasource in Wildfly.

5. Deploy both apps to the application server:
   - using Maven Wildfly plugin or
   
      ```bash
      $ mvn wildfly:deploy -Dforce=true
      ```
   
   - manual deployment using Wildfly's deployments folder or
   - manual deployment using Wildfly's web console


### Project configuration

#### JMS destinations

We are using two queues:
- `myQueue` (java:/myQueue): contains Car objects as XML
- `stringQueue` (java:/stringQueue): contains text messages

#### Database

We are using a PostgreSQL database as a datasource in Wildfly. See https://www.youtube.com/watch?v=xSHXMcRsF0A 
for instructions.  
Configure `persistence.xml` file for a JTA datasource:

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.2"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
    <!-- Define persistence unit -->
    <persistence-unit name="my-persistence-unit" transaction-type="JTA">
        <jta-data-source>java:/PostgresDS</jta-data-source>
        <properties>
            <property name="javax.persistence.schema-generation.database.action" value="update"/>
        </properties>
    </persistence-unit>
</persistence>
```