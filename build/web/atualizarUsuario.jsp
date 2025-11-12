<%-- 
    Document   : novoCliente
    Created on : 28 de out. de 2025, 17:01:36
    Author     : Leonardo Oliveira Moreira
--%>

<%@page import="model.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>smd e-commerce</title>
    </head>
    <body>
        <h1>Atualizar Usuário</h1>
        <% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>
        <form action="AtualizarUsuario" method="post">
            <input type="hidden" name="id" value="<%= usuario.getId() %>" />
            Nome: <input type="text" name="nome" value="<%= usuario.getNome() %>" /><br/>
            Endereço <input type="text" name="endereco" value="<%= usuario.getEndereco() %>" /><br/>
            Email: <input type="text" name="email" value="<%= usuario.getEmail() %>" /><br/>
            Login: <input type="text" name="login" value="<%= usuario.getLogin() %>"/><br/>
            Senha: <input type="text" name="senha" value="<%= usuario.getSenha() %>"/><br/>
            <input type="submit" value="Salvar"/>
        </form>
        <% if (request.getAttribute("mensagem") != null) {%>
        <h3><%= request.getAttribute("mensagem")%></h3>
        <% }%>
    </body>
</html>
