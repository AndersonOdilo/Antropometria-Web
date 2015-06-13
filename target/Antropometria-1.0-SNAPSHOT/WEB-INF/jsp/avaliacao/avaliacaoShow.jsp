<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Antropometria</title> 
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/relatorio.css"/>" rel="stylesheet" media="print"/>
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
            <div class="panel panel-primary">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="pull-right btn-group">
                        <a href="javascript:print()" class="btn btn-default btn-lg"><i class="fa fa-print"></i> Imprimir</a>
                    </div>
                    <h2 class="text-primary" style="margin-top: 0px"><strong>${a.paciente.nome}</strong></h2>
                    <h4>${a.professor.nome}</h4>
                    <p><i>Data: </i> ${a.getDiaMes()}</p>
                    <p><i>Altura:</i> ${a.altura} | <i>Peso:</i> ${a.getDiaMes()} | <i>IMC:</i> 20,09 | <i>Retorno:</i> ${a.getDataRetornoFormatada()}</p>
                    <p><i>Descrição:</i> ${a.descricao}</p>

                    <hr>
                    <h4>Circunferências:</h4>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody class="col-md-8">
                                <tr>
                                    <th class="col-md-1" scope="row">Ombro:</th>
                                    <td class="col-md-1">${a.circunferencia.ombro} cm</td>
                                    <th class="col-md-2" scope="row">Panturrilha (E):</th>
                                    <td class="col-md-1">${a.circunferencia.panturrilhaE} cm</td>
                                    <th class="col-md-2" scope="row">Braço Relaxado (E):</th>
                                    <td class="col-md-1">${a.circunferencia.bracoRelaxadoE} cm</td>
                                </tr>
                                <tr>
                                    <th scope="row">Peitoral:</th>
                                    <td>${a.circunferencia.peitoral} cm</td>
                                    <th scope="row">Panturrilha (D):</th>
                                    <td>${a.circunferencia.panturrilhaD} cm</td>
                                    <th scope="row">Braço Relaxado (D):</th>
                                    <td>${a.circunferencia.bracoRelaxadoD} cm</td>
                                </tr>
                                <tr>
                                    <th scope="row">Cintura:</th>
                                    <td>${a.circunferencia.cintura} cm</td>
                                    <th scope="row">Coxa (E):</th>
                                    <td>${a.circunferencia.coxaE} cm</td>
                                    <th scope="row">Braço contraido (E):</th>
                                    <td>${a.circunferencia.bracoContraidoE} cm</td>
                                </tr>
                                <tr>
                                    <th scope="row">Abdomen:</th>
                                    <td>${a.circunferencia.abdomen} cm</td>
                                    <th scope="row">Coxa (D):</th>
                                    <td>${a.circunferencia.coxaD} cm</td>
                                    <th scope="row">Braço contraido (D):</th>
                                    <td>${a.circunferencia.bracoContraidoD} cm</td>
                                </tr>
                                <tr>
                                    <th scope="row">Quadril:</th>	
                                    <td>${a.circunferencia.quadril} cm</td>
                                    <th scope="row">Coxa Proximal (E):</th>
                                    <td>${a.circunferencia.coxaProximalE} cm</td>
                                    <th scope="row">Antebraço:</th>
                                    <td>${a.circunferencia.antebraco} cm</td>
                                </tr>
                                <tr>
                                    <th scope="row">Pescoço:</th>
                                    <td>${a.circunferencia.pescoco} cm</td>
                                    <th scope="row">Coxa Proximal (D):</th>
                                    <td>${a.circunferencia.coxaProximalD} cm</td>
                                    <th scope="row">Punho:</th>
                                    <td>${a.circunferencia.punho} cm</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <hr>
                    <h4>Diâmetros Ósseos:</h4>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody class="col-md-4">
                                <tr>
                                    <th class="col-md-1" scope="row">Punho:</th>
                                    <td class="col-md-1">${a.osseo.punho}</td>
                                    <th class="col-md-1" scope="row">Fêmur:</th>
                                    <td class="col-md-1" >${a.osseo.femur}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <hr>
                    <h4>Percentual de Gordura (Pregas cutâneas):</h4>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody class="col-md-5">
                                <tr>
                                    <th class="col-md-1" scope="row">Bíceps:</th>
                                    <td class="col-md-1">${a.pregas.biceps} cm</td>
                                    <th class="col-md-1" scope="row">Abdominal:</th>
                                    <td class="col-md-1">${a.pregas.abdominal} cm</td>
                                </tr>
                                <tr>
                                    <th scope="row">Tríceps:</th>
                                    <td>${a.pregas.triceps} cm</td>
                                    <th scope="row">Suprailíaca:</th>
                                    <td>${a.pregas.suprailiaca} cm</td>
                                </tr>
                                <tr>
                                    <th scope="row">Axilar média:</th>
                                    <td>${a.pregas.axilarMedia} cm</td>
                                    <th scope="row">Subescapular:</th>
                                    <td>${a.pregas.subescapular} cm</td>
                                </tr>
                                <tr>
                                    <th scope="row">Torax:</th>
                                    <td>${a.pregas.torax} cm</td>
                                    <th scope="row">Coxa:</th>
                                    <td>${a.pregas.coxa} cm</td>
                                </tr>
                            </tbody>
                        </table>

                        <div id="quebra-linha"></div>
                        <hr>
                        <h4>Resultado (Bioimpedância):</h4>
                        <div class="table-responsive">
                            <table class="table">
                                <tbody class="col-md-5">
                                    <tr>
                                        <th class="col-md-2" scope="row">IMC Atual:</th>
                                        <td class="col-md-1">1 cm</td>
                                        <th class="col-md-2" scope="row">Densidade Corporal:</th>
                                        <td class="col-md-1">${a.circunferencia.panturrilhaE} cm</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Peso ideal:</th>
                                        <td>${a.circunferencia.peitoral} cm</td>
                                        <th scope="row">Massa Magra:</th>
                                        <td>${a.circunferencia.panturrilhaD} cm</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">% Massa Gorda:</th>
                                        <td>${a.circunferencia.cintura} cm</td>
                                        <th scope="row">Massa Gorda:</th>
                                        <td>${a.circunferencia.coxaE} cm</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">% Massa Magra:</th>
                                        <td>${a.circunferencia.abdomen} cm</td>
                                        <th scope="row">Peso Residural:</th>
                                        <td>${a.circunferencia.coxaD} cm</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
