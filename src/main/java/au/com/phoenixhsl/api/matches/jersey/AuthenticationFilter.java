package au.com.phoenixhsl.api.matches.jersey;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import au.com.phoenixhsl.api.matches.exception.ApiException;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final String UNAUTHORIZED = "Access denied.";

	@Context
	private ResourceInfo resourceInfo;

	public void filter(ContainerRequestContext ctx) throws IOException {

		Method method = resourceInfo.getResourceMethod();

		if (method.isAnnotationPresent(RolesAllowed.class)) {

			final MultivaluedMap<String, String> headers = ctx.getHeaders();
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			if (authorization == null || authorization.isEmpty()) {
				throw new ApiException(Response.Status.UNAUTHORIZED.getStatusCode(), UNAUTHORIZED);
			}

			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
			String userAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

			final StringTokenizer token = new StringTokenizer(userAndPassword, ":");
			final String user = token.nextToken();
			final String password = token.nextToken();

			RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
			Set<String> roles = new HashSet<String>(Arrays.asList(rolesAllowed.value()));

			if (! isAccessAllowed(user, password, roles)) {
				throw new ApiException(Response.Status.UNAUTHORIZED.getStatusCode(), UNAUTHORIZED);
			}
		}
	}

	private boolean isAccessAllowed(final String user, final String password, final Set<String> roles) {

		//XXX: implement database here
		String dbUser = "user", dbPassword = "password", dbUserRole = "SPORTS_ANALIST";

		return (user.equals(dbUser) && password.equals(dbPassword) && roles.contains(dbUserRole));
	}
}