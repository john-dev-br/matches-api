package au.com.phoenixhsl.api.matches.hibernate;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateSession implements Factory<Session> {

    private final SessionFactory factory;

    @Inject
    public HibernateSession(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Session provide() {
        return factory.openSession();
    }

    @Override
    public void dispose(Session session) {
    	
        if (session.isOpen()) {
            session.close();
        }
    }
}