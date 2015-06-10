package com.antropometria.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date data;

    @Temporal(TemporalType.DATE)
    private Date dataRetorno;

    private double altura;
    private double peso;
    private String descricao;

    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Professor professor;

    @OneToOne(cascade = CascadeType.ALL)
    private Circunferencia circunferencia;
    @OneToOne(cascade = CascadeType.ALL)
    private Osseo osseo;
    @OneToOne(cascade = CascadeType.ALL)
    private PregasCutaneas pregas;
    @OneToOne(cascade = CascadeType.ALL)
    private Bioimpedancia bioimpedancia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Circunferencia getCircunferencia() {
        return circunferencia;
    }

    public void setCircunferencia(Circunferencia circunferencia) {
        this.circunferencia = circunferencia;
    }

    public Osseo getOsseo() {
        return osseo;
    }

    public void setOsseo(Osseo osseo) {
        this.osseo = osseo;
    }

    public PregasCutaneas getPregas() {
        return pregas;
    }

    public void setPregas(PregasCutaneas pregas) {
        this.pregas = pregas;
    }

    public Bioimpedancia getBioimpedancia() {
        return bioimpedancia;
    }

    public void setBioimpedancia(Bioimpedancia bioimpedancia) {
        this.bioimpedancia = bioimpedancia;
    }

    public String getMesAno() {
        if (data != null) {
            return new SimpleDateFormat("MMMMM - YYYY").format(data);
        }
        return "";
    }

    public String getDiaMes() {
        if (data != null) {
            return new SimpleDateFormat("dd 'de' MMMMM").format(data);
        }
        return "";
    }

    public String getDataRetornoFormatada() {
        if (dataRetorno != null) {
            return new SimpleDateFormat("dd - MMMMM - YYYY").format(dataRetorno);
        }
        return "";
    }

}
