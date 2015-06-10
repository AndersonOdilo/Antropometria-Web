<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Antropometria</title>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>

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
                        <th width="20%">Telefone</th>
                        <th width="23%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${professorList}" var="p">
                        <tr>
                            <td>${p.nome}</td>
                            <td>${p.getDataFormatada()}</td>
                            <td>${p.sexo}</td>
                            <td>${p.telefone}</td>
                            <td>
                                <div class="btn-group pull-right">
                                    <a professor_id="${p.id}" id="abrir_modal" class="btn btn-default btn-sm"><i class="fa fa-file-text-o"></i> Abrir</a>
                                    <a href="professor/editar/${p.id}" class="btn btn-default btn-sm"><i class="fa fa-edit"></i> Editar</a>
                                    <a href="professor/excluir/${p.id}" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Apagar</a>
                                </div>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>

            <div class="modal fade" id="professorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Professor</h4>
                        </div>
                        <div class="modal-body" id="body">
                            ...
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>

            <script>
                $('#abrir_modal').click(function (event) {
                    $.get("professor/" + $(this).attr('professor_id'), function (res) {
                        $('#body').html(res);
                        $('#professorModal').modal('show');
                    });
                });
            </script>

            <a class="btn btn-primary pull-right" href="${linkTo[ProfessorController].novo()}">+ Novo professor</a>

            <script>
                $(".nav").find(".active").removeClass("active");
                $("#professores").addClass("active");
            </script>
        </div>
    </body>
</html>
