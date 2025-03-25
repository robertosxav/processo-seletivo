    - [Download](#download)
    - [Execução](#execução)
    - [api](#swagger)
### Download

1. Clone o projeto

```shellscript
$ git clone https://github.com/robertosxav/processo-seletivo.git
$ cd processo-seletivo/api-processo-seletivo/sandbox/
```

### Execução

2. Inicialize a aplicação com `docker-compose up --build`

```shellscript
docker-compose up --build
```

### Api

3. Acesse  `Swagger `

```shellscript
`http://localhost:8083/apiprocessoseletivo/swagger-ui/index.html`
```
4. Realizar AUTENTICACAO
  4.1 - Execute o serviço /atuth/login. Uitlize o usuário: admin, senha: password
  4.2 - Copie o acessToken e insira na variável autenticação (canto superior direito).

5. CRUD de CIDADE( opcinional)
   5.1 - Utilize o serviço POST para criar uma nova cidade. Caso não queira criar cidade, já foram inseridas 05 cidades no start da aplicação
   5.2 - Verifique as cidades existentes: Utilize o serviço GET /paginado/all para verificar as cidades que já existem de forma paginado e o serviço GET /{cidId} para buscar uma cidade específica
   5.3 - Utilize o serviço PUT /{cidId} para atualizar uma cidade
   5.4 - Utilize o serviço DELETE /{cidId} para excluir uma cidade
   
6. CRUD de ENDERECO( opcinional)
   5.1 - Utilize o serviço POST para criar uma nova endereco. Caso não queira criar endereco, já foram inseridas 05 enderecos no start da aplicação
    5.1.1- Na criacao de um endereco, é possível criar e editar cidade. Se for passado um id de cidade juntamente com os outros campos de cidade, a cidade será atualizada, se o id da cidade não passado será criado uma nova cidade.
   5.2 - Verifique as enderecos existentes: Utilize o serviço GET /paginado/all para verificar as enderecos que já existem de forma paginado e o serviço GET /{endId} para buscar uma endereco específico
   5.3 - Utilize o serviço PUT /{endId} para atualizar uma endereco
   5.4 - Utilize o serviço DELETE /{endId} para excluir uma endereco

6. CRUD de UNIDADE
   5.1 - Utilize o serviço POST para criar uma nova unidade.
   5.2 - Verifique as unidades existentes: Utilize o serviço GET /paginado/all para verificar as unidades que já existem de forma paginado e o serviço GET /{endId} para buscar uma unidade específica
   5.3 - Utilize o serviço PUT /{unidId} para atualizar uma endereco
   5.4 - Utilize o serviço DELETE /{unidId} para excluir uma endereco

