<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Antropometria Professor</title>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.mask.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/antropometria.js"/>" type="text/javascript"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${linkTo[AvaliacaoController].avaliacoes()}">AntroMais</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li id="avaliacoes"><a href="${linkTo[AvaliacaoController].avaliacoes()}">Avaliações Físicas</a></li>
                        <li id="pacientes"><a href="${linkTo[PacienteController].pacientes()}">Pacientes</a></li>
                        <li id="professores"><a href="${linkTo[ProfessorController].professores()}">Professores</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li id="planos"><a href="/planos">Planos</a></li>
                        <li><a href="/nova-impressao">Configurações</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="container">
            <div class="well bs-component">   
                <form method="post" action="${linkTo[ProfessorController].salvar()}" class="form-horizontal">
                    <input type="hidden" name="professor.id" value="${professor.id}">
                    <fieldset>
                        <legend>Novo professor</legend>      
                        <div class="form-group">
                            <div class="col-sm-4">
                                <label>Nome*</label>
                                <input type="text" name="professor.nome" value="${professor.nome}" class="form-control" required="true">
                            </div>
                            <div class="col-sm-3">
                                <label>Nascimento*</label>
                                <input type="text" name="professor.dataNascimento" value="${professor.getDataFormatada()}" class="form-control data" required="true">
                            </div>
                            <div class="col-sm-3">
                                <label>Sexo*</label>
                                <select name="professor.sexo" class="form-control" required="true">
                                    <option value="Masculino" ${professor.sexo eq 'Masculino'? 'selected' : ''}>Masculino</option>
                                    <option value="Feminino" ${professor.sexo eq 'Feminino'? 'selected' : ''}>Feminino</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4">
                                <label>Email:</label>
                                <input type="text" name="professor.email" value="${professor.email}" class="form-control">
                            </div>
                            <div class="col-sm-3">
                                <label>Telefone*</label>
                                <input type="text" name="professor.telefone" value="${professor.telefone}" class="form-control fone" required="true">
                            </div>
                        </div>
                        <a href="${linkTo[ProfessorController].professores()}" type="button" class="btn btn-default">Cancelar</a>
                        <button type="submit" class="btn btn-success">Salvar</button>
                    </fieldset>
                </form>
            </div>
            <script>
                $(".nav").find(".active").removeClass("active");
                $("#professores").addClass("active");
            </script>
        </div>
    </body>
</html>
