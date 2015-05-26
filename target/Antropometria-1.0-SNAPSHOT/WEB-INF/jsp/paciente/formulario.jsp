<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Antropometria Paciente</title>       
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>" type="text/javascript"></script>
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
            <div class="form-group">
                <div class="well bs-component">   
                    <form method="post" action="${linkTo[PacienteController].salvar}" class="form-horizontal">
                        <input type="hidden" name="paciente.id" value="${paciente.id}"/>
                        <fieldset>
                            <legend>Novo paciente</legend>   
                            <div class="form-group">
                                 <div class="col-sm-4">
                                 <label>Nome*</label>
                                <input type="text" name="paciente.nome" value="${paciente.nome}" class="form-control">
                            </div>
                            <div class="col-sm-3">
                                <label>Nascimento*</label>
                                <input type="date" name="paciente.dataNascimento" value="${paciente.dataNascimento}" class="form-control">
                            </div>
                            <div class="col-sm-3">
                                <label>Sexo*</label>
                                <select name="paciente.sexo"  class="form-control">
                                    <option value="Masculino" ${paciente.sexo eq 'Masculino'? 'selected' : ''} >Masculino</option>
                                    <option value="Feminino" ${paciente.sexo eq 'Feminino'? 'selected' : ''}>Feminino</option>
                                </select>
                            </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label>Email:</label>
                                    <input type="email" name="paciente.email" value="${paciente.email}" class="form-control">
                                </div>
                                <div class="col-sm-3">
                                    <label>Telefone*</label>
                                    <input type="text" name="paciente.telefone" value="${paciente.telefone}" class="form-control">
                                </div>
                                <div class="col-sm-3">
                                    <label>Cidade:</label>
                                    <input type="text" name="paciente.cidade" value="${paciente.cidade}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <br>
                                    <textarea name="paciente.observacao" placeholder="Observações" class="form-control">${paciente.observacao}</textarea>
                                </div>
                            </div>
                            <a href="${linkTo[PacienteController].pacientes()}" type="button" class="btn btn-default">Cancelar</a>
                            <button type="submit" class="btn btn-success">Salvar</button>
                        </fieldset>
                    </form>
                </div>
                <script>
                    $(".nav").find(".active").removeClass("active");
                    $("#pacientes").addClass("active");
                </script>
            </div>
        </div>
    </body>
</html>
