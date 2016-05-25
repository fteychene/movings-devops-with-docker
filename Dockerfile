FROM jboss/wildfly

ENV JBOSS_JOME=/opt/jboss/wildfly

USER root

RUN yum -y install wget \
  && cd /tmp \
  && wget https://jdbc.postgresql.org/download/postgresql-9.4-1201.jdbc41.jar

USER jboss


COPY execute.sh /tmp/
COPY commands.cli /tmp/

RUN /tmp/execute.sh \
  && rm -Rf $JBOSS_JOME/standalone/configuration/standalone_xml_history

ADD target/rest.war $JBOSS_JOME/standalone/deployments/
