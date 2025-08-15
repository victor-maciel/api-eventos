#  API de Gerenciamento de Eventos

Esta é uma API RESTful desenvolvida em **Java com Spring Boot**, que permite o **gerenciamento de eventos**, incluindo **cadastro de eventos, upload de imagens para o Amazon S3**, filtro por critérios, e **vinculação de cupons de desconto**.

---

##  Funcionalidades

-  **Criar eventos** (com ou sem imagem)
-  **Upload automático de imagens** para o Amazon S3
-  **Listar e filtrar eventos** por título, cidade, UF e data
-  **Cadastrar cupons** associados a eventos
-  **Consultar detalhes de um evento**, incluindo cupons válidos

---

## Tecnologias Utilizadas

- Java 21+
- Spring Boot
- Spring Data JPA
- Amazon S3 (para upload de imagens)
- Banco de dados relacional: PostgreSQL
- Maven

---

##  Endpoints principais

###  Evento

| Método | Endpoint | Descrição |
|--------|----------|------------|
| `POST` | `/api/evento` | Cria um novo evento com dados do formulário `multipart/form-data` |
| `GET` | `/api/evento` | Lista eventos pendentes com paginação |
| `GET` | `/api/evento/filtro` | Lista eventos com filtros por cidade, UF, data e título |
| `GET` | `/api/evento/{id}` | Retorna os detalhes completos de um evento, incluindo cupons válidos |

###  Cupom

| Método | Endpoint | Descrição |
|--------|----------|------------|
| `POST` | `/api/cupom/evento/{eventoId}` | Adiciona um cupom ao evento correspondente |

---


---

##  Upload de Imagens no Amazon S3

O sistema realiza upload automático de imagens usando o cliente `AmazonS3`, com as credenciais configuradas via `application.properties`:

```properties
aws.accessKey=SUAS_CREDENCIAIS
aws.secretKey=SUAS_CREDENCIAIS
aws.region=us-east-1
aws.bucket.name=nome-do-seu-bucket


