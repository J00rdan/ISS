package Repository;

import Model.Librarian;
import Model.Subscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SubscriberDBRepository implements SubscriberRepository{
    private SessionFactory sessionFactory;

    public SubscriberDBRepository(SessionFactory sessionFactory) {
        //logger.info("Initializing TeamDBRepository with properties: {} ",props);
        //dbUtils=new JdbcUtils(props);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Subscriber findOne(Integer id) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    String queryString = "from Subscriber subscriber where subscriber .id = :idP";

                    List<Subscriber> result = session.createQuery(queryString, Subscriber.class).setParameter("idP", id).list();
                    tx.commit();

                    if(result.size() == 1){
                        return result.get(0);
                    }

                } catch (RuntimeException ex) {
                    System.err.println("Eroare la findOne "+ex);
                    if (tx != null)
                        tx.rollback();
                }
            }

        }catch (Exception e){
            System.err.println("Exception "+e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Subscriber> findAll() {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    List<Subscriber> result = session.createQuery("from Subscriber", Subscriber.class).list();
                    tx.commit();

                    return result;

                } catch (RuntimeException ex) {
                    System.err.println("Eroare la findAll "+ex);
                    if (tx != null)
                        tx.rollback();
                }
            }

        }catch (Exception e){
            System.err.println("Exception "+e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Subscriber entity) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    session.save(entity);

                    tx.commit();

                } catch (RuntimeException ex) {
                    System.err.println("Eroare la save "+ex);
                    if (tx != null)
                        tx.rollback();
                }
            }

        }catch (Exception e){
            System.err.println("Exception "+e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Subscriber entity) {

    }
}
