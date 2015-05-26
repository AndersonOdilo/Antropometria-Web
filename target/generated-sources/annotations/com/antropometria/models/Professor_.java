package com.antropometria.models;

import com.antropometria.models.Avaliacao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-05-21T15:28:35")
@StaticMetamodel(Professor.class)
public class Professor_ { 

    public static volatile SingularAttribute<Professor, Long> id;
    public static volatile ListAttribute<Professor, Avaliacao> avaliacoes;
    public static volatile SingularAttribute<Professor, String> email;
    public static volatile SingularAttribute<Professor, String> telefone;
    public static volatile SingularAttribute<Professor, String> sexo;
    public static volatile SingularAttribute<Professor, String> nome;
    public static volatile SingularAttribute<Professor, Date> dataNascimento;

}