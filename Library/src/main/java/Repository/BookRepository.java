package Repository;

import Model.Book;

public interface BookRepository extends Repository<Integer, Book> {

    public Iterable<Book> findAvailable();
}
