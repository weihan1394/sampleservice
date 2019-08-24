#!/usr/bin/env bash

for file in \
    "./sampleservice-repo/target/site/jacoco/index.html" \
    "./sampleservice-service/target/site/jacoco/index.html" \
    "./sampleservice-controller/target/site/jacoco/index.html"
do
    if ! [[ -f "$file" ]];
    then
        echo "$file not found"
        exit 1
    fi
done
echo "Jacoco plugin is ok"