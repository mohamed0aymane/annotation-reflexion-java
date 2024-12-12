package org.mql.java.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mql.java.ennumeration.Separator;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	String name() default "";
	String icon() default "";
	int key();
	Separator separator() default Separator.NONE;
}
