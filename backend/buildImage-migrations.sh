#!/bin/bash
set -e

imageTag=${1:-latest}
imageFullName="task_migrations:$imageTag"

SCRIPT_DIR=$(cd -P -- "$(dirname "$0")" && pwd -P)
BACKEND_DIR="$SCRIPT_DIR"

echo "[Migrations STARTING] building $imageFullName"

echo "[Migrations] remove old image $imageFullName"
docker rmi -f "$imageFullName" || true

echo "[Migrations] build context = $BACKEND_DIR"
echo "[Migrations] dockerfile = $BACKEND_DIR/migrations.Dockerfile"

docker build --no-cache \
  -t "$imageFullName" \
  -f "$BACKEND_DIR/migrations.Dockerfile" \
  "$BACKEND_DIR"

echo "[Migrations FINISHED] $imageFullName"