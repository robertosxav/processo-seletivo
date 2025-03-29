#!/bin/sh

set -e  # Faz o script falhar caso algum comando retorne erro

echo "Aguardando MinIO iniciar..."
sleep 10
COUNTER=0
MAX_RETRIES=20

while ! mc alias set myminio http://minio-roberto:9000 admin seletivo123@; do
  echo "MinIO ainda não está pronto... aguardando..."
  sleep 2
  COUNTER=$((COUNTER + 1))
  if [ "$COUNTER" -ge "$MAX_RETRIES" ]; then
    echo "Erro: MinIO não iniciou após várias tentativas."
    exit 1
  fi
done

echo "MinIO iniciado! Configurando MinIO Client..."

# Criar bucket se não existir
if mc ls myminio/fotos >/dev/null 2>&1; then
  echo "Bucket 'fotos' já existe."
else
  echo "Criando bucket 'fotos'..."
  mc mb myminio/fotos
  echo "Tornando o bucket 'fotos' público..."
  mc anonymous set public myminio/fotos
  echo "Bucket 'fotos' criado e configurado com sucesso!"
fi
