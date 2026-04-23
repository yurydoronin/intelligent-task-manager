#!/bin/bash
set -e

SCRIPT_DIR=$(cd -P -- "$(dirname "$0")" && pwd -P)
ROOT_DIR=$(cd -P -- "$SCRIPT_DIR/../.." && pwd -P)

echo "[RUN] ROOT_DIR = $ROOT_DIR"

# сборка миграций
"$ROOT_DIR/backend/buildImage-migrations.sh"

# запуск docker compose
docker-compose \
  -f "$ROOT_DIR/tools/docker/docker-compose.yml" \
  --project-directory "$ROOT_DIR" \
  --project-name=task-manager-service \
  up -d --remove-orphans