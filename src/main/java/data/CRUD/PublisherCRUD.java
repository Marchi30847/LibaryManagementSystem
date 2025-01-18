package data.CRUD;


import utils.HibernateUtil;
import data.entities.Publisher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PublisherCRUD {
    public void savePublisher(Publisher publisher) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Generate ID if not set
            if (publisher.getId() == 0) {
                Integer nextId = (Integer) session.createQuery("SELECT COALESCE(MAX(p.id), 0) + 1 FROM Publisher p")
                        .uniqueResult();
                publisher.setId(nextId);
            }

            session.save(publisher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new IllegalStateException("Failed to save publisher", e);
        }
    }



    public List<Publisher> getAllPublishers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Publisher", Publisher.class).list();
        }
    }

    public Publisher getPublisherById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Publisher.class, id);
        }
    }

    public void updatePublisher(Publisher publisher) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(publisher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deletePublisher(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Publisher publisher = session.get(Publisher.class, id);
            if (publisher != null) {
                session.delete(publisher);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

