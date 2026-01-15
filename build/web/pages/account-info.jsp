<%@ page import="model.usuario.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Info</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />
    <script src="<%= request.getContextPath() %>/js/app.js" defer></script>
  </head>
  <body>
    <header>
      <a href="<%= request.getContextPath()%>/Inicio" class="logo-photo">
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

    <div class="form-page-container">
      <h2>Informações de Conta</h2>

      <div class="form-container">
            <%
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario != null) {
            %>
              <h3>Olá, <%= usuario.getNome() %>!</h3>
              <h4>Você é um <%= usuario.getAdministrador() ? "Administrador" : "Cliente" %>.</h4>
            <%
            } else {
            %>
              <h3>Usuário não está logado.</h3>
            <%
            }
            %>
        <nav>
          <ul class="account-actions-list">
          
          <% if (usuario.getAdministrador()) {
            %>
          <a href="${pageContext.request.contextPath}/pages/ver-compras-admin-page.jsp">
            <div>
              <button class="button-account-info">Gerenciar Vendas</button>
            </div>
          </a>
          <div>
            <a href="${pageContext.request.contextPath}/admin/ListarProduto">
                <button class="button-account-info" aria-label="Update Info">Produtos</button>
            </a>
          </div>
          <div>
            <a href="${pageContext.request.contextPath}/admin/ListarCategoria">
                <button class="button-account-info" aria-label="Update Info">Categorias</button>
            </a>
          </div>
          <div>
                <form action="${pageContext.request.contextPath}/admin/RelatorioCompras"
                      method="get"
                      style="display: inline;">

                    <label for="dataInicio">Início:</label>
                    <input type="date" id="dataInicio" name="dataInicio" required>

                    <label for="dataFim">Fim:</label>
                    <input type="date" id="dataFim" name="dataFim" required>

                    <button class="button-account-info" type="submit">
                        Relatório de Compras
                    </button>

                </form>
          </div>
          <div>
            <a href="${pageContext.request.contextPath}/admin/RelatorioProdutosFaltantes">
                <button class="button-account-info" aria-label="Update Info">Relatório de Produtos Faltantes</button>
            </a>
          </div>
          <div>
                <form action="${pageContext.request.contextPath}/admin/RelatorioVendas"
                      method="get"
                      style="display: inline;">

                    <label for="dataInicio">Início:</label>
                    <input type="date" id="dataInicio" name="dataInicio" required>

                    <label for="dataFim">Fim:</label>
                    <input type="date" id="dataFim" name="dataFim" required>

                    <button class="button-account-info" type="submit">
                        Relatório de Vendas
                    </button>

                </form>
          </div>
          <% 
           } else {
          %>
          <a href="${pageContext.request.contextPath}/pages/my-purchases-page.jsp">
            <div>
              <button class="button-account-info">Minhas Compras</button>
            </div>
          </a>
          <% 
           } 
          %>            
          <div>
            <a href="${pageContext.request.contextPath}/pages/update-info.jsp">
                <button class="button-account-info" aria-label="Update Info">Alterar Dados</button>
            </a>
          </div>
          <div>
           <a href="${pageContext.request.contextPath}/RemoverUsuario?id=<%= usuario.getId()%>">
             <button class="button-account-info">Excluir Conta</button>
           </a>
          </div>
          <div>
            <a href="${pageContext.request.contextPath}/Logout">
                <button class="button-account-info">Sair</button>
            </a>
          </div>
        </ul>
        </nav>
      </div>
    </div>

    <footer>
      <p>&copy; 2025 E-Commerce WebDev &ndash; Name</p>
    </footer>

    </div>
  </body>
</html>
