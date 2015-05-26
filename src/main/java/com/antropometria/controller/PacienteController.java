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
import com.antropometria.dao.PacienteJpaController;
import com.antropometria.dao.exceptions.RollbackFailureException;
import com.antropometria.models.Paciente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author anderson
 */
@Controller
public class PacienteController {

    @Inject
    private Result result;

    @Inject
    private PacienteJpaController dao;

    public void formulario() {
    }

    public void novo() {
        result.redirectTo(this).formulario();
    }

    @Get("/paciente/editar/{paciente.id}")
    public void editar(Paciente paciente) {
        result.include("paciente", dao.findPaciente(paciente.getId()));
        result.of(this).formulario();
    }

    @Post
    public void salvar(Paciente paciente) {
        try {
            if (paciente.getId() > 0) {
                dao.edit(paciente);
            } else {
                dao.create(paciente);
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.redirectTo(this).pacientes();
    }

    @Get("/pacientes")
    public List<Paciente> pacientes() {
        return dao.findPacienteEntities();
    }

    @Get("/paciente/{paciente.id}")
    public Paciente pacienteShow(Paciente paciente) {
        return dao.findPaciente(paciente.getId());
    }

    @Get("/paciente/excluir/{paciente.id}")
    public void excluir(Paciente paciente) {
        try {
            dao.destroy(paciente.getId());
        } catch (RollbackFailureException ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.redirectTo(this).pacientes();
    }
}