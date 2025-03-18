#!/bin/sh

# Gerar Access Key e Secret Key com tamanho correto
ACCESS_KEY=${ACCESS_KEY:-"buceta$(openssl rand -hex 3 | cut -c1-10)"}  # 10 caracteres
SECRET_KEY=${SECRET_KEY:-$(openssl rand -hex 16)} 

echo "Aguardando MinIO iniciar..."
while ! mc alias set myminio http://minio:9000 admin123 strongpassword123; do
  echo "MinIO ainda não está pronto... aguardando..."
  sleep 2
done

echo "MinIO iniciado! Configurando MinIO Client..."

# Criar bucket se não existir
if mc ls myminio/testebucket >/dev/null 2>&1; then
  echo "Bucket 'testebucket' já existe."
else
  echo "Criando bucket 'testebucket'..."
  mc mb myminio/testebucket
  if [ $? -eq 0 ]; then
    echo "Bucket 'testebucket' criado com sucesso!"
  else
    echo "Erro ao criar bucket!"
    exit 1
  fi
fi

# Criar usuário se não existir
if mc admin user info myminio "$ACCESS_KEY" >/dev/null 2>&1; then
  echo "Usuário '$ACCESS_KEY' já existe no MinIO."
else
  echo "Criando novo usuário no MinIO..."
  mc admin user add myminio "$ACCESS_KEY" "$SECRET_KEY"

  # Verificar criação do usuário
  if mc admin user info myminio "$ACCESS_KEY" >/dev/null 2>&1; then
    echo "Usuário '$ACCESS_KEY' criado com sucesso!"
  else
    echo "Erro ao criar usuário no MinIO!"
    exit 1
  fi
fi

# Atribuir permissões ao usuário
mc admin policy attach myminio readwrite --user="$ACCESS_KEY"

# Salvar credenciais para a API
echo "S3_ACCESS_KEY=$ACCESS_KEY" > /app/minio_credentials.env
echo "S3_ACCESS_SECRET=$SECRET_KEY" >> /app/minio_credentials.env

echo "Configuração do MinIO concluída com sucesso!"
