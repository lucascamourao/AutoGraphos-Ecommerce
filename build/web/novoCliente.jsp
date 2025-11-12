<%-- 
    Document   : novoCliente
    Created on : 28 de out. de 2025, 17:01:36
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>smd e-commerce</title>
    </head>
    <body>
        <h1>Criar Novo Cliente</h1>
        <form action="InserirCliente" method="post">
            Nome: <input type="text" name="nome" /><br/>
            Endereço <input type="text" name="endereco" /><br/>
            Email: <input type="text" name="email" /><br/>
            Login: <input type="text" name="login" /><br/>
            Senha: <input type="text" name="senha" /><br/>
            <input type="submit" value="Salvar"/>
        </form>
        <a href="index.jsp">Login</a>
        <% if (request.getAttribute("mensagem") != null) {%>
        <h3><%= request.getAttribute("mensagem")%></h3>
        <% }%>
    </body>
</html>
