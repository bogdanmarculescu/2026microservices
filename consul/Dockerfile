FROM hashicorp/consul:2.0.0
COPY ./consul_kv.json /usr/src/consul/
WORKDIR /usr/src/consul
ENV CONSUL_HTTP_ADDR=consul:8500
ENTRYPOINT until consul kv import @consul_kv.json; do echo "Waiting for Consul"; sleep 2; done