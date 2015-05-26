/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.antropometria.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import javax.inject.Inject;

/**
 *
 * @author anderson
 */
@Controller
public class PlanoController {
    
    @Inject
    Result result;
    
    @Get("/planos")
    public void plano(){
        
    }
}
