package Repository;

import Model.Book;
import Model.Librarian;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookDBRepository implements BookRepository{
    private SessionFactory sessionFactory;

    public BookDBRepository(SessionFactory sessionFactory) {
        //logger.info("Initializing TeamDBRepository with properties: {} ",props);
        //dbUtils=new JdbcUtils(props);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book findOne(Integer id) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    String queryString = "from Book book where book.id = :idP";

                    List<Book> result = session.createQuery(queryString, Book.class).setParameter("idP", id).list();
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
    public Iterable<Book> findAll() {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    List<Book> result = session.createQuery("from Book", Book.class).list();
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
    public void save(Book entity) {
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

                    String queryString = "from Book book where book.id = :idP";

                    Book book = session.createQuery(queryString, Book.class).setParameter("idP", id).setMaxResults(1).uniqueResult();

                    session.delete(book);

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
    public void update(Book entity) {
        try {
            try(Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();

                    Book book = session.load(Book.class, entity.getId());
                    book.setTitle(entity.getTitle());

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
