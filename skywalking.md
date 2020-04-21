VM options -javaagent:/home/m2keloop/skywalking/apache-skywalking-apm-bin-es7/agent/skywalking-agent.jar
ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800;SW_AGENT_NAME=spring-skywalking
#### 忽略追踪
```$xslt
cp optional-plugins/apm-trace-ignore-plugin-6.6.0.jar plugins/
SW_AGENT_TRACE_IGNORE_PATH = ...
```