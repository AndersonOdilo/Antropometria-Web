/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antropometria.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import com.antropometria.dao.ProfessorJpaController;
import com.antropometria.dao.exceptions.RollbackFailureException;
import com.antropometria.models.Professor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author anderson
 */
@Controller
public class ProfessorController {

    @Inject
    private Result result;

    @Inject
    private ProfessorJpaController dao;

    public void novo() {
        result.of(this).formulario();
    }

    public void formulario() {

    }

    @Get("/professor/editar/{professor.id}")
    public void editar(Professor professor) {
        result.include("professor", dao.findProfessor(professor.getId()));
        result.of(this).formulario();
    }

    public void salvar(Professor professor) {
        try {
            if (professor.getId() > 0) {
                dao.edit(professor);
            } else {
                dao.create(professor);

            }
        } catch (Exception ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.redirectTo(this).formulario();
    }

    @Get("/professores")
    public List<Professor> professores() {
        return dao.findProfessorEntities();
    }

    @Get("/professor/{professor.id}")
    public Professor professorShow(Professor professor) {
        return dao.findProfessor(professor.getId());
    }

    @Get("/professor/excluir/{professor.id}")
    public void excluir(Professor professor) {
        try {
            dao.destroy(professor.getId());
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.redirectTo(this).professores();
    }

}
