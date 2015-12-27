package ru.firsto.improvetest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.firsto.improvetest.model.Catalog;
import ru.firsto.improvetest.model.Product;

import java.util.List;

public class ProductDAO implements DAO<Product> {

    private SessionFactory sessionFactory;

	private Session currentSession;
	
	private Transaction currentTransaction;

	public ProductDAO() {
        sessionFactory = getSessionFactory();
	}

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;
    }

	public Session openCurrentSession() {
		currentSession = sessionFactory.openSession();
		return currentSession;
	}

	public Session openCurrentSessionWithTransaction() {
		currentSession = sessionFactory.openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
        currentSession.close();
	}

	public void closeCurrentSessionWithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Product entity) {
		getCurrentSession().save(entity);
	}

	public void update(Product entity) {
		getCurrentSession().update(entity);
	}

	public Product findById(Integer id) {
		Product product = (Product) getCurrentSession().get(Product.class, id);
		return product;
	}

	public void delete(Product entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		List<Product> products = (List<Product>) getCurrentSession().createQuery("from Product").list();
		return products;
	}

    //TODO: Вынести в отдельный DAO
    @SuppressWarnings("unchecked")
	public List<Catalog> getCatalogs() {
		List<Catalog> catalogs = (List<Catalog>) getCurrentSession().createQuery("from Catalog").list();
		return catalogs;
	}

	public void deleteAll() {
		List<Product> entityList = findAll();
		for (Product entity : entityList) {
			delete(entity);
		}
	}

}
