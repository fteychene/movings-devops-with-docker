FROM alpine

RUN apk add --update curl

ADD README /opt/conf/

RUN cat /opt/conf/README

ENTRYPOINT ["curl"]
