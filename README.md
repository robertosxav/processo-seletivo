## Dados da Inscrição PSS 02/2025/SEPLAG (Analista de TI - Perfil Junior, Pleno e Sênior)

### Número da inscrição: 8219
### CPF: 04422044184
### Perfil: DESENVOLVEDOR JAVA (BACK-END) - SÊNIOR
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
   - 1.1. Execute o seguinte comando no terminal dentro dessa pasta:

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

O projeto segue os princípios de arquitetura **Ports And Adapters**.


---
## Como Testar

### MinIO
2. Acesse   `MiniIO `
```bash
http://localhost:9001/
```
      2.1 - Usuário de acesso: admin
      2.2 - Senha de acesso: seletivo123@
      2.3 - Verifique que foi criado um bucket com nome fotos
### Api

3. Acesse  `Swagger `

```shellscript
http://localhost:8083/apiseletivo/swagger-ui/index.html#/
```
4. Realizar AUTENTICAÇÃO
   - 4.1 - Execute o serviço **POST /auth/login** que está na **AUTENTICAÇÃO**. Para autenticar utilize o **usuário: admin**, **senha: password**
   - 4.2 - Copie o acessToken e insira na variável **Authorize** (canto superior direito).
   - 4.3 - Após esta ação você estará autenticado. Tempo token: **5 minutos**.
   - 4.4 - É possível utilizar o refresh-token, através do serviço **POST /auth/refresh-token**. Neste serviço cole o refreshToken gerado no **/auth/login** e gere um novo token. Cole este token variável authorize.
   
5. CRUD de CIDADE **(Recurso opcional)**
   - 5.1 - Utilize o serviço **POST /cidade/** para criar uma nova cidade. Caso não queira criar cidade, já foram inseridas 05 cidades no start da aplicação.
   - 5.2 - Verifique as cidades existentes: Utilize o serviço **GET cidade/paginado/all** para verificar as cidades que já existem de forma paginado e o serviço **GET /cidade/{cidId}** para buscar uma cidade específica.
   - 5.3 - Utilize o serviço **PUT /cidade/{cidId}** para atualizar uma cidade.
   - 5.4 - Utilize o serviço **DELETE /cidade/{cidId}** para excluir uma cidade.
   
6. CRUD de ENDERECO **(Recurso opcional)**
   - 6.1 - Utilize o serviço **POST /endereco/** para criar uma nova endereço. Caso não queira criar endereco, já foram inseridas 05 endereços no start da aplicação.
        - 6.1.1- Na criação de um endereço, é possível criar e editar cidade. Se for passado um id de cidade juntamente com os outros campos de cidade, a cidade será atualizada, se o id da cidade não for passado será criado uma nova cidade.
   - 6.2 - Verifique as enderecos existentes: Utilize o serviço **GET /endereco/paginado/all** para verificar as endereços que já existem de forma paginado e o serviço **GET /endereco/{endId}** para buscar uma endereço específico.
   - 6.3 - Utilize o serviço **PUT /endereco/{endId}** para atualizar uma endereço.
        - 6.3.1- Na edição de um endereço, é possível criar e editar cidade. Se for passado um id de cidade juntamente com os outros campos de cidade, a cidade será atualizada, se o id da cidade não for passado será criado uma nova cidade.
   - 6.4 - Utilize o serviço **DELETE /endereco/{endId}** para excluir uma endereço.
   

7. CRUD de UNIDADE
   - 7.1 - Utilize o serviço **POST /unidade/** para criar uma nova unidade.
   - 7.2 - Verifique as unidades existentes: Utilize o serviço **GET /unidade/paginado/all** para verificar as unidades que já existem de forma paginado e o serviço **GET /unidade/{unidId}** para buscar uma unidade específica.
   - 7.3 - Utilize o serviço **PUT /unidade/{unidId}** para atualizar uma unidade.
   - 7.4 - Utilize o serviço **DELETE /unidade/{unidId}** para excluir uma unidade.
     
8. CRUD de Servidor Efetivo
   - 8.1 - Utilize o serviço **POST /servidor-efetivo/** para criar um novo servidor efetivo.
        - 8.1.1- Na criação de um servidor efetivo é necessário informar a pessoa completa.
        - 8.1.2- Na criação de um servidor efetivo não é necessário passar uma lista de endereços completa, somente a lista de ids do endereço
   - 8.2 - Verifique os servidores efetivos existentes: Utilize o serviço **GET /servidor-efetivo/paginado/all** para verificar as servidores efetivos que já existem de forma paginado e o serviço **GET servidor-efetivo/{pesId}** para buscar uma servidor efetivo específico.
   - 8.3 - Utilize o serviço **PUT /servidor-efetivo/{pesId}** para atualizar um servidor
   - 8.4 - Utilize o serviço **POST /servidor-efetivo/upload-fotos/{pesId}** para inserir uma ou mais fotos em um servidor efetivo
   - 8.5 - Utilize o serviço **DELETE /servidor-efetivo/{pesId}** para excluir uma servidor efetivo.
   - 8.6 - Utilize o serviço **GET /servidor-efetivo/endereco-funcional/**,que tem como parâmetro a variável nome, para buscar o endereco funcional a partir de uma parte do nome, apenas para servidores efetivos.
   - 8.7 - Utilize o serviço **GET /servidor-efetivo/lotados-unidade/{unidId}** para buscar os servidores efetivos lotados em determinada unidade. Para ter resutados neste serviço primeiro é necessário criar alguma lotação **10. CRUD de Lotação**
  
9. CRUD de Servidor Temporário
   - 9.1 - Utilize o serviço **POST /servidor-temporario/** para criar um novo servidor temporário.
        - 9.1.1- Na criação de um servidor temporário é necessário informar a pessoa completa.
        - 9.1.2- Na criação de um servidor temporário não é necessário passar uma lista de endereços completa, somente a lista de ids do endereço
   - 9.2 - Verifique os servidores temporários existentes: Utilize o serviço **GET /servidor-temporario/paginado/all** para verificar as servidores temporários que já existem de forma paginado e o serviço **GET /servidor-temporario/{pesId}** para buscar uma servidor temporário específico.
   - 9.3 - Utilize o serviço **PUT /servidor-temporario/{pesId}** para atualizar um servidor temporário.
   - 9.4 - Utilize o serviço **POST /servidor-efetivo/upload-fotos/{pesId}** para inserir uma ou mais fotos em um servidor temporário.
   - 9.5 -  Utilize o serviço **DELETE /servidor-temporario/{pesId}** para excluir um servidor temporário.
     
10. CRUD de Lotação
   - 10.1 - Utilize o serviço **POST /lotacao/** para criar uma nova lotação.
        - 10.1.1- Na criação de uma lotação não é necessário informar a pessoa completa, somente o id da pessoa.
        - 10.1.2- Na criação de uma lotação não é necessário informar a unidade completa, somente o id da unidade.
   - 10.2 - Verifique as lotações existentes: Utilize o serviço **GET /lotacao/paginado/all** para verificar as lotações que já existem de forma paginado e o serviço **GET /lotacao/{lotId}** para buscar uma lotação específica.
   - 10.3 - Utilize o serviço **PUT /lotacao/{lotId}** para atualizar uma lotação.
   - 10.4 - Utilize o serviço **DELETE /lotacao/{lotId}** para excluir uma lotação.



