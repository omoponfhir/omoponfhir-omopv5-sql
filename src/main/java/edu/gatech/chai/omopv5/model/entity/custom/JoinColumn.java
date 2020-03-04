package edu.gatech.chai.omopv5.model.entity.custom;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface JoinColumn {

	/**
	 * Name.
	 *
	 * @return the string
	 */
    String name() default "";
    
    /**
	 * ReferencedColumnName.
	 *
	 * @return the string
	 */
    String referencedColumnName() default "";
    
    /**
	 * Table.
	 *
	 * @return the string
	 */
    String table() default "";
    
	/**
	 * Nullable.
	 *
	 * @return true, if successful
	 */
    boolean nullable() default true;
}
