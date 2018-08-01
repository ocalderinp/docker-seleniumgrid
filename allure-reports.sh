#!/usr/bin/env bash

ALLURE_IMAGE=automationwizards/allure:1.4.24.RC3

docker run -d --rm -v $PWD/reports/allure-reports:/allure-report -v $PWD/reports/allure-results:/allure-results $ALLURE_IMAGE allure report generate /allure-results -o /allure-report
docker run -d --rm --name=allure_reporter -p 5000:5000 -v $PWD/reports/allure-reports:/allure-report $ALLURE_IMAGE allure report open -o /allure-report -p 5000
echo "reports server running in http://127.0.0.1:5000"
read -n 1 -s -r -p "press any keey to quit server"
docker stop allure_reporter
echo "reports server is down"


