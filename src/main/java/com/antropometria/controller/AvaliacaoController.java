/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antropometria.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.observer.download.Download;
import com.antropometria.dao.AvaliacaoJpaController;
import com.antropometria.dao.PacienteJpaController;
import com.antropometria.dao.ProfessorJpaController;
import com.antropometria.dao.exceptions.RollbackFailureException;
import com.antropometria.models.Avaliacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author anderson
 */
@Controller
public class AvaliacaoController {

    @Inject
    private Result result;
    @Inject
    private AvaliacaoJpaController dao;
    @Inject
    private ProfessorJpaController professorDAO;
    @Inject
    private PacienteJpaController pacienteDAO;

    public void formulario() {
        result.include("professores", professorDAO.findProfessorEntities());
        result.include("pacientes", pacienteDAO.findPacienteEntities());
    }

    public void novo() {
        result.redirectTo(this).formulario();
    }

    @Post
    public void salvar(Avaliacao avaliacao) {
        try {
            dao.create(avaliacao);
        } catch (Exception ex) {
            Logger.getLogger(AvaliacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.redirectTo(this).avaliacoes();
    }

    @Get("/")
    public void avaliacoes() {

    }

    @Get("/avaliacao/{avaliacao.id}")
    public void avaliacaoShow(Avaliacao avaliacao) {
        result.include("a", dao.findAvaliacao(avaliacao.getId()));
    }

    @Get("/avaliacao/excluir/{avaliacao.id}")
    public void excluir(Avaliacao avaliacao) {
        try {
            dao.destroy(avaliacao.getId());
        } catch (RollbackFailureException ex) {
            Logger.getLogger(AvaliacaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AvaliacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.redirectTo(PacienteController.class).pacientes();
    }

    @Path("/clients/pdf")
    public Download pdfReport() {
        Report report = generateReport();
        return new ReportDownload(report, (ExportFormat) pdfReport());
    }

}
