<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    </head>
    <body>
        <form method="post" action="${linkTo[UsuarioController].logar()}" class="form-horizontal">
            <div class="content">
                <h4 class="title">
                    Login
                </h4>
                <input type="email" name="email" placeholder="Email" class="form-control">
                <input type="password" name="senha" placeholder="Password" class="form-control">
                <button class="btn btn-block btn-default btn-rad btn-lg" type="submit">Acessar</button>
            </div>
        </form>
        <hr>
        <div class="foot">
            <span>Não possui conta no AntroMais? <a href="#">Experimente agora!</a></span>
        </div>
    </body>
</html>
