<%-- 
    Document   : index
    Created on : 30 de out. de 2025, 17:06:49
    Author     : Leonardo Oliveira Moreira
--%>
<%@page import="model.produto.Produto"%>
<%@page import="java.util.List"%>
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
      <a href="${pageContext.request.contextPath}/index.jsp" class="logo-photo">
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

        <a href="${pageContext.request.contextPath}/pages/details-product.jsp" class="product-details-card">
          <div class="product-card">
            <img src="assets/images/pele_ball.jpg" alt="Product View" />
            <h3 class="product-description">Bola Autografada por Pelé</h3>
          </div>
        </a>

        <a href="${pageContext.request.contextPath}/pages/details-product.jsp" class="product-details-card">
          <div class="product-card">
            <img src="assets/images/slipknot.jpg" alt="Product View" />
            <h3 class="product-description">Guitarra Autografada SlipKnot</h3>
          </div>
        </a>

        <a href="${pageContext.request.contextPath}/pages/details-product.jsp" class="product-details-card">
          <div class="product-card">
            <img src="assets/images/fotos-beatles.jpg" alt="Product View" />
            <h3 class="product-description">Foto Autografada Beatles</h3>
          </div>
        </a>

        <a href="${pageContext.request.contextPath}/pages/details-product.jsp" class="product-details-card">
          <div class="product-card">
            <img src="assets\images\pele_ball.jpg" alt="Product View" />
            <h3 class="product-description">Bola Autografada por Pelé</h3>
          </div>
        </a>

        <a href="${pageContext.request.contextPath}/pages/details-product.jsp" class="product-details-card">
          <div class="product-card">
            <img src="assets\images\pele_ball.jpg" alt="Product View" />
            <h3 class="product-description">Bola Autografada por Pelé</h3>
          </div>
        </a>
      </div>
    </main>

    <footer>
      <p>&copy; 2025 E-Commerce WebDev &ndash; Name</p>
    </footer>
  </body>
</html>

