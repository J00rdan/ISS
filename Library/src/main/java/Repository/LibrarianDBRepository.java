package Repository;


import Model.Librarian;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LibrarianDBRepository implements LibrarianRepository{
    private SessionFactory sessionFactory;

    public LibrarianDBRepository(SessionFactory sessionFactory) {
        //logger.info("Initializing TeamDBRepository with properties: {} ",props);
        //dbUtils=new JdbcUtils(props);
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Librarian auth(int id, String pass) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    List<Librarian> result = session.createQuery("from Librarian ", Librarian.class).list();
                    tx.commit();

                    for (Librarian librarian :  result) {
                        if(librarian.getId() == id && librarian.getPassword().equals(pass))
                            return librarian;
                    }

                } catch (RuntimeException ex) {
                    System.err.println("Eroare la auth "+ex);
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
    public Librarian findOne(Integer id) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    String queryString = "from Librarian librarian where librarian.id = :idP";

                    List<Librarian> result = session.createQuery(queryString, Librarian.class).setParameter("idP", id).list();
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
    public Iterable<Librarian> findAll() {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    List<Librarian> result = session.createQuery("from Librarian", Librarian.class).list();
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
    public void save(Librarian entity) {
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

                    String queryString = "from Librarian librarian where librarian.id = :idP";

                    Librarian librarian = session.createQuery(queryString, Librarian.class).setParameter("idP", id).setMaxResults(1).uniqueResult();

                    session.delete(librarian);

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
    public void update(Librarian entity) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    Librarian librarian = session.load(Librarian.class, entity.getId());
                    librarian.setPassword(entity.getPassword());

                    tx.commit();

                } catch (RuntimeException ex) {
                    System.err.println("Eroare la update "+ex);
                    if (tx != null)
                        tx.rollback();
                }
            }

        }catch (Exception e){
            System.err.println("Exception "+e);
            e.printStackTrace();
        }
    }
}
