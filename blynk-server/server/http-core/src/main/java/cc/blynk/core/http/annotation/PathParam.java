package cc.blynk.core.http.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binds the value of a URI template parameter or a path segment
 * containing the template parameter to a resource classMethod parameter, resource
 * class field, or resource class
 * bean property. The value is URL decoded unless this
 * is disabled using the annotation.
 * A default value can be specified using the
 * annotation.
 *
 * The type of the annotated parameter, field or property must either:
 * <ul>
 * <li>Be { javax.ws.rs.core.PathSegment}, the value will be the final
 * segment of the matching part of the path.
 * See { javax.ws.rs.core.UriInfo} for a means of retrieving all request
 * path segments.</li>
 * <li>Be {@code List<javax.ws.rs.core.PathSegment>}, the
 * value will be a list of {@code PathSegment} corresponding to the path
 * segment(s) that matched the named template parameter.
 * See {javax.ws.rs.core.UriInfo} for a means of retrieving all request
 * path segments.</li>
 * <li>Be a primitive type.</li>
 * <li>Have a constructor that accepts a single String argument.</li>
 * <li>Have a static classMethod named {@code valueOf} or {@code fromString}
 * that accepts a single
 * String argument (see, for example, {@link Integer#valueOf(String)}).</li>
 * <li>Have a registered implementation of { javax.ws.rs.ext.ParamConverterProvider}
 * JAX-RS extension SPI that returns a { javax.ws.rs.ext.ParamConverter}
 * instance capable of a "from string" conversion for the type.</li>
 * </ul>
 *
 * <p>The injected value corresponds to the latest use (in terms of scope) of
 * the path parameter. E.g. if a class and a sub-resource classMethod are both
 * annotated with a { javax.ws.rs.Path &#64;Path} containing the same URI template
 * parameter, use of {@code @PathParam} on a sub-resource classMethod parameter
 * will bind the value matching URI template parameter in the classMethod's
 * {@code @Path} annotation.</p>
 *
 * <p>Because injection occurs at object creation time, use of this annotation
 * on resource class fields and bean properties is only supported for the
 * default per-request resource class lifecycle. Resource classes using
 * other lifecycles should only use this annotation on resource classMethod
 * parameters.</p>
 *
 * @author Paul Sandoz
 * @author Marc Hadley
 * @since 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathParam {

    /**
     * Defines the name of the URI template parameter whose value will be used
     * to initialize the value of the annotated classMethod parameter, class field or
     * property. See { javax.ws.rs.Path#value()} for a description of the syntax of
     * template parameters.
     *
     * <p>E.g. a class annotated with: {@code @Path("widgets/{id}")}
     * can have methods annotated whose arguments are annotated
     * with {@code @PathParam("id")}.
     */
    String value();
}
