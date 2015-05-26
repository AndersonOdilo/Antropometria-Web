<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Antropometria Pacientes</title>
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
            <table id="example" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th width="32%">Nome</th>
                        <th width="15%">Nascimento</th>
                        <th width="10%">Sexo</th>
                        <th width="15%">Telefone</th>
                        <th width="5%">Avaliações</th>
                        <th width="23%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pacienteList}" var="p"> 
                        <tr>
                            <td>${p.nome}</td>
                            <td>${p.dataNascimento}</td>
                            <td>${p.sexo}</td>
                            <td>${p.telefone}</td>
                            <td class="text-center">${p.avaliacoes.size()}</td>
                            <td>
                                <div class="btn-group pull-right">
                                    <a href="paciente/${p.id}" class="btn btn-default btn-sm"><i class="fa fa-file-text-o"></i> Abrir</a>
                                    <a href="paciente/editar/${p.id}" class="btn btn-default btn-sm"><i class="fa fa-edit"></i> Editar</a>
                                    <a href="paciente/excluir/${p.id}" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Apagar</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div><a class="btn btn-primary pull-right" href="${linkTo[PacienteController].novo()}">+ Novo paciente</a></div>

            <script>
                $(".nav").find(".active").removeClass("active");
                $("#pacientes").addClass("active");
            </script>
        </div>
    </body>
</html>
