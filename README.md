# Sistema de Supermercado em Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-GUI-blue?style=for-the-badge)
![MVC](https://img.shields.io/badge/Architecture-MVC-green?style=for-the-badge)
![SQL](https://img.shields.io/badge/Database-SQL-lightgrey?style=for-the-badge)

## Sobre o Projeto

Projeto desenvolvido durante a disciplina de Projeto e Desenvolvimento de Sistemas do Técnico Integrado em Informática (IFSC).

O sistema simula um ambiente de supermercado, permitindo o gerenciamento de produtos, clientes e vendas.
Desenvolvido em Java, utilizando Programação Orientada a Objetos (POO) e arquitetura MVC (Model-View-Controller).  
Interface gráfica construída com Java Swing, utilizando MigLayout para organização dos componentes, além de integração com banco de dados SQL.

---

## Funcionalidades

- Cadastro de produtos  
- Edição e remoção de produtos  
- Controle de estoque  
- Cadastro de clientes  
- Sistema de vendas/pedidos  
- Login de usuários e administradores
- Persistência de dados em banco SQL  

---

## Tecnologias Utilizadas

- Java (POO)  
- Java Swing (GUI)  
- MigLayout (layout manager)  
- SQL (Banco de dados relacional)  
- Arquitetura MVC  

---

## Arquitetura MVC

O projeto segue o padrão MVC, dividido em:

- Model: Responsável pelas entidades e regras de negócio (Produto, Cliente, Venda etc.)
- View: Telas do sistema desenvolvidas com Java Swing
- Controller: Intermedia a comunicação entre Model e View

---

## Banco de Dados

O sistema utiliza um banco de dados SQL para persistência dos dados.

Tabelas principais:

- usuarios  
- produtos  
- carrinho  

---

## Interface do Sistema

### Tela Inicial
![Tela Inicial](./images/Inicial.png)

### Catálogo
![Catalogo](./images/Catalogo.png)

### Cadastro de Produtos
![CadastroProdutos](./images/CadastroProdutos.png)

### Edição de Produtos
![EdicaoProdutos](./images/EdicaoProdutos.png)

### Carrinho
![Carrinho](./images/Carrinho.png)

### Emissão de Nota Fiscal
![NotaFiscal](./images/NotaFiscal.png)

---

## Observação

Este projeto tem fins acadêmicos e foi desenvolvido com foco em aprendizado de:

- POO
- MVC
- Desenvolvimento de aplicações desktop
- Integração com banco de dados
- Interface gráfica com Java Swing e MigLayout
