# 📱 Book-phone

**Book-phone** é uma aplicação de agenda telefônica que permite:

- Cadastro e login de usuários
- Cadastro de novos contatos
- Edição de contatos
- Marcação de contatos como favoritos
- Remoção de contatos

O projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Frontend:** Angular
- **Backend:** Java com Spring
- **Banco de Dados:** MySQL

---

## 🚀 Como executar o projeto localmente

### Pré-requisitos

Certifique-se de ter instalado:

- Node.js
- Angular CLI (`npm install -g @angular/cli`)
- Java (versão compatível com Spring Boot)
- MySQL

### Configuração do Banco de Dados

1. Crie um banco de dados no MySQL com o nome esperado pelo backend.
2. No backend (Spring), abra o arquivo `application.properties` e configure as variáveis de conexão:

```
spring.datasource.url=jdbc:mysql://localhost:3306/NOME_DO_BANCO
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

---

### 🔧 Iniciando o projeto

#### 1. Inicie o Backend (Spring Boot)

Na pasta do projeto backend, execute:

```bash
./mvnw spring-boot:run
```

Ou, se estiver usando Gradle:

```bash
./gradlew bootRun
```

#### 2. Inicie o Frontend (Angular)

Na pasta do projeto frontend, execute:

```bash
npm install
npm start
```

A aplicação estará disponível em `http://localhost:4200`.

---

## 📌 Observações

- O projeto não possui link de demonstração online.
- Nenhuma API externa é utilizada.

---

## 📝 Licença

Este projeto está sob a licença MIT.
