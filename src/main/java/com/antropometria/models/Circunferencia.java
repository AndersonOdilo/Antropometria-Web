package com.antropometria.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Circunferencia implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String ombro;
    private String peitoral;
    private String cintura;
    private String abdomen;
    private String quadril;
    private String pescoco;
    private String panturrilhaE;
    private String panturrilhaD;
    private String punho;
    private String coxaE;
    private String coxaD;
    private String coxaProximalE;
    private String coxaProximalD;
    private String bracoRelaxadoE;
    private String bracoRelaxadoD;
    private String bracoContraidoE;
    private String bracoContraidoD;
    private String antebraco;

    @OneToOne(mappedBy = "circunferencia")
    private Avaliacao avaliacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOmbro() {
        return ombro;
    }

    public void setOmbro(String ombro) {
        this.ombro = ombro;
    }

    public String getPeitoral() {
        return peitoral;
    }

    public void setPeitoral(String peitoral) {
        this.peitoral = peitoral;
    }

    public String getCintura() {
        return cintura;
    }

    public void setCintura(String cintura) {
        this.cintura = cintura;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getQuadril() {
        return quadril;
    }

    public void setQuadril(String quadril) {
        this.quadril = quadril;
    }

    public String getPescoco() {
        return pescoco;
    }

    public void setPescoco(String pescoco) {
        this.pescoco = pescoco;
    }

    public String getPanturrilhaE() {
        return panturrilhaE;
    }

    public void setPanturrilhaE(String panturrilhaE) {
        this.panturrilhaE = panturrilhaE;
    }

    public String getPanturrilhaD() {
        return panturrilhaD;
    }

    public void setPanturrilhaD(String panturrilhaD) {
        this.panturrilhaD = panturrilhaD;
    }

    public String getPunho() {
        return punho;
    }

    public void setPunho(String punho) {
        this.punho = punho;
    }

    public String getCoxaE() {
        return coxaE;
    }

    public void setCoxaE(String coxaE) {
        this.coxaE = coxaE;
    }

    public String getCoxaD() {
        return coxaD;
    }

    public void setCoxaD(String coxaD) {
        this.coxaD = coxaD;
    }

    public String getCoxaProximalE() {
        return coxaProximalE;
    }

    public void setCoxaProximalE(String coxaProximalE) {
        this.coxaProximalE = coxaProximalE;
    }

    public String getCoxaProximalD() {
        return coxaProximalD;
    }

    public void setCoxaProximalD(String coxaProximalD) {
        this.coxaProximalD = coxaProximalD;
    }

    public String getBracoRelaxadoE() {
        return bracoRelaxadoE;
    }

    public void setBracoRelaxadoE(String bracoRelaxadoE) {
        this.bracoRelaxadoE = bracoRelaxadoE;
    }

    public String getBracoRelaxadoD() {
        return bracoRelaxadoD;
    }

    public void setBracoRelaxadoD(String bracoRelaxadoD) {
        this.bracoRelaxadoD = bracoRelaxadoD;
    }

    public String getBracoContraidoE() {
        return bracoContraidoE;
    }

    public void setBracoContraidoE(String bracoContraidoE) {
        this.bracoContraidoE = bracoContraidoE;
    }

    public String getBracoContraidoD() {
        return bracoContraidoD;
    }

    public void setBracoContraidoD(String bracoContraidoD) {
        this.bracoContraidoD = bracoContraidoD;
    }

    public String getAntebraco() {
        return antebraco;
    }

    public void setAntebraco(String antebraco) {
        this.antebraco = antebraco;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    
}
