# 🎬 Top Movie Catalog

## 🧾 Descrição

O **Top Movie Catalog** é uma aplicação web simples desenvolvida em **Java Spring Boot** para cadastro e listagem de filmes.  
O sistema permite **gerenciar filmes locais em memória** e também **buscar filmes externos** usando a API pública do **TMDb (The Movie Database)**.

O foco do projeto é demonstrar boas práticas de arquitetura, separação em camadas (Controller, Service, Repository, Model, DTO, Utils), regras de negócio e integração com APIs externas.


## 🚀 Funcionalidades

### 🎞️ Filmes Locais
- Cadastrar filmes (título, ano de lançamento, gênero)
- Listar todos os filmes
- Editar
- Excluir filmes

### 🌐 Filmes Externos (TMDb)
- Buscar filmes pelo título usando a API pública do TMDb
- Exibir título, ano e **primeiro gênero** do filme retornado
- Filmes externos **não são salvos no banco**, apenas exibidos na listagem

---

## 💻 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **Maven**
- **RestTemplate** (para consumo da API externa)
- **DTO Pattern** (para isolar entidade e resposta)
- **ResponseEntity** (para respostas HTTP padronizadas)

---

## ⚙️ Configuração e Execução

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/ViniVSO/top-movie.git
cd top-movie
```

### 2️⃣ Inserir sua chave TMDb

No arquivo src/main/resources/application.properties, adicione:
```bash
tmdb.api.key=SUA_CHAVE_AQUI
tmdb.api.url=https://api.themoviedb.org/3
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:moviesdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
```
🧩 Para obter a chave gratuita, acesse: https://developer.themoviedb.org

### 3️⃣ Executar o projeto
```bash
mvn spring-boot:run
```

### 4️⃣ Acessar

- API base: http://localhost:8080/api/movies
- H2 Console: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:moviesdb
- Usuário: sa
- Senha: (deixe em branco)

## 📡 Endpoints Principais

| Método | Endpoint | Descrição |
|--------|---------|-----------|
| GET    | /api/movies | Lista todos os filmes |
| GET    | /api/movies/{id} | Busca filme por ID |
| GET    | /api/movies/external?title={nome} | Busca filmes externos pelo título |
| POST   | /api/movies | Cadastra um novo filme |
| PUT    | /api/movies/{id} | Atualiza um filme |
| DELETE | /api/movies/{id} | Remove um filme |

## 🧠 O que foi aprendido

Durante o desenvolvimento deste projeto, foram aplicados e aprendidos:

- Criação de API REST com Spring Boot;
- Uso de ResponseEntity para controle de status HTTP;
- Atualização parcial de dados com BeanUtils.copyProperties;
- Implementação de regra de negócio dentro do service;
- Consumo de API externa com RestTemplate;
- Estruturação de projeto usando o padrão MVC + DTO;
- Validação e tratamento de dados nulos;
- Organização de código e boas práticas de arquitetura.

### ⚠️ Dificuldades encontradas

- Manter atualização parcial (PUT) sem sobrescrever valores nulos
- Lidar com resposta complexa da API TMDb (arrays de gêneros)
- Mapear corretamente o primeiro gênero do filme externo
- Resolver erros de conexão do driver H2 durante testes iniciais

### 🚧 Melhorias futuras

- Adicionar testes unitários e de integração com JUnit e Mockito
- Implementar um front-end em React ou Flutter Web consumindo essa API
- Melhorar tratamento global de exceções (@ControllerAdvice)
- Adicionar paginação e busca filtrada
- Armazenar filmes externos em cache local

### 🧩 Observações

- O sistema não persiste dados entre execuções (banco H2 em memória);
- Filmes externos não são salvos, apenas exibidos;
- Se o gênero não for retornado pela API, é exibido como "Desconhecido";
- Suporte a títulos com espaços e caracteres especiais.

### 👨‍💻 Autor

Vinicius Santos Oliveira

📧 Email: vinicius.sanoliver@gmail.com  
🔗 LinkedIn: https://www.linkedin.com/in/vinicius-oliveira-vso/
