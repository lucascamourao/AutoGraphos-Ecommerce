<%-- 
    Document   : index
    Created on : 30 de out. de 2025, 17:06:49
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>smd e-commerce</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Identificação do Usuário</h1>
        <form action="Login" method="post">
            Login: <input type="text" name="login" /><br/>
            Senha: <input type="password" name="senha" /><br/>
            <input type="submit" value="Entrar"/>
        </form>
        <a href="novoCliente.jsp">Criar nova conta de cliente</a>
        <% if (request.getAttribute("mensagem") != null) {%>
        <h3><%= request.getAttribute("mensagem")%></h3>
        <% }%>
    </body>
</html>
