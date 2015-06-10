package com.antropometria.models;

import com.antropometria.models.Avaliacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-06-09T23:10:46")
@StaticMetamodel(PregasCutaneas.class)
public class PregasCutaneas_ { 

    public static volatile SingularAttribute<PregasCutaneas, Long> id;
    public static volatile SingularAttribute<PregasCutaneas, String> abdominal;
    public static volatile SingularAttribute<PregasCutaneas, String> coxa;
    public static volatile SingularAttribute<PregasCutaneas, String> axilarMedia;
    public static volatile SingularAttribute<PregasCutaneas, String> torax;
    public static volatile SingularAttribute<PregasCutaneas, String> triceps;
    public static volatile SingularAttribute<PregasCutaneas, String> subescapular;
    public static volatile SingularAttribute<PregasCutaneas, String> biceps;
    public static volatile SingularAttribute<PregasCutaneas, String> suprailiaca;
    public static volatile SingularAttribute<PregasCutaneas, Avaliacao> avaliacao;

}