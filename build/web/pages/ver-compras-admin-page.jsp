<%@page import="model.usuario.Usuario"%>
<%@page import="model.produto.Produto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.venda.Venda"%>
<%@page import="model.venda.VendaDAO"%>
<%@page import="model.venda_produto.VendaProduto"%>
<%@page import="model.venda_produto.VendaProdutoDAO"%>
<%@page import="model.produto.ProdutoDAO"%>
<%@page import="model.usuario.UsuarioDAO"%>
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
            src="..\assets\images\icon_ecommerce_new.png"
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
        <div style="margin-top: 20px;text-align: center;">
            <h2>Compras de Todos os Usuários</h2>
        </div>

        <%
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            VendaDAO vendaDAO = new VendaDAO();
            VendaProdutoDAO vendaProdutoDAO = new VendaProdutoDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            List<Usuario> todosUsuarios = usuarioDAO.obterTodos(); // precisa implementar este método no UsuarioDAO
            if (todosUsuarios != null && !todosUsuarios.isEmpty()) {
        %>

        <div class="form-page-container">
            <div class="form-container">
                <nav class="nav-purchase-page">

                    <%
                        for (Usuario user : todosUsuarios) {
                            List<Venda> vendasUsuario = vendaDAO.obterPorUsuario(user.getId());
                            if (vendasUsuario != null && !vendasUsuario.isEmpty()) {
                                for (Venda v : vendasUsuario) {
                                    List<VendaProduto> itensVenda = vendaProdutoDAO.obterPorVenda(v.getId());
                                    if (itensVenda != null && !itensVenda.isEmpty()) {
                                        for (VendaProduto vp : itensVenda) {
                                            Produto p = produtoDAO.obter(vp.getProduto().getId());
                    %>
                    <div class="my-purchase-card">
                        <div class="card-content">
                            <h3><strong><%= p.getDescricao() %></strong></h3>
                            <p><strong>Preço pago:</strong> R$ <%= vp.getPreco() %></p>
                            <p><strong>Quantidade:</strong> <%= vp.getQuantidade() %></p>
                            <p><strong>ID da Venda:</strong> <%= v.getId() %></p>
                            <p><strong>ID do Usuário:</strong> <%= user.getId() %></p>
                            <p><strong>Nome do Usuário:</strong> <%= user.getNome() %></p>
                            
                            <form action="<%= request.getContextPath() %>/admin/DeletarVenda" method="post" style="margin-top: 10px;">
                                <input type="hidden" name="vendaId" value="<%= v.getId() %>" />
                                <button type="submit" class="header-button" style="background-color: #e74c3c; color: white;">Deletar Venda</button>
                            </form>
                        </div>
                    </div>
                    <%
                                        }
                                    }
                                }
                            }
                        }
                    %>

                </nav>
            </div>
        </div>

        <%
            } else {
        %>
            <p>Não existem usuários ou vendas registradas.</p>
        <%
            }
        %>
    </main>




    <footer>
      <p>&copy; 2025 E-Commerce WebDev &ndash; Name</p>
    </footer>
  </body>
</html>
