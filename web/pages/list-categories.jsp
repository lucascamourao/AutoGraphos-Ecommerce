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

        <a href="${pageContext.request.contextPath}/pages/shopping-cart-page.jsp">
          <button id="shopping-cart-button" class="button-header">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              class="size-6"
            >
              <path
                d="M2.25 2.25a.75.75 0 0 0 0 1.5h1.386c.17 0 .318.114.362.278l2.558 9.592a3.752 3.752 0 0 0-2.806 3.63c0 .414.336.75.75.75h15.75a.75.75 0 0 0 0-1.5H5.378A2.25 2.25 0 0 1 7.5 15h11.218a.75.75 0 0 0 .674-.421 60.358 60.358 0 0 0 2.96-7.228.75.75 0 0 0-.525-.965A60.864 60.864 0 0 0 5.68 4.509l-.232-.867A1.875 1.875 0 0 0 3.636 2.25H2.25ZM3.75 20.25a1.5 1.5 0 1 1 3 0 1.5 1.5 0 0 1-3 0ZM16.5 20.25a1.5 1.5 0 1 1 3 0 1.5 1.5 0 0 1-3 0Z"
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
        <div style="margin-top: 20px; text-align: center;">
            <h2>Cadastro de Categorias</h2>
        </div>

        <%
            List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
            if (categorias != null && !categorias.isEmpty()) {
        %>

        <div class="form-page-container">
            <div class="form-container">
                <nav class="nav-purchase-page">

                    <%
                        for (Categoria c : categorias) {
                    %>
                    <div class="my-purchase-card">
                        <div class="card-content">
                            <h3><strong><%= c.getNome() %></strong></h3>
                            <div style="display: flex; gap: 10px; margin-top: 12px;">
                                <a href="<%= request.getContextPath() %>/admin/MostrarCategoria?id=<%= c.getId() %>">
                                    <button class="header-button">Atualizar</button>
                                </a>

                                <a href="<%= request.getContextPath() %>/admin/RemoverCategoria?id=<%= c.getId() %>">
                                    <button class="header-button">Remover</button>
                                </a>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>

                </nav>
            </div>
        </div>

        <%
            } else {
        %>
            <p>Não existem categorias registradas.</p>
        <%
            }
        %>

        <div style="margin-bottom: 10px; text-align: center;">
            <a href="<%= request.getContextPath() %>/admin/NovaCategoria">
                <button class="header-button">Nova Categoria</button>
            </a>
        </div>
    </main>

    <footer>
      <p>&copy; 2025 E-Commerce WebDev &ndash; Name</p>
    </footer>

    <script src="<%= request.getContextPath() %>/js/app.js"></script>
    <script src="../js/form-validation.js" type="text/javascript"></script>
    
  </body>
</html>
