/*
 * This is custom annotation to be used with OMOP SQL implementation.
 * The entities here are designed to be used with OHDSI's SqlRender. Thus
 * persistent package is completed removed. Thus, instead of persist package's
 * column, the annotation is copied here to support JDBC operations.
 */
package edu.gatech.chai.omopv5.model.entity.custom;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface Column.
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Column {
	
	/**
	 * Name.
	 *
	 * @return the string
	 */
	String name() default "";
	
	/**
	 * Nullable.
	 *
	 * @return true, if successful
	 */
	boolean nullable() default true;
}
