<%@page import="model.usuario.Usuario"%>
<%@page import="model.categoria.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>E-Commerce</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />

  </head>

  <body>
    <header>
      <a href="${pageContext.request.contextPath}/index.jsp" class="logo-photo">
        <div>
          <img
            src="<%= request.getContextPath() %>\assets\images\icon_ecommerce_new.png"
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
      <div class="form-page-container">
        <h2>Atualizar uma Categoria Existente</h2>
        <div class="form-container">
          <%
            Categoria categoria = (Categoria) request.getAttribute("categoria");
          %>
          <form action="<%= request.getContextPath() %>/admin/AtualizarCategoria" method="post">
            <input type="hidden" name="id" value="<%= categoria.getId() %>" />

            <div class="form-group">
              <label for="nome-categoria">Nome da Categoria</label>
              <input
                type="text"
                id="nome-categoria"
                name="nome"
                placeholder="Entre com o nome da categoria"
                value="<%= categoria.getNome() %>"
              />
            </div>

            <button type="submit" class="button">Atualizar</button>
          </form>

          <div style="margin-top: 12px; text-align: center;">
            <a href="<%= request.getContextPath() %>/admin/ListarCategoria" class="button" style="text-decoration: none;">Voltar</a>
          </div>

          <% if (request.getAttribute("mensagem") != null) { %>
            <h3><%= request.getAttribute("mensagem") %></h3>
          <% } %>
        </div>
      </div>
    </main>


    <footer>
      <p>&copy; 2025 E-Commerce WebDev &ndash; Name</p>
    </footer>

    <script src="<%= request.getContextPath() %>/js/app.js"></script>
    <script src="../js/form-validation.js" type="text/javascript"></script>
    
  </body>
</html>
