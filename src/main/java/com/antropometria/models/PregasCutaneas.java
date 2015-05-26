package com.antropometria.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class PregasCutaneas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;

    private String biceps;
    private String triceps;
    private String axilarMedia;
    private String torax;
    private String abdominal;
    private String suprailiaca;
    private String subescapular;
    private String coxa;

    @OneToOne(mappedBy = "pregas")
    private Avaliacao avaliacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiceps() {
        return biceps;
    }

    public void setBiceps(String biceps) {
        this.biceps = biceps;
    }

    public String getTriceps() {
        return triceps;
    }

    public void setTriceps(String triceps) {
        this.triceps = triceps;
    }

    public String getAxilarMedia() {
        return axilarMedia;
    }

    public void setAxilarMedia(String axilarMedia) {
        this.axilarMedia = axilarMedia;
    }

    public String getTorax() {
        return torax;
    }

    public void setTorax(String torax) {
        this.torax = torax;
    }

    public String getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(String abdominal) {
        this.abdominal = abdominal;
    }

    public String getSuprailiaca() {
        return suprailiaca;
    }

    public void setSuprailiaca(String suprailiaca) {
        this.suprailiaca = suprailiaca;
    }

    public String getSubescapular() {
        return subescapular;
    }

    public void setSubescapular(String subescapular) {
        this.subescapular = subescapular;
    }

    public String getCoxa() {
        return coxa;
    }

    public void setCoxa(String coxa) {
        this.coxa = coxa;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    
}
