package com.antropometria.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Osseo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;

    private String punho;
    private String femur;

    @OneToOne(mappedBy = "osseo")
    private Avaliacao avaliacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPunho() {
        return punho;
    }

    public void setPunho(String punho) {
        this.punho = punho;
    }

    public String getFemur() {
        return femur;
    }

    public void setFemur(String femur) {
        this.femur = femur;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    
}
