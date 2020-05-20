package productclient;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-19T17:37:03")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, LocalDate> bestBefore;
    public static volatile SingularAttribute<Products, BigDecimal> price;
    public static volatile SingularAttribute<Products, String> name;
    public static volatile SingularAttribute<Products, Integer> id;
    public static volatile SingularAttribute<Products, Integer> version;

}