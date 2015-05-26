package com.antropometria.models;

import com.antropometria.models.Avaliacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-05-21T15:28:35")
@StaticMetamodel(Osseo.class)
public class Osseo_ { 

    public static volatile SingularAttribute<Osseo, Long> id;
    public static volatile SingularAttribute<Osseo, String> punho;
    public static volatile SingularAttribute<Osseo, String> femur;
    public static volatile SingularAttribute<Osseo, Avaliacao> avaliacao;

}