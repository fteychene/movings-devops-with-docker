FROM jboss/wildfly

ENV JBOSS_JOME=/opt/jboss/wildfly

ADD standalone.xml $JBOSS_JOME/standalone/configuration/standalone.xml
ADD target/rest.war $JBOSS_JOME/standalone/deployments/
