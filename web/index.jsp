<%-- 
    Document   : index
    Created on : 30 de out. de 2025, 17:06:49
    Author     : Leonardo Oliveira Moreira
--%>
<%@ page import="model.usuario.Usuario" %>
<%@page import="model.produto.Produto"%>
<%@page import="model.carrinho.CarrinhoItem"%>
<%@page import="java.util.List"%>
<%@page import="utils.Utils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>E-Commerce</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />

    <script src="<%= request.getContextPath() %>/js/app.js" defer></script>

  </head>

  <body>
    <header>
      <a href="<%= request.getContextPath()%>/Inicio" class="logo-photo">
        <div>
          <img
            src="${pageContext.request.contextPath}/assets/images/icon_ecommerce_new.png"
            alt="My Logo"
            height="32"
          />
        </div>
      </a>

      <nav>
        <a href="${pageContext.request.contextPath}/pages/account-info.jsp">
          <button id="account-button" class="button-header">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              class="size-6"
            >
              <path
                fill-rule="evenodd"
                d="M7.5 6a4.5 4.5 0 1 1 9 0 4.5 4.5 0 0 1-9 0ZM3.751 20.105a8.25 8.25 0 0 1 16.498 0 .75.75 0 0 1-.437.695A18.683 18.683 0 0 1 12 22.5c-2.786 0-5.433-.608-7.812-1.7a.75.75 0 0 1-.437-.695Z"
                clip-rule="evenodd"
              />
            </svg>
          </button>
        </a>

        <button id="theme-toggle-button" class="button-header">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor"
            class="size-6"
          >
            <path
              fill-rule="evenodd"
              d="M9.528 1.718a.75.75 0 0 1 .162.819A8.97 8.97 0 0 0 9 6a9 9 0 0 0 9 9 8.97 8.97 0 0 0 3.463-.69.75.75 0 0 1 .981.98 10.503 10.503 0 0 1-9.694 6.46c-5.799 0-10.5-4.7-10.5-10.5 0-4.368 2.667-8.112 6.46-9.694a.75.75 0 0 1 .818.162Z"
              clip-rule="evenodd"
            />
          </svg>
        </button>

        <a href="${pageContext.request.contextPath}/pages/sign-up-page.jsp">
          <button class="header-button" aria-label="Sign-Up">Cadastro</button>
        </a>

        <a href="${pageContext.request.contextPath}/pages/sign-in-page.jsp">
          <button class="header-button" aria-label="Sign-in">Login</button>
        </a>
      </nav>
    </header>

    <main>
      <div class="search-container">
        <input type="text" id="search-input" placeholder="Seach for title..." />

        <button class="button-header" id="search-button">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor"
            class="size-6"
          >
            <path
              fill-rule="evenodd"
              d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z"
              clip-rule="evenodd"
            />
          </svg>
        </button>
      </div>

      <div class="product-container">
        <!-- All products go here -->
        <!-- All Images 4:5-->

        <%
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null && !produtos.isEmpty()) {
        %>

        <div class="product-container">

            <%
                for (Produto p : produtos) {
            %>

            <a href="AdicionarItemCarrinho?produtoId=<%= p.getId() %>&quantidade=1" class="product-details-card">
                <div class="product-card">
                    <img 
                        src="MostrarFotoProduto?id=<%= p.getId() %>" 
                        alt="Imagem do produto"
                    />

                    <h3 class="product-description">
                        <%= p.getDescricao() %>
                    </h3>

                    <p class="product-info">
                        Preço: R$ <%= p.getPreco() %><br/>
                        Quantidade: <%= p.getQuantidade() %>
                    </p>
                </div>
            </a>

            <%
                }
            %>

        </div>
      </div>
        <%
            }
            List<CarrinhoItem> itensCarrinhoCompras =
                (List<CarrinhoItem>) request.getAttribute("itensCarrinhoCompras");

            if (itensCarrinhoCompras != null && !itensCarrinhoCompras.isEmpty()) {
                double totalCarrinhoCompras = 0;
        %>

        <hr/>
        <section class="cart-container">
            <h2 class="cart-title">Carrinho de Compras</h2>
            <table class="cart-table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Produto</th>
                        <th>Descrição</th>
                        <th>Preço</th>
                        <th>Qtd</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int i = 1;
                        for (CarrinhoItem item : itensCarrinhoCompras) {
                    %>
                    <tr>
                        <td><%= i++ %></td>
                        <td>
                            <% if (item.getProduto().getFoto() != null
                                   && !item.getProduto().getFoto().trim().isEmpty()) { %>
                                <img
                                    src="<%= request.getContextPath() %>/MostrarFotoProduto?id=<%= item.getProduto().getId() %>"
                                    class="product-img-list"
                                    alt="Produto"
                                />
                            <% } %>
                        </td>
                        <td><%= item.getProduto().getDescricao() %></td>
                        <td>
                            R$ <%= Utils.formatarMoeda(item.getProduto().getPreco()) %>
                        </td>
                        <td class="cart-qtd">
                            <a
                                class="qtd-btn"
                                href="<%= request.getContextPath() %>/DiminuirItemCarrinho?produtoId=<%= item.getProduto().getId() %>"
                            >−</a>

                            <span class="qtd-value"><%= item.getQuantidade() %></span>

                            <a
                                class="qtd-btn"
                                href="<%= request.getContextPath() %>/AdicionarItemCarrinho?produtoId=<%= item.getProduto().getId() %>&quantidade=1"
                            >+</a>
                        </td>
                        <td>
                            <a
                                href="<%= request.getContextPath() %>/RemoverItemCarrinho?produtoId=<%= item.getProduto().getId() %>"
                                class="cart-remove-btn"
                            >
                                Remover
                            </a>
                        </td>
                    </tr>
                    <%
                            totalCarrinhoCompras +=
                                item.getQuantidade() * item.getProduto().getPreco();
                        }
                    %>
                </tbody>
            </table>
            <div class="cart-total">
                Total: R$ <%= Utils.formatarMoeda(totalCarrinhoCompras) %>
            </div>
            <%
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                boolean usuarioLogado = (usuario != null);
            %>
            <div class="cart-actions">
                <% if (usuarioLogado) { %>
                    <a href="<%= request.getContextPath() %>/FinalizarVenda"
                       class="cart-buy-btn">
                        Comprar
                    </a>
                <% } else { %>
                    <span class="cart-buy-btn disabled">
                        Comprar
                    </span>
                <% } %>
            </div>
        </section>
        <%
            }
        %>

          

    </main>

    <footer>
      <p>&copy; 2025 E-Commerce WebDev &ndash; Name</p>
    </footer>
  </body>
</html>

