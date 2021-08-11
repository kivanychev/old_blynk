package cc.blynk.core.http.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies the URI path that a resource class or class classMethod will serve
 * requests for.
 *
 * <p>Paths are relative. For an annotated class the base URI is the
 * application path. For an annotated
 * classMethod the base URI is the
 * effective URI of the containing class. For the purposes of absolutizing a
 * path against the base URI , a leading '/' in a path is
 * ignored and base URIs are treated as if they ended in '/'. E.g.:</p>
 *
 * <pre>&#64;Path("widgets")
 * public class WidgetsResource {
 *  &#64;GET
 *  String getList() {...}
 *
 *  &#64;GET &#64;Path("{id}")
 *  String getWidget(&#64;PathParam("id") String id) {...}
 * }</pre>
 *
 * <p>In the above, if the application path is
 * {@code catalogue} and the application is deployed at
 * {@code http://example.com/}, then {@code GET} requests for
 * {@code http://example.com/catalogue/widgets} will be handled by the
 * {@code getList} classMethod while requests for
 * <code>http://example.com/catalogue/widgets/<i>nnn</i></code> (where
 * <code><i>nnn</i></code> is some value) will be handled by the
 * {@code getWidget} classMethod. The same would apply if the value of either
 * {@code @Path} annotation started with '/'.</p>
 *
 * <p>Classes and methods may also be annotated with javax.ws.rs.Consumes and
 * to filter the requests they will receive.</p>
 *
 * @author Paul Sandoz
 * @author Marc Hadley
 * @since 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Path {

    /**
     * Defines a URI template for the resource class or classMethod, must not
     * include matrix parameters.
     *
     * <p>Embedded template parameters are allowed and are of the form:</p>
     *
     * <pre> param = "{" *WSP name *WSP [ ":" *WSP regex *WSP ] "}"
     * name = (ALPHA / DIGIT / "_")*(ALPHA / DIGIT / "." / "_" / "-" ) ; \w[\w\.-]*
     * regex = *( nonbrace / "{" *nonbrace "}" ) ; where nonbrace is any char other than "{" and "}"</pre>
     *
     * <p>See {@link <a href="http://tools.ietf.org/html/rfc5234">RFC 5234</a>}
     * for a description of the syntax used above and the expansions of
     * {@code WSP}, {@code ALPHA} and {@code DIGIT}. In the above {@code name}
     * is the template parameter name and the optional {@code regex} specifies
     * the contents of the capturing group for the parameter. If {@code regex}
     * is not supplied then a default value of {@code [^/]+} which terminates at
     * a path segment boundary, is used. Matching of request URIs to URI
     * templates is performed against encoded path values and implementations
     * will not escape literal characters in regex automatically, therefore any
     * literals in {@code regex} should be escaped by the author according to
     * the rules of
     * {@link <a href="http://tools.ietf.org/html/rfc3986#section-3.3">RFC 3986 section 3.3</a>}.
     * Caution is recommended in the use of {@code regex}, incorrect use can
     * lead to a template parameter matching unexpected URI paths. See
     * {@link <a href="http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html">Pattern</a>}
     * for further information on the syntax of regular expressions.
     * Values of template parameters may be extracted using.</p>
     *
     * <p>The literal part of the supplied value (those characters
     * that are not part of a template parameter) is automatically percent
     * encoded to conform to the {@code path} production of
     * {@link <a href="http://tools.ietf.org/html/rfc3986#section-3.3">RFC 3986 section 3.3</a>}.
     * Note that percent encoded values are allowed in the literal part of the
     * value, an implementation will recognize such values and will not double
     * encode the '%' character.</p>
     */
    String value();
}
