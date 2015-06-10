package com.antropometria.models;

import com.antropometria.models.Avaliacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-06-09T23:10:46")
@StaticMetamodel(Bioimpedancia.class)
public class Bioimpedancia_ { 

    public static volatile SingularAttribute<Bioimpedancia, Long> id;
    public static volatile SingularAttribute<Bioimpedancia, String> porcentagemMagra;
    public static volatile SingularAttribute<Bioimpedancia, String> massaGorda;
    public static volatile SingularAttribute<Bioimpedancia, String> massaMagra;
    public static volatile SingularAttribute<Bioimpedancia, String> imc;
    public static volatile SingularAttribute<Bioimpedancia, String> pesoIdeal;
    public static volatile SingularAttribute<Bioimpedancia, String> densidadeCorporal;
    public static volatile SingularAttribute<Bioimpedancia, String> pesoResidual;
    public static volatile SingularAttribute<Bioimpedancia, String> porcentagemGorda;
    public static volatile SingularAttribute<Bioimpedancia, Avaliacao> avaliacao;

}