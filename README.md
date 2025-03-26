## Projeto: Sistema do Processo Seletivo 

## Nome: Roberto de Souza Xavier Junior
## Inscrição: xx

---

## Tecnologias Utilizadas

- **Spring Boot**
- **Java 17**
- **MinIO**:latest
- **PostgreSQL**:latest
- **NGINX**:latest

---

## Como Executar

1. Navegue até a pasta `sandbox` no projeto.
2. Execute o seguinte comando no terminal dentro dessa pasta:

   ```bash
   docker-compose up
   ```

   Este comando iniciará os seguintes containers:
   - MinIO
   - PostgreSQL
   - NGINX
   - Aplicação Spring Boot

---

## Arquitetura da Solução

A aplicação utiliza **NGINX** como proxy reverso para resolver problemas de geração de links temporários no **MinIO**. 

---

## Arquitetura do Projeto

O projeto segue os princípios de arquitetura **Ports And Adapters**


---
## Como Testar

### Api

3. Acesse  `Swagger `

```shellscript
http://localhost:8083/apiprocessoseletivo/swagger-ui/index.html
```
4. Realizar AUTENTICACAO
   - 4.1 - Execute o serviço **POST** /atuth/login que está na **AUTENTICAÇÃO**. Para autenticar utlize o **usuário: admin**, **senha: password**
   - 4.2 - Copie o acessToken e insira na variável autenticação (canto superior direito).

5. CRUD de CIDADE **(Recurso opcinional)**
   - 5.1 - Utilize o serviço **POST** para criar uma nova cidade. Caso não queira criar cidade, já foram inseridas 05 cidades no start da aplicação
   - 5.2 - Verifique as cidades existentes: Utilize o serviço **GET /paginado/all** para verificar as cidades que já existem de forma paginado e o serviço **GET /{cidId}** para buscar uma cidade específica
   - 5.3 - Utilize o serviço **PUT /{cidId}** para atualizar uma cidade
   - 5.4 - Utilize o serviço **DELETE /{cidId}** para excluir uma cidade
   
6. CRUD de ENDERECO **(Recurso opcinional)**
   - 6.1 - Utilize o serviço **POST** para criar uma nova endereco. Caso não queira criar endereco, já foram inseridas 05 enderecos no start da aplicação
         - 6.1.1- Na criação de um endereco, é possível criar e editar cidade. Se for passado um id de cidade juntamente com os outros campos de cidade, a cidade será atualizada, se o id da cidade não passado será criado uma nova cidade.
   - 6.2 - Verifique as enderecos existentes: Utilize o serviço **GET /paginado/all** para verificar as enderecos que já existem de forma paginado e o serviço **GET /{endId}** para buscar uma endereco específico
   - 6.3 - Utilize o serviço **PUT /{endId}** para atualizar uma endereco
        - 6.3.1- Na atualização de um endereco, é possível criar e editar cidade. Se for passado um id de cidade juntamente com os outros campos de cidade, a cidade será atualizada, se o id da cidade não passado será criado uma nova cidade.
   - 6.4 - Utilize o serviço **DELETE /{endId}** para excluir uma endereco
   

7. CRUD de UNIDADE
   - 7.1 - Utilize o serviço **POST** para criar uma nova unidade.
   - 7.2 - Verifique as unidades existentes: Utilize o serviço **GET /paginado/all** para verificar as unidades que já existem de forma paginado e o serviço **GET /{unidId}** para buscar uma unidade específica
   - 7.3 - Utilize o serviço **PUT /{unidId}** para atualizar uma unidade
   - 7.4 - Utilize o serviço **DELETE /{unidId}** para excluir uma unidade
     
8. CRUD de Servidor Efetivo
   - 8.1 - Utilize o serviço **POST** para criar um novo servidor efetivo.
    - 8.1.1- Na criação de um servidor efetivo é necessário informar a pessoa completa.
   - 8.2 - Verifique os servidores efetivos existentes: Utilize o serviço **GET /paginado/all** para verificar as servidores efetivos que já existem de forma paginado e o serviço **GET /{unidId}** para buscar uma servidor efetivo específico.
   - 8.3 - Utilize o serviço **PUT /{lotId}** para atualizar um servidor 
   - 8.4 - Utilize o serviço **DELETE /{lotId}** para excluir uma lotação
  
9. CRUD de Servidor Temporário
   - 9.1 - Utilize o serviço **POST** para criar um novo servidor temporário.
         - 9.1.1- Na criação de um servidor efetivo é necessário informar a pessoa completa.
   - 9.2 - Verifique os servidores temporários existentes: Utilize o serviço **GET /paginado/all** para verificar as servidores temporários que já existem de forma paginado e o serviço **GET /{unidId}** para buscar uma servidor temporário específico.
   - 9.3 - Utilize o serviço **PUT /{lotId}** para atualizar uma lotação
   - 9.4 - Utilize o serviço **DELETE /{lotId}** para excluir uma lotação
     
10. CRUD de Lotação
   - 10.1 - Utilize o serviço **POST** para criar uma nova lotação.
         - 10.1.1- Na criação de uma lotação não é necessário informar a pessoa completa, somente o id da pessoa.
         - 10.1.2- Na criação de uma lotação não é necessário informar a unidade completa, somente o id da unidade.
   - 10.2 - Verifique as lotações existentes: Utilize o serviço **GET /paginado/all** para verificar as lotações que já existem de forma paginado e o serviço **GET /{unidId}** para buscar uma lotação específica
   - 10.3 - Utilize o serviço **PUT /{lotId}** para atualizar uma lotação
   - 10.4 - Utilize o serviço **DELETE /{lotId}** para excluir uma lotação



