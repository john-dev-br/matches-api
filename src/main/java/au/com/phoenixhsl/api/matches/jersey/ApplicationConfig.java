package au.com.phoenixhsl.api.matches.jersey;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import au.com.phoenixhsl.api.matches.dao.DaoFactory;
import au.com.phoenixhsl.api.matches.exception.ApiExceptionMapper;
import au.com.phoenixhsl.api.matches.exception.GenericExceptionMapper;
import au.com.phoenixhsl.api.matches.hibernate.HibernateSession;
import au.com.phoenixhsl.api.matches.hibernate.HibernateSessionFactory;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {

		// visual db editor for testing
		//org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url","jdbc:hsqldb:file:db/matches", "--noexit" });

		packages("au.com.phoenixhsl.api.matches.service"
				, "com.thirdpartyservices.api.matches.service");

		register(ApiExceptionMapper.class);
		register(GenericExceptionMapper.class);
		register(AuthenticationFilter.class);

		register(new AbstractBinder() {

			@Override
			protected void configure() {

				bindFactory(HibernateSessionFactory.class)
						.to(SessionFactory.class)
						.in(Singleton.class);

				bindFactory(HibernateSession.class)
						.to(Session.class)
						.in(RequestScoped.class);
				
				bind(DaoFactory.class).to(DaoFactory.class);
			}
		});
	}
}