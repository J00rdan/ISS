package Service;


import Model.Book;
import Model.Borrow;
import Model.Subscriber;
import Repository.BookRepository;
import Repository.BorrowRepository;
import Repository.LibrarianRepository;
import Repository.SubscriberRepository;

public class Service {
    private LibrarianRepository librarianRepository;
    private BookRepository bookRepository;
    private SubscriberRepository subscriberRepository;
    private BorrowRepository borrowRepository;

    public Service(LibrarianRepository librarianRepository, BookRepository bookRepository, SubscriberRepository subscriberRepository, BorrowRepository borrowRepository) {
        this.librarianRepository = librarianRepository;
        this.bookRepository = bookRepository;
        this.subscriberRepository = subscriberRepository;
        this.borrowRepository = borrowRepository;
    }

    public boolean adminLogin(int id, String pass){
        if(!pass.equals("")){
            return librarianRepository.auth(id, pass) != null;
        }
        return false;
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(Book book){
        bookRepository.delete(book.getId());
    }

    public void saveSubscriber(Subscriber subscriber){
        subscriberRepository.save(subscriber);
    }

    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Iterable<Book> getAllAvailableBooks(){
        return bookRepository.findAvailable();
    }

    public Iterable<Subscriber> getAllSubscribers(){
        return subscriberRepository.findAll();
    }

    public Subscriber login(int id, String pass){
        if(!pass.equals("")){
            return subscriberRepository.auth(id, pass);
        }
        return null;
    }

    public void borrowBook(Book book, Subscriber borrower){
        Borrow borrow = new Borrow(borrower.getId(), book.getId());
        borrowRepository.save(borrow);
        Book b = bookRepository.findOne(book.getId());
        b.setBorrowed(true);
        bookRepository.update(b);
    }

    public void returnBook(Borrow borrow) throws Exception {
        Borrow borrowDb = borrowRepository.findBorrow(borrow.getBookId(), borrow.getSubscriberId());

        if(borrowDb == null){
            throw new Exception("Incorect Credentials");
        }

        Book b = bookRepository.findOne(borrow.getBookId());
        b.setBorrowed(false);
        bookRepository.update(b);

        borrowRepository.delete(borrowDb.getId());
    }
}
