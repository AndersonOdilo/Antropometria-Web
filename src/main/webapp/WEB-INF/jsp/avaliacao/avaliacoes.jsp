<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Antropometria</title>
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
                        <li id="planos"><a href="${linkTo[PlanoController].plano()}">Planos</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="container">

            <h3>10 ultimas Avaliações</h3>
            <table id="example" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Paciente</th>
                        <th>Professor</th>
                        <th>Data efetuada</th>
                        <th>Data retorno</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${avaliacaoList}" var="a"> 
                        <tr>
                            <td>${a.paciente.nome}</td>
                            <td>${a.professor.nome}</td>
                            <td>${a.getDataFormatada()}</td>
                            <td>${a.getDataRetornoFormatada()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <a class="btn btn-primary pull-right" href="${linkTo[AvaliacaoController].novo()}">+ Nova avaliação</a>

            </div>
            <script>
                $(".nav").find(".active").removeClass("active");
                $("#avaliacoes").addClass("active");
            </script>
        </div>
    </body>
</html>
