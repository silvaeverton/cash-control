# 📘 Controle de Caixa - Planejamento do Projeto

## 📝 1. Ideia/Objetivo do Projeto

**Desenvolver um sistema completo (backend + frontend) para controle de caixa de uma loja. O sistema permitirá o registro de entradas (ex: vendas) e saídas (ex: despesas), com detalhamento da forma de pagamento (dinheiro, cartão, pix), descrição, foto da nota fiscal (em saídas) e cálculo automático do saldo em caixa. O sistema também permitirá filtros e exportação de relatórios mensais.**

## 🔍 2. Principais Funcionalidades Planejadas

- ✔️ Cadastro de Usuário (para o dono da loja e/ou funcionários autorizados)
- ✔️ Login com autenticação (JWT)

- ✔️ Registro de Entradas:

- Tipo de pagamento:`crédito`/ `débito` / `pix` / `dinheiro`

- Descrição

- Valor da entrada
- ✔️ Registro de Saídas:

- Tipo de pagamento: `crédito` / `débito` / `pix` / `dinheiro`

- Descrição

- Valor da saída

- Upload de foto da nota fiscal (imagem)
✔️ Cálculo automático do saldo do caixa
✔️ Visualização de saldo atual
✔️ Listagem e histórico de entradas/saídas separadamente
✔️ Filtros (por data, tipo de pagamento, valor)
✔️ Exportação de relatório mensal em PDF ou Excel
✔️ Dashboard resumido (opcional)
✔️ Deploy (produção/hospedagem)

## 🏗️ 3. Tecnologias Utilizadas

* Backend:

* Java 17

* Spring Boot 3.5

* Spring Security (JWT)

* Spring Data JPA

* MySQL (produção)

* H2 (testes)

* Lombok

* JUnit 5 / Mockito / MockMvc

* Swagger / OpenAPI


**Outros:** 

* AWS S3 ou Cloudinary (para armazenamento das fotos das notas fiscais)

* Jacoco (cobertura de testes)

## 📂 4. Modelagem Inicial de Dados (Simplificada)

**Usuário (User):**

- id

- nome

- email

**senha (criptografada)**

- Entrada (Entry):

- id

- valor

- descricao

- tipoPagamento (ENUM: `CREDITO`, `DEBITO`, `PIX`, `DINHEIRO`)

- dataRegistro

- usuarioId (FK)

**Saída (Exit):**

- id

- valor

- descricao

- tipoPagamento (ENUM)

- dataRegistro

- fotoNotaFiscalUrl (String)

- usuarioId (FK)

## 📌 Endpoints da API - Controle de Caixa

| Método   | Endpoint                          | Descrição                                               |
|----------|-----------------------------------|---------------------------------------------------------|
| POST     | `/income`                         | Criar nova entrada de caixa (venda, etc.)               |
| GET      | `/income`                         | Listar todas as entradas                                |
| GET      | `/income/{idIncome}`              | Buscar entrada específica por ID                        |
| GET      | `/income/user/{idUser}`           | Buscar entradas pelo Id do usuário                      |
| GET      | `/income/payment?method=PIX`      | Buscar entradas pelo metodo de pagamento                |
| GET      |`/income/by-month?year=2025&month=6`| Buscar por mês                                          |
| DELETE   | `/income/{idIncome}`               | Deletar uma entrada                                     |
| POST     | `/expense`                      | Criar nova saída de caixa (despesa, etc.)                |
| GET      | `/expense`                      | Listar todas as saídas                                  |
| GET      | `/exoense/{id}`                 | Buscar saída específica por ID                          |
| GET      | `/expense/user/{idUser}`           | Buscar entradas pelo Id do usuário                      |
| GET      | `/expense/payment?method=PIX`      | Buscar entradas pelo metodo de pagamento                |
| GET      |`/expense/by-month?year=2025&month=6`| Buscar por mês                                          |
| DELETE   | `/expense/{id}`                 | Deletar uma saída                                       |
| GET      | `/cash-register`                | Consultar saldo atual do caixa                          |
| GET      | `cash-register/cash`           | Gerar relatório resumido do mês (PDF ou JSON)            |
| POST     | `/user`                    | Criar novo usuário                                      |
| GET      | `/user/{id}`               | Consultar dados do usuário                              |
| PUT      | `/user/{id}`               | Atualizar usuário                                       |
| PATH     | `/user/{id}`               | inativa user usuário                                         |

## 📌 6. MVP (Produto Mínimo Viável)

- ✔️ Cadastro e login de usuário
- ✔️ CRUD completo de entradas e saídas
- ✔️ Cálculo automático do saldo
- ✔️ Listagem simples de entradas/saídas
- ✔️ Upload de imagem da nota fiscal
- ✔️ Swagger documentando toda API


## ✅ 7. Testes Planejados

* Testes unitários de services com Mockito

* Testes de integração de controllers com MockMvc

* Testes de cobertura Jacoco

## 🧩 8. Possíveis Melhorias Futuras

- Gráfico financeiro (Dashboard)

- Notificações automáticas (ex: quando o saldo estiver abaixo de um valor crítico)

- Multi-usuário (ex: vários funcionários)

- Backup automático em nuvem

- Integração com APIs de pagamento

## 🌍 9. Deploy Planejado

- Backend: Render, Railway ou AWS Elastic Beanstalk

- Banco de dados: MySQL em AWS RDS ou Railway

## 👨‍💻 Autor

**Everton Silva Java Backend Developer | Spring Enthusiast | System Integrator**

