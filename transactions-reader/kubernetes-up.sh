#!/bin/bash
kubectl create -f deployment.yaml
minikube service transactions-reader
