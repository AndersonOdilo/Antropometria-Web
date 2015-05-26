package com.antropometria.models;

import com.antropometria.models.Bioimpedancia;
import com.antropometria.models.Circunferencia;
import com.antropometria.models.Osseo;
import com.antropometria.models.Paciente;
import com.antropometria.models.PregasCutaneas;
import com.antropometria.models.Professor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-05-21T15:28:35")
@StaticMetamodel(Avaliacao.class)
public class Avaliacao_ { 

    public static volatile SingularAttribute<Avaliacao, Long> id;
    public static volatile SingularAttribute<Avaliacao, Double> peso;
    public static volatile SingularAttribute<Avaliacao, PregasCutaneas> pregas;
    public static volatile SingularAttribute<Avaliacao, Paciente> paciente;
    public static volatile SingularAttribute<Avaliacao, Date> data;
    public static volatile SingularAttribute<Avaliacao, Professor> professor;
    public static volatile SingularAttribute<Avaliacao, Circunferencia> circunferencia;
    public static volatile SingularAttribute<Avaliacao, Osseo> osseo;
    public static volatile SingularAttribute<Avaliacao, Double> altura;
    public static volatile SingularAttribute<Avaliacao, String> descricao;
    public static volatile SingularAttribute<Avaliacao, Bioimpedancia> bioimpedancia;

}