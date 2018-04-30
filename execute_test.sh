#!/usr/bin/env bash

mvn clean test -Dsuite="SMOKE_TEST" -Denvironment="QA" -Dhub=$HUB_TCP_ADDR:$HUB_TCP_PORT
cp -r /root/automationFramework/target/allure-results /root/automationFramework/reports  
cp -r /root/automationFramework/target/surefire-reports /root/automationFramework/reports
