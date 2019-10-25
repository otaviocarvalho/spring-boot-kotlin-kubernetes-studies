#!/bin/bash
./mvnw package
eval $(minikube docker-env)
docker build --file=Dockerfile --tag=transactions-reader:latest --rm=true .
