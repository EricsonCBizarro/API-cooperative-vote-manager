# Cooperative Vote Manager
O Cooperative Vote Manager é um sistema de gerenciamento de votações cooperativas online, desenvolvido para permitir a criação, condução e contagem de votações entre associados.

# Funcionalidades
Cadastro de associados
Criação de tópicos de votação
Abertura, fechamento e contagem de votos em sessões de votação
Registro de votos por associados

# Requisitos
Java 11 ou superior (Recomendação 17)
Spring Boot
Banco de dados H2

# Configuração
Clone este repositório: git clone https://gitlab.com/ericsonbizarro/cooperativevotemanager
Configure as informações do banco de dados no arquivo application.properties

# Executando o Projeto
Para compilar o projeto, execute: ./mvnw clean install
Para executar os testes, utilize: ./mvnw test
Para executar o projeto, utilize: ./mvnw spring-boot:run
Acesse http://localhost:8080 em seu navegador

# Endpoints
## API/versão V1

GET /associates: Recupera a lista de associados

POST /associates: Cadastra um novo associado

GET /topics: Recupera a lista de tópicos de votação

POST /topics: Cria um novo tópico de votação

POST /voting-sessions: Cria uma nova sessão de votação

PUT /voting-sessions/{votingSessionId}/open: Abre uma sessão de votação

PUT /voting-sessions/{votingSessionId}/close: Fecha uma sessão de votação

GET /voting-sessions/{votingSessionId}/vote-count: Conta os votos de uma sessão de votação

POST /votes: Registra um novo voto

# Contato
Para mais informações ou dúvidas, entre em contato pelo email ericsonbizarro@gmail.com