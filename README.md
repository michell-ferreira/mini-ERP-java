# Mini ERP em Java

![Java](https://img.shields.io/badge/Language-Java-orange)
![Status](https://img.shields.io/badge/Status-Concluído-green)
![Platform](https://img.shields.io/badge/Platform-Console-lightgrey)

Projeto de um sistema de console simulando um Mini ERP (Enterprise Resource Planning), desenvolvido para aplicar e demonstrar conceitos fundamentais de Programação Orientada a Objetos (POO) com Java.

---

## 🎥 Demonstração em Vídeo

**(Assim que gravar, edite esta linha e o link abaixo)**

[Assista a uma rápida demonstração do sistema em funcionamento](https://www.youtube.com/watch?v=SEU_LINK_AQUI_DO_SEU_VIDEO)

---

## 📜 Sobre o Projeto

Este projeto foi concebido como um desafio prático para solidificar os pilares da POO. A aplicação simula um ambiente de gestão comercial, permitindo o cadastro de produtos (perecíveis e não-perecíveis), o cadastro de clientes e o registro de vendas, culminando na implementação completa do ciclo CRUD (Create, Read, Update, Delete) e de lógicas de negócio como relatórios e anulação de registros.

O foco principal foi a criação de um código limpo, bem estruturado em pacotes, e a aplicação correta de padrões de design para garantir um sistema robusto e de fácil manutenção.

## ✨ Funcionalidades

* **Gestão de Produtos:**
    * Cadastro de produtos com distinção entre perecíveis (com data de validade) e não-perecíveis (com garantia).
    * Listagem completa de todos os produtos cadastrados.
    * Edição de informações dos produtos (nome e preço) após o cadastro.
* **Gestão de Clientes:**
    * Cadastro e listagem de clientes.
* **Gestão de Vendas:**
    * Registro de vendas associadas a um cliente, com um carrinho de compras para múltiplos produtos.
    * Listagem detalhada de todas as vendas, incluindo cliente, produtos, data, valor total e status.
    * **Soft Delete:** Funcionalidade para cancelar uma venda, alterando seu status para `CANCELADO` sem apagar o registro do histórico, o que permite a manutenção de dados históricos para análises futuras.
* **Relatórios:**
    * Geração de um relatório simples que calcula e exibe o valor total acumulado apenas das vendas `ATIVAS`.

## 💡 Destaque da Implementação: Soft Delete

Uma das implementações que mais me orgulho neste projeto é o conceito de **Soft Delete** nas vendas. Em vez de remover completamente um registro de venda ao "cancelá-lo", o sistema altera apenas o seu `StatusVenda` para `CANCELADO`. Isso garante que:

* **Integridade Histórica:** Todas as vendas permanecem no registro, permitindo auditorias e análises futuras.
* **Simplicidade:** O processo de cancelamento é seguro e reverte facilmente caso necessário.
* **Inteligência de Relatórios:** Relatórios como o de "Vendas Ativas" filtram inteligentemente esses registros, garantindo que apenas as vendas de interesse sejam consideradas, sem perder o histórico completo.

Esta abordagem demonstra um entendimento prático de como lidar com dados em cenários de negócio do mundo real.

## 🛠️ Tecnologias e Conceitos Aplicados

Este projeto foi uma oportunidade para aplicar na prática os seguintes conceitos:

* **Java SE:** Utilização da linguagem Java e suas bibliotecas padrão (`java.time`, `java.util`).
* **Programação Orientada a Objetos (POO):**
    * **Abstração:** Uso de classes `abstract` (`Produto`) para criar um modelo base.
    * **Herança:** Especialização da classe `Produto` em `ProdutoPerecivel` e `ProdutoNaoPerecivel`.
    * **Polimorfismo:** Aplicação no método `exibirDetalhes()`, onde cada subclasse de `Produto` tem sua própria implementação.
    * **Encapsulamento:** Proteção dos atributos com `private` e `protected` e acesso controlado através de `getters` e `setters`.
* **Estrutura de Dados:** Uso intensivo de `ArrayList` para gerenciar as listas de produtos, clientes e vendas em memória.
* **Enums:** Utilização de `enum` para garantir a integridade de dados em campos de estado, como `StatusVenda`.
* **Programação Defensiva:**
    * Tratamento de exceções com blocos `try-catch` para lidar com entradas inválidas do usuário (`NumberFormatException`, etc.).
    * Validação de regras de negócio (ex: não permitir preços negativos, verificar datas de validade, validar campos obrigatórios).
* **Boas Práticas de Código:**
    * Organização do projeto em pacotes (`application`, `entities`, `enums`).
    * Foco em nomes de variáveis e métodos claros e expressivos.
    * Uso de `LocalDate` para manipulação de datas.

## 🚀 Como Executar o Projeto

Este projeto foi desenvolvido para ser compilado e executado via linha de comando.

```bash
# 1. Clone o repositório
git clone [https://github.com/michell-ferreira/mini-ERP-java](https://github.com/michell-ferreira/mini-ERP-java)

# 2. Navegue até a pasta src
cd mini-ERP-java/src

# 3. Compile todos os arquivos .java
# (Este comando compila todos os arquivos de todos os pacotes de uma vez)
javac application/Main.java entities/*.java enums/*.java

# 4. Execute a classe Main
# (Note que usamos o nome completo da classe, com pontos)
java application.Main
```

## ✒️ Autor

**Michell Ferreira**

* **GitHub:** [michell-ferreira](https://github.com/michell-ferreira)
* **LinkedIn:** https://www.linkedin.com/in/ferreira-michel/

---
