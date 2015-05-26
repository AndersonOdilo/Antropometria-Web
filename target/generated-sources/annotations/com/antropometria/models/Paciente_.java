package com.antropometria.models;

import com.antropometria.models.Avaliacao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-05-21T15:28:35")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, Long> id;
    public static volatile SingularAttribute<Paciente, String> cidade;
    public static volatile ListAttribute<Paciente, Avaliacao> avaliacoes;
    public static volatile SingularAttribute<Paciente, String> email;
    public static volatile SingularAttribute<Paciente, String> telefone;
    public static volatile SingularAttribute<Paciente, String> sexo;
    public static volatile SingularAttribute<Paciente, String> nome;
    public static volatile SingularAttribute<Paciente, Date> dataNascimento;
    public static volatile SingularAttribute<Paciente, String> observacao;

}