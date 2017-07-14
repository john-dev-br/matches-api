package au.com.phoenixhsl.api.matches.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoFactory {

	private final Session session;
	private Transaction transaction;

	@Inject
	public DaoFactory(Session session) {
		this.session = session;
	}

	public boolean hasTransaction() {
		return this.transaction != null;
	}
	
	public void beginTransaction() {
		this.transaction = this.session.beginTransaction();
	}

	public void commit() {

		this.transaction.commit();
		this.transaction = null;
	}
	
	public void rollback() {
		
		this.transaction.rollback();
		this.transaction = null;
	}
	
	public MatchDao getMatchDao() {
		return new MatchDao(this.session);
	}
}