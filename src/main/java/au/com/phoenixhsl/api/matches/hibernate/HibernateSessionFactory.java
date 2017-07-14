package au.com.phoenixhsl.api.matches.hibernate;

import org.glassfish.hk2.api.Factory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory implements Factory<SessionFactory> {

    private final SessionFactory sessionFactory;

    public HibernateSessionFactory() {

    	StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    @Override
    public SessionFactory provide() {
        return sessionFactory;
    }

    @Override
    public void dispose(SessionFactory sessionFactory) {
        sessionFactory.close();
    }
}