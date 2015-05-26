<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Antropometria</title>
        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>" type="text/javascript"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/timeline.css"/>">       
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

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
                        <li><a href="/nova-impressao">Configurações</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="container">
            <style>
                .modal .modal-body {
                    max-height: 600px;
                    overflow-y: auto;
                }
            </style>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">${paciente.nome}</h3>
                </div>
                <div class="panel-body">  
                    <div class="btn-group pull-right">
                        <a href="${linkTo[AvaliacaoController].novo()}" type="button" class="btn btn-primary">+ Nova Avaliação</a>
                        <a type="button" class="btn btn-default">Gerar Relatório</a>
                    </div>
                    <p><strong>Sexo: </strong> ${paciente.sexo}</p>
                    <p><strong>Nascimento: </strong>${paciente.dataNascimento}</p>
                    <p><strong>Email: </strong> ${paciente.email}</p>
                    <p><strong>Telefone: </strong> ${paciente.telefone}</p>
                    <p><strong>Cidade: </strong> ${paciente.cidade}</p>
                    <p><strong>Observação: </strong> ${paciente.observacao}</p>
                    <br>

                    <div >
                        <ul class="timeline">
                            <li class="time-label">
                                <span class="bg-blue">Histórico de avaliações:
                                </span>
                                <br />
                            </li>
                            <c:forEach items="${paciente.avaliacoes}" var="a"> 

                                <li class="time-label">
                                    <span class="bg-light-blue"> ${a.data}
                                    </span>
                                </li>
                                <li>
                                    <i class="fa fa-user-md bg-blue"></i>
                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i><b> Data: ${a.data}</b></span>
                                        <h3 class="timeline-header"><a href="#">${a.professor.nome}</a> realizou esta avaliação física.</h3>
                                        <div class="timeline-body">
                                            <p><i>Altura:</i> ${a.altura} | <i>Peso:</i> ${a.peso} | <i>IMC:</i> 20,09 | <i>Retorno:</i> ${a.data}</p>
                                            <p>Descrição: ${a.descricao}</p>
                                            <div class='pull-right'>
                                                <a href="${linkTo[AvaliacaoController].avaliacaoShow}${a.id}" class="btn btn-default btn-sm"><i class="fa fa-file-text-o"></i> Abrir avaliação</a>
                                                <a href="${linkTo[AvaliacaoController].excluir}${a.id}" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Apagar</a>
                                            </div>
                                            <br>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                </div>
            </div>

            <div class="modal bs-example-modal-lg" id="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body" id="body">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning">Imprimir</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>

            <script>
                $(".nav").find(".active").removeClass("active");
                $("#pacientes").addClass("active");
            </script>
        </div>
    </body>
</html>
