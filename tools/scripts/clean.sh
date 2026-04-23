#!/bin/bash
set -e
currentDir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
rootDir="$currentDir/../../"

(cd "$rootDir" && exec docker-compose -f ./tools/docker/docker-compose.yml \
  --project-name=task-manager-service down -v)
(cd "$rootDir" && exec docker-compose -f ./tools/docker/docker-compose.yml \
  --project-name=task-manager-service rm -f)
