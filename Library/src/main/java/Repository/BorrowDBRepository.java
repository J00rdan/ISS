package Repository;

import Model.Book;
import Model.Borrow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowDBRepository implements BorrowRepository{
    private SessionFactory sessionFactory;

    public BorrowDBRepository(SessionFactory sessionFactory) {
        //logger.info("Initializing TeamDBRepository with properties: {} ",props);
        //dbUtils=new JdbcUtils(props);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Borrow findOne(Integer id) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    String queryString = "from Borrow borrow where borrow.id = :idP";

                    List<Borrow> result = session.createQuery(queryString, Borrow.class).setParameter("idP", id).list();
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
    public Iterable<Borrow> findAll() {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    List<Borrow> result = session.createQuery("from Borrow ", Borrow.class).list();
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
    public void save(Borrow entity) {
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
    public void delete(Integer id) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    String queryString = "from Borrow borrow where borrow.id = :idP";

                    Borrow borrow = session.createQuery(queryString, Borrow.class).setParameter("idP", id).setMaxResults(1).uniqueResult();

                    session.delete(borrow);

                    tx.commit();

                } catch (RuntimeException ex) {
                    System.err.println("Eroare la delete "+ex);
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
    public void update(Borrow entity) {

    }

    @Override
    public Borrow findBorrow(int idBook, int idSub) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    String queryString = "from Borrow borrow where borrow.subscriberId = :idS and borrow.bookId = :idB";

                    List<Borrow> result = session.createQuery(queryString, Borrow.class).setParameter("idS", idSub).setParameter("idB", idBook).list();

                    System.out.println(result);

                    if(result.size() == 1){
                        return result.get(0);
                    }

                    tx.commit();

                } catch (RuntimeException ex) {
                    System.err.println("Eroare la delete "+ex);
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
}
