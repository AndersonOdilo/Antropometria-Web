<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Antropometria Planos</title>
        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>" type="text/javascript"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/plans.css"/>">       
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
        <link rel="stylesheet" media="screen" href='@routes.Assets.at("css/plans.css")'>
        <div class="wrapper">
            <!-- PRICING-TABLE CONTAINER -->
            <div class="pricing-table group">
                <h3>Assine abaixo:</h3>
                <!-- PERSONAL -->
                <div class="block personal fl">
                    <h2 class="title">Autônomos</h2>
                    <!-- CONTENT -->
                    <div class="content">
                        <p class="price">
                            <sup>R$</sup>
                            <span>9,90</span>
                            <sub>/mês</sub>
                        </p>
                    </div>
                    <!-- /CONTENT -->
                    <!-- FEATURES -->
                    <ul class="features">
                        <li><span class="fontawesome-circle"></span>1 Profissional</li>
                        <li><span class="fontawesome-circle"></span>50 Clientes</li>
                        <li><span class="fontawesome-circle"></span>Avaliação física</li>
                        <li><span class="fontawesome-circle"></span>Lembretes</li>
                    </ul>
                    <!-- /FEATURES -->
                    <!-- PT-FOOTER -->
                    <a type="button" class="btn btn-primary btn-lg btn-block" >Assinar</a>
                    <!-- /PT-FOOTER -->
                </div>
                <!-- /PERSONAL -->
                <!-- PROFESSIONAL -->
                <div class="block professional fl">
                    <h2 class="title">+ Popular</h2>
                    <!-- CONTENT -->
                    <div class="content">
                        <p class="price">
                            <sup>R$</sup>
                            <span>17,90</span>
                            <sub>/mês</sub>
                        </p>
                    </div>
                    <!-- /CONTENT -->
                    <!-- FEATURES -->
                    <ul class="features">
                        <li><span class="fontawesome-circle"></span>3 Profissionais</li>
                        <li><span class="fontawesome-circle"></span>250 Clientes</li>
                        <li><span class="fontawesome-circle"></span>Avaliação física</li>
                        <li><span class="fontawesome-circle"></span>Lembretes</li>
                        <li><span class="fontawesome-circle"></span>Gráficos</li>
                        <li><span class="fontawesome-circle"></span>Relatórios</li>
                    </ul>
                    <!-- /FEATURES -->
                    <!-- PT-FOOTER -->
                    <a type="button" class="btn btn-primary btn-lg btn-block" >Assinar</a>
                    <!-- /PT-FOOTER -->
                </div>
                <!-- /PROFESSIONAL -->
                <!-- BUSINESS -->
                <div class="block business fl">
                    <h2 class="title">Clínicas</h2>
                    <!-- CONTENT -->
                    <div class="content">
                        <p class="price">
                            <sup>R$</sup>
                            <span>29,90</span>
                            <sub>/mês</sub>
                        </p>
                    </div>
                    <!-- /CONTENT -->

                    <!-- FEATURES -->
                    <ul class="features">
                        <li><span class="fontawesome-circle"></span>Profissionais ilimitados</li>
                        <li><span class="fontawesome-circle"></span>Clientes ilimitados.</li>
                        <li><span class="fontawesome-circle"></span>Avaliação física</li>
                        <li><span class="fontawesome-circle"></span>Lembretes</li>
                        <li><span class="fontawesome-circle"></span>Gráficos</li>
                        <li><span class="fontawesome-circle"></span>Relatórios</li>
                        <li><span class="fontawesome-circle"></span>Avisar cliente por email/sms*</li>
                    </ul>
                    <!-- /FEATURES -->

                    <!-- PT-FOOTER -->
                    <a type="button" class="btn btn-primary btn-lg btn-block" >Assinar</a>
                    <!-- /PT-FOOTER -->
                </div>
                <!-- /BUSINESS -->
            </div>
            <!-- /PRICING-TABLE -->
        </div>

        <p>* Assinando o plano clínicas você ganha 50 envios de sms por mês, caso necessite de mais, poderá comprar pacotes de sms com um custo de 20 centavos cada.</p>

        <script>
            $(".nav").find(".active").removeClass("active");
            $("#planos").addClass("active");
        </script>
    </body>
</html>
