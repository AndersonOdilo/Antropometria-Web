<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    </head>
    <body>
        <div class="well bs-component">   
            <form method="post" action="${linkTo[UsuarioController].registrar()}" class="form-horizontal">
                <input type="hidden" name="usuario.id" value="${usuario.id}"/>

                <legend>Registro</legend>      
                <div class="form-group">
                    <div class="col-sm-4">
                        <label>Nome</label>
                        <input type="text" name="usuario.nome" class="form-control">
                    </div>
                    <div class="col-sm-3">
                        <label>Email</label>
                        <input type="email" name="usuario.email" class="form-control">
                    </div>
                    <div class="col-sm-2">
                        <label>Senha</label>
                        <input id="senha" type="password" name="usuario.senha" class="form-control">
                    </div>
                    <div class="col-sm-2">
                        <label for="confirmacao">Confirme a senha:</label>
                        <input id="confirmacao" equalTo="#senha" type="password" class="form-control">
                    </div>
                </div>
                <a href="/pacientes" type="button" class="btn btn-default">Cancelar</a>
                <button type="submit" class="btn btn-success">Salvar</button>
            </form>
        </div>    
    </body>
</html>
