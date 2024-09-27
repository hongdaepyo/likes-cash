#!/bin/bash
docker build -t likescash:0.1 ../
docker run --rm -d -p 8080:8080 likescash:0.1
