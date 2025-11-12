<%-- 
    Document   : principal
    Created on : 30 de out. de 2025, 17:20:03
    Author     : Leonardo Oliveira Moreira
--%>

<%@page import="model.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("usuario") != null) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>smd e-commerce</title>
    </head>
    <body>
        <h1>Olá, <%= usuario.getNome()%></h1>
        <h3>Você é um <%= usuario.getAdministrador() ? "Administrador" : "Cliente" %></h3>
        <a href="RemoverUsuario?id=<%= usuario.getId()%>">Remover Conta</a>
        <a href="atualizarUsuario.jsp">Atualizar Conta</a>
        <a href="Logout">Sair</a>
    </body>
</html>
<%
    } else {
        request.setAttribute("mensagem", "Você não tem permissão para acessar este recurso");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
%>
