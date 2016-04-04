#!/usr/bin/env bash

# Dump db
docker exec -i ecs-enigma30-10-enigma30-mysql-dab6bdf182cfb9f35b00 -u root -proot enigme30 < dump/enigme30-dump.sql

