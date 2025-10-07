 API para Barbearia - Clube dos Barbas

Este repositório contém o código-fonte do backend para a aplicação de gestão de barbearia "Clube dos Barbas". A API foi desenvolvida para ser robusta, escalável e segura, servindo como a base para uma aplicação front-end completa (web ou mobile).

-----

## 🚀 Funcionalidades Implementadas

A API oferece um conjunto completo de funcionalidades para a gestão de uma barbearia moderna:

  * **Gestão de Entidades (CRUD):**
      * **Barbeiros:** Cadastro, listagem, atualização e exclusão (com validação de `username` e `celular` únicos).
      * **Clientes:** Cadastro (com validação de `telefone` único), listagem, atualização e exclusão.
      * **Serviços:** Cadastro, listagem, atualização e exclusão de serviços oferecidos.
      * **Estabelecimento:** Gestão dos dados da barbearia (nome, horários).
  * **Sistema de Agendamento Avançado:**
      * **Solicitação de Agendamento:** Clientes podem solicitar horários, que ficam pendentes de confirmação.
      * **Gestão pelo Barbeiro:** Barbeiros podem confirmar ou cancelar agendamentos.
      * **Busca com Filtros:** Endpoint para buscar agendamentos por barbeiro, cliente e/ou intervalo de datas.
      * **Regras de Negócio:** Validações para impedir agendamentos duplicados, fora do horário de funcionamento ou sem antecedência mínima.
  * **Agenda do Barbeiro:**
      * **Horários de Trabalho:** Endpoint para definir os dias e horas de trabalho de cada barbeiro.
      * **Bloqueio de Horários:** Funcionalidade para o barbeiro bloquear períodos na agenda (ex: almoço, compromissos).
  * **Módulo Financeiro:**
      * **Livro-Caixa:** Registo de todas as movimentações financeiras (`ENTRADA` ou `DESPESA`).
      * **Relatórios:** Geração de relatórios financeiros diários e mensais, com cálculo de totais e lucro.
  * **Chat:**
      * **Troca de Mensagens:** Endpoints para enviar e receber mensagens entre clientes e barbeiros.
  * **Segurança e Autenticação:**
      * **Endpoint de Login:** Autenticação de `Barbeiros` com `username` e `senha`, devolvendo um token JWT.
      * **Criptografia de Senhas:** As senhas são guardadas de forma segura no banco de dados usando BCrypt.
  * **Padronização e Boas Práticas:**
      * **Respostas Padronizadas:** Todas as respostas da API seguem um formato JSON consistente para sucesso e erro.
      * **Versionamento:** Todos os endpoints estão sob o prefixo `/api/v1`.
      * **Documentação Automática:** A API está documentada com Swagger (OpenAPI).

-----

## 🛠️ Tecnologias Utilizadas

  * **Linguagem:** Java 17 (LTS)
  * **Framework:** Spring Boot 3.3.1
  * **Acesso a Dados:** Spring Data JPA / Hibernate
  * **Segurança:** Spring Security
  * **Banco de Dados:** PostgreSQL
  * **Ambiente de Desenvolvimento:** Docker e Docker Compose
  * **Gestão de Projeto:** Maven
  * **Documentação:** SpringDoc OpenAPI (Swagger)

-----

## ⚙️ Como Executar o Projeto Localmente

Para executar esta API na sua máquina, siga os passos abaixo.

### **Pré-requisitos:**

  * [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) instalado
  * [Docker](https://www.docker.com/products/docker-desktop/) instalado e em execução

### **Passos para a Execução:**

1.  **Clone o Repositório:**

    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd api
    ```

2.  **Inicie o Banco de Dados:**
    Na raiz do projeto, execute o Docker Compose para iniciar o container do PostgreSQL em segundo plano.

    ```bash
    docker-compose up -d
    ```

3.  **Execute a Aplicação:**
    Use o Maven Wrapper para compilar e iniciar a aplicação Spring Boot.

    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Pronto\!**
    A sua API estará a correr em `http://localhost:8080`. Todos os endpoints estão disponíveis sob o prefixo `/api/v1` (ex: `http://localhost:8080/api/v1/swagger-ui.html`).

-----

## 📚 Documentação da API (Swagger)

Com a aplicação em execução, você pode aceder à documentação interativa do Swagger para ver e testar todos os endpoints disponíveis.

  * **URL do Swagger UI:** [http://localhost:8080/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui.html)
