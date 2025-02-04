📌 Sobre o Autor
Meu nome é Lucas Eduardo, estou no 6° período do curso de Tecnologia em Sistemas para Internet pela UTFPR. 
Este projeto foi desenvolvido como parte do meu Trabalho de Conclusão de Curso (TCC).

🩺 Grafo de Conhecimento de Doenças e Sintomas
Este projeto tem como objetivo extrair, estruturar e visualizar relações entre doenças e sintomas por meio de um grafo de conhecimento interativo.
Os dados são obtidos de uma base pública da Universidade de Columbia por meio de Web Scraping, processados no backend e exibidos utilizando uma visualização do tipo Radial Tree Graph com D3.js.

🔧 Tecnologias Utilizadas
O projeto utiliza diversas tecnologias para Web Scraping, processamento de dados e visualização gráfica:

Backend:

Java 11+ – Linguagem principal do projeto
Spring Boot – Para construção da API REST
Jsoup – Para Web Scraping dos dados
JGraphT – Para manipulação e estruturação do grafo
Gson – Para conversão dos dados em JSON
Maven – Gerenciador de dependências

Frontend:
D3.js – Para visualização interativa do grafo
HTML/CSS/JavaScript – Para a interface do usuário

📌 Pré-requisitos
Antes de rodar o projeto, certifique-se de ter os seguintes programas instalados:

Java 11+ – Para rodar o backend
Maven – Para gerenciar dependências
Git – Para clonar o repositório
Node.js (opcional) – Para rodar um servidor de desenvolvimento do frontend

🚀 Como Executar o Projeto
Siga os passos abaixo para rodar o backend e visualizar o grafo interativo no navegador.

1️⃣ Clonar o Repositório
Antes de tudo, clone este repositório no seu computador:

git clone https://github.com/lalmeida99/ProjetoTCC2_GRAFO.git
cd ProjetoTCC2_GRAFO
2️⃣ Backend - Inicializando a API
Certifique-se de ter o Java 11+ e Maven instalados. Para rodar o backend, execute os comandos abaixo:

mvn clean install
mvn spring-boot:run
Se tudo estiver correto, a API estará disponível em:

http://localhost:8080/api/hierarchy

3️⃣ Frontend - Como Acessar a Visualização
Com o backend rodando, abra o navegador e acesse:

http://localhost:8080/index.html

Grato.










