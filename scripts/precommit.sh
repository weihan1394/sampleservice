#!/bin/sh
mvn com.coveo:fmt-maven-plugin:format && mvn checkstyle:check && mvn clean install -P test-coverage