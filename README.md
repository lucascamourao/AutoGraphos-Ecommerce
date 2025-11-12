## AutoGraphos - E-Commerce de Itens Autografados

O AutoGraphos é uma aplicação web desenvolvida em Java (JSP e Servlets) que simula um e-commerce voltado à venda de produtos autografados por personalidades famosas.
O sistema permite o cadastro de clientes, o listagem de produtos e a navegação entre páginas dinâmicas renderizadas com JSP.

A aplicação foi desenvolvida para a cadeira de Programação para Web I 2025.2 (SMD0052), ministrada pelo Prof. Dr. Leonardo Oliveira Moreira.

### 🛠️ Tecnologias Utilizadas
- Java EE / Jakarta EE
- JSP (JavaServer Pages)
- Servlets
- HTML / CSS / JavaScript
- NetBeans IDE
- Servidor Apache Tomcat

### ⚙️ Requisitos do Ambiente
- Java 25 (JDK 25) — versão mais recente da plataforma Java.
- Apache Tomcat 10.0 ou superior — servidor de aplicações compatível com Jakarta EE 10+.

Após instalar o Tomcat:
1. No NetBeans, acesse Tools → Servers → Add Server.
2. Selecione Apache Tomcat e informe o caminho da instalação.
3. Associe o servidor ao projeto AutoGraphos-Backend.
4. Certifique-se de que o Tomcat está marcado como servidor padrão antes de rodar o projeto.

### 💡 Funcionalidades Principais
- Página inicial com listagem de produtos
- Cadastro de cliente via formulário
- Separação de camadas MVC: controle, modelo, e visão (.jsp)
- Recursos estáticos (CSS e JS) servidos diretamente pela aplicação
