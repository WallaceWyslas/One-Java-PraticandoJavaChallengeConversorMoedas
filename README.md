# Conversor de Moedas - ONE - Alura & Oracle

## 📝 Descrição do Projeto

Este projeto é um Conversor de Moedas desenvolvido como parte do Programa ONE (Oracle Next Education) em parceria com a Alura. A aplicação permite ao usuário converter valores entre diferentes moedas, utilizando taxas de câmbio obtidas em tempo real através da API [ExchangeRate-API](https://www.exchangerate-api.com/).

O conversor oferece uma interface de texto simples e interativa no console, onde o usuário pode escolher a moeda de origem, a moeda de destino e o valor a ser convertido.

## ✨ Funcionalidades Principais

- **Busca de Taxas de Câmbio em Tempo Real**: Conecta-se à ExchangeRate-API para obter as taxas de câmbio mais recentes.
- **Interface de Console Interativa**: Permite ao usuário:
    - Visualizar uma lista de moedas populares para conversão.
    - Digitar o código da moeda de origem (ex: USD, BRL).
    - Digitar o código da moeda de destino (ex: EUR, ARS).
    - Informar o valor a ser convertido.
    - Visualizar o resultado da conversão formatado.
    - Realizar múltiplas conversões em uma única execução.
    - Sair do programa quando desejar.
- **Suporte a Diversas Moedas**: Embora sugira moedas populares, permite a conversão entre quaisquer moedas suportadas pela API, desde que o usuário forneça os códigos corretos.
- **Tratamento de Erros**: Inclui validações básicas para entradas do usuário e tratamento de possíveis erros na comunicação com a API ou na obtenção das taxas.
- **Segurança da API Key**: A chave da API é armazenada em um arquivo de configuração (`config.properties`) que não é versionado no Git, garantindo sua proteção.

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java Development Kit (JDK) instalado (versão 11 ou superior, devido ao uso do `java.net.http.HttpClient`).
- IntelliJ IDEA (ou outra IDE Java de sua preferência).
- Biblioteca GSON (ex: `gson-2.11.0.jar`).
- Uma chave de API válida da [ExchangeRate-API](https://www.exchangerate-api.com/) (o plano gratuito é suficiente).

### Passos para Configuração e Execução

1.  **Clone o Repositório (ou baixe os arquivos do projeto):**
    ```bash
    # Se estiver usando Git
    # git clone [URL_DO_SEU_REPOSITORIO_AQUI]
    # cd [NOME_DA_PASTA_DO_PROJETO]
    ```

2.  **Configure a API Key:**
    *   Crie o diretório `src/main/resources` na raiz do seu projeto, caso ainda não exista.
    *   Dentro de `src/main/resources`, crie um arquivo chamado `config.properties`.
    *   Adicione sua API key ao arquivo `config.properties` da seguinte forma:
        ```properties
        api.key=SUA_CHAVE_API
        ```
        Substitua `SUA_CHAVE_API` pela sua chave de API válida.

3.  **Adicione a Biblioteca GSON ao Projeto no IntelliJ IDEA:**
    *   Crie uma pasta chamada `lib` na raiz do seu projeto (ao lado da pasta `src`).
    *   Coloque o arquivo `.jar` da biblioteca GSON (ex: `gson-2.11.0.jar`) dentro desta pasta `lib`.
    *   No IntelliJ IDEA:
        1.  Vá em `File > Project Structure...`.
        2.  Selecione `Modules` no painel esquerdo.
        3.  No painel direito, vá para a aba `Dependencies`.
        4.  Clique no ícone `+` (Adicionar) e escolha `JARs or directories...`.
        5.  Navegue até a pasta `lib` do seu projeto, selecione o arquivo `.jar` do GSON e clique em `OK`.
        6.  Certifique-se de que o escopo está como `Compile` e clique em `Apply` e depois em `OK`.

4.  **Configure a Pasta de Recursos no IntelliJ IDEA (se necessário):**
    *   No IntelliJ IDEA:
        1.  Vá em `File > Project Structure...`.
        2.  Selecione `Modules` no painel esquerdo.
        3.  No painel direito, vá para a aba `Sources`.
        4.  Encontre a pasta `src/main/resources`.
        5.  Selecione-a e clique no botão/opção `Resources` para marcá-la como uma pasta de recursos (geralmente fica azul).

5.  **Execute a Aplicação:**
    *   Abra a classe `com.conversordemoedas.Main.java` no IntelliJ IDEA.
    *   Clique com o botão direito do mouse dentro do editor de código e selecione `Run 'Main.main()'` (ou clique no ícone de play verde ao lado do método `main`).
    *   A interface do conversor de moedas aparecerá no console.

## 🛠️ Tecnologias Utilizadas

- **Java 11+**: Linguagem de programação principal.
    - `java.net.http.HttpClient`: Para realizar requisições HTTP à API.
    - `java.util.Scanner`: Para interação com o usuário no console.
- **GSON**: Biblioteca do Google para converter objetos Java para/de JSON.
- **ExchangeRate-API**: API externa para obter taxas de câmbio em tempo real.
- **IntelliJ IDEA**: Ambiente de Desenvolvimento Integrado (IDE) recomendado.
- **Git & GitHub**: Para controle de versão e hospedagem do projeto (opcional para execução local, mas recomendado para desenvolvimento).

---

_Projeto desenvolvido como parte do Desafio Conversor de Moedas do Programa ONE - Oracle + Alura_

