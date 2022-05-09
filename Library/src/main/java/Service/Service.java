package Service;


import Model.Book;
import Model.Subscriber;
import Repository.BookRepository;
import Repository.LibrarianRepository;
import Repository.SubscriberRepository;

public class Service {
    private LibrarianRepository librarianRepository;
    private BookRepository bookRepository;
    private SubscriberRepository subscriberRepository;

    public Service(LibrarianRepository librarianRepository, BookRepository bookRepository, SubscriberRepository subscriberRepository) {
        this.librarianRepository = librarianRepository;
        this.bookRepository = bookRepository;
        this.subscriberRepository = subscriberRepository;
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

    public void saveSubscriber(Subscriber subscriber){
        subscriberRepository.save(subscriber);
    }

    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Iterable<Subscriber> getAllSubscribers(){
        return subscriberRepository.findAll();
    }

    public boolean login(int id, String pass){
        return false;
    }
}
