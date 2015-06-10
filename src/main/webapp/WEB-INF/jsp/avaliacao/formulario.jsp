<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atropometria Avaliação</title>
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
                <form method="post" action="${linkTo[AvaliacaoController].salvar}" class="form-horizontal">
                    <input type="hidden" value="${avaliacao.id}" name="avaliacao.id"/>
                    <input type="hidden" value="${avaliacao.circunferencia.id}" name="avaliacao.circunferencia.id"/>
                    <input type="hidden" value="${avaliacao.osseo.id}" name="avaliacao.osseo.id"/>
                    <input type="hidden" value="${avaliacao.pregas.id}" name="avaliacao.pregas.id"/>
                    <fieldset>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Realizar avaliação física</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>Data*</label>
                                        <input type="text" name="avaliacao.data" value="${avaliacao.data}" class="form-control data" required="true"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Data do retorno*</label>
                                        <input type="text" name="avaliacao.dataRetorno" value="${avaliacao.dataRetorno}" class="form-control data" required="true"/>
                                    </div>
                                    <div class="col-sm-4">
                                        <label>Professor*</label>
                                        <select name="avaliacao.professor.id" class="form-control" required="true">
                                            <c:forEach items="${professores}" var="professor">    
                                                <option value="${professor.id}">${professor.nome}</option>    
                                            </c:forEach>   
                                        </select>
                                    </div>
                                    <div class="col-sm-4">
                                        <label>Paciente*</label>  
                                        <select name="avaliacao.paciente.id" class="form-control" required="true">
                                            <c:forEach items="${pacientes}" var="paciente">    
                                                <option value="${paciente.id}">${paciente.nome}</option>    
                                            </c:forEach>   
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-8">
                                        <label>Descrição*</label>
                                        <input type="text" name="avaliacao.descricao" value="${avaliacao.descricao}" class="form-control" required="true"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Altura*</label>
                                        <input type="text" name="avaliacao.altura" value="${avaliacao.altura}" class="form-control" required="true"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Peso*</label>
                                        <input type="text" name="avaliacao.peso" value="${avaliacao.peso}" class="form-control peso" required="true"/>
                                    </div>
                                </div>
                                <hr>
                                <h4>
                                    Circunferências
                                </h4>
                                <hr>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>Ombro</label>
                                        <input type="text" name="avaliacao.circunferencia.ombro" value="${avaliacao.circunferencia.ombro}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Peitoral</label>
                                        <input type="text" name="avaliacao.circunferencia.peitoral" value="${avaliacao.circunferencia.peitoral}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Cintura</label>
                                        <input type="text" name="avaliacao.circunferencia.cintura" value="${avaliacao.circunferencia.cintura}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Abdomen</label>
                                        <input type="text" name="avaliacao.circunferencia.abdomen" value="${avaliacao.circunferencia.abdomen}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Quadril</label>
                                        <input type="text" name="avaliacao.circunferencia.quadril" value="${avaliacao.circunferencia.quadril}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Pescoço</label>
                                        <input type="text" name="avaliacao.circunferencia.pescoco" value="${avaliacao.circunferencia.pescoco}" class="form-control medida"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>Panturrilha (E)</label>
                                        <input type="text" name="avaliacao.circunferencia.panturrilhaE" value="${avaliacao.circunferencia.panturrilhaE}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Panturrilha (D)</label>
                                        <input type="text" name="avaliacao.circunferencia.panturrilhaD" value="${avaliacao.circunferencia.panturrilhaD}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Coxa (E)</label>
                                        <input type="text" name="avaliacao.circunferencia.coxaE" value="${avaliacao.circunferencia.coxaE}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Coxa (D)</label>
                                        <input type="text" name="avaliacao.circunferencia.coxaD" value="${avaliacao.circunferencia.coxaD}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Coxa proximal (E)</label>
                                        <input type="text" name="avaliacao.circunferencia.coxaProximalE" value="${avaliacao.circunferencia.coxaProximalE}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Coxa proximal (D)</label>
                                        <input type="text" name="avaliacao.circunferencia.coxaProximalD" value="${avaliacao.circunferencia.coxaProximalD}" class="form-control medida"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>Braço relaxado (E)</label>
                                        <input type="text" name="avaliacao.circunferencia.bracoRelaxadoE" value="${avaliacao.circunferencia.bracoRelaxadoE}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Braço relaxado (D)</label>
                                        <input type="text" name="avaliacao.circunferencia.bracoRelaxadoD" value="${avaliacao.circunferencia.bracoRelaxadoD}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Braço contraido (E)</label>
                                        <input type="text" name="avaliacao.circunferencia.bracoContraidoE" value="${avaliacao.circunferencia.bracoContraidoE}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Braço contraido (D)</label>
                                        <input type="text" name="avaliacao.circunferencia.bracoContraidoD" value="${avaliacao.circunferencia.bracoContraidoD}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Antebraço</label>
                                        <input type="text" name="avaliacao.circunferencia.antebraco" value="${avaliacao.circunferencia.antebraco}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Punho</label>
                                        <input type="text" name="avaliacao.circunferencia.punho" value="${avaliacao.circunferencia.punho}" class="form-control medida"/>
                                    </div>
                                </div>

                                <hr>
                                <h4>
                                    Diâmetros Ósseos
                                </h4>
                                <hr>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>Punho</label>
                                        <input type="text" name="avaliacao.osseo.punho" value="${avaliacao.osseo.punho}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Femur</label>
                                        <input type="text" name="avaliacao.osseo.femur" value="${avaliacao.osseo.femur}" class="form-control medida"/>
                                    </div>
                                </div>

                                <hr>
                                <h4>
                                    Percentual de Gordura (Pregas cutâneas)
                                </h4>
                                <hr>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>Bíceps</label>
                                        <input type="text" name="avaliacao.pregas.biceps" value="${avaliacao.pregas.biceps}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Tríceps</label>
                                        <input type="text" name="avaliacao.pregas.triceps" value="${avaliacao.pregas.triceps}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Axilar média</label>
                                        <input type="text" name="avaliacao.pregas.axilarMedia" value="${avaliacao.pregas.axilarMedia}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Torax</label>
                                        <input type="text" name="avaliacao.pregas.torax" value="${avaliacao.pregas.torax}" class="form-control medida"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>Abdominal</label>
                                        <input type="text" name="avaliacao.pregas.abdominal" value="${avaliacao.pregas.abdominal}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Suprailíaca</label>
                                        <input type="text" name="avaliacao.pregas.suprailiaca" value="${avaliacao.pregas.suprailiaca}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Subescapular</label>
                                        <input type="text" name="avaliacao.pregas.subescapular" value="${avaliacao.pregas.subescapular}" class="form-control medida"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>Coxa</label>
                                        <input type="text" name="avaliacao.pregas.coxa" value="${avaliacao.pregas.coxa}" class="form-control medida"/>
                                    </div>
                                </div>

                                <hr>
                                <h4>
                                    Resultado
                                </h4>
                                <hr>
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label>IMC Atual:</label>
                                        <label class="label-data form-control">
                                            <span data-bind="text: value">2,00</span>
                                            <!-- ko with:situacao-->
                                            <span class="label label-resultado label-danger" data-bind="text: descricao, css: color">Baixo</span>
                                            <!-- /ko -->
                                        </label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label>Peso Ideal:</label>
                                        <label class="label-data form-control">
                                            <span data-bind="text: value">21,50 Kg</span>
                                        </label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label>% Massa Gorda:</label>
                                        <label class="label-data form-control">
                                            <span data-bind="text: value">6,70%</span>
                                            <!-- ko with:situacao-->
                                            <span class="label label-resultado label-danger" data-bind="text: descricao, css: color">Baixo</span>
                                            <!-- /ko -->
                                        </label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label>% Massa Magra:</label>
                                        <label class="label-data form-control">
                                            <span data-bind="text: value">7,80%</span>
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label>Densidade Corporal:</label>
                                        <label class="label-data form-control">
                                            <span data-bind="text: value">5,19</span>
                                        </label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label>Massa Magra:</label>
                                        <label class="label-data form-control">
                                            <span data-bind="text: value">19,10 Kg</span>
                                        </label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label>Massa Gorda:</label>
                                        <label class="label-data form-control">
                                            <span data-bind="text: value">23,20 Kg</span>
                                        </label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label>Peso Residual:</label>
                                        <label class="label-data form-control">
                                            <span data-bind="text: value">7,80 Kg</span>
                                        </label>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="pull-right">
                            <a href="/avaliacoes" type="button" class="btn btn-default">Cancelar</a>
                            <button type="submit" class="btn btn-success">Salvar</button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </body>
</html>
