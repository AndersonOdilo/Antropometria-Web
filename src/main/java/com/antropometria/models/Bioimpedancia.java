package com.antropometria.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Bioimpedancia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String imc;
    private String pesoIdeal;
    private String porcentagemGorda;
    private String porcentagemMagra;
    private String densidadeCorporal;
    private String massaGorda;
    private String massaMagra;
    private String pesoResidual;

    @OneToOne(mappedBy = "bioimpedancia")
    private Avaliacao avaliacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getPesoIdeal() {
        return pesoIdeal;
    }

    public void setPesoIdeal(String pesoIdeal) {
        this.pesoIdeal = pesoIdeal;
    }

    public String getPorcentagemGorda() {
        return porcentagemGorda;
    }

    public void setPorcentagemGorda(String porcentagemGorda) {
        this.porcentagemGorda = porcentagemGorda;
    }

    public String getPorcentagemMagra() {
        return porcentagemMagra;
    }

    public void setPorcentagemMagra(String porcentagemMagra) {
        this.porcentagemMagra = porcentagemMagra;
    }

    public String getDensidadeCorporal() {
        return densidadeCorporal;
    }

    public void setDensidadeCorporal(String densidadeCorporal) {
        this.densidadeCorporal = densidadeCorporal;
    }

    public String getMassaGorda() {
        return massaGorda;
    }

    public void setMassaGorda(String massaGorda) {
        this.massaGorda = massaGorda;
    }

    public String getMassaMagra() {
        return massaMagra;
    }

    public void setMassaMagra(String massaMagra) {
        this.massaMagra = massaMagra;
    }

    public String getPesoResidual() {
        return pesoResidual;
    }

    public void setPesoResidual(String pesoResidual) {
        this.pesoResidual = pesoResidual;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    
}
