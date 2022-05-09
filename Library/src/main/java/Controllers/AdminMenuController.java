package Controllers;

import Model.Book;
import Model.Subscriber;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AdminMenuController{
    private Service srv;

    ObservableList<Book> model = FXCollections.observableArrayList();
    ObservableList<Subscriber> model2 = FXCollections.observableArrayList();

    @FXML
    public TableColumn<Book, String> tableColumnId;
    @FXML
    public TableColumn<Book, String> tableColumnTitle;
    @FXML
    public TableColumn<Book, String> tableColumnAuthor;

    @FXML
    public TableView<Book> tableViewBook;

    @FXML
    public TableColumn<Subscriber, String> tableColumnSId;
    @FXML
    public TableColumn<Subscriber, String> tableColumnFName;
    @FXML
    public TableColumn<Subscriber, String> tableColumnLName;

    @FXML
    public TableView<Subscriber> tableViewSubscribers;

    @FXML
    public TextField titleTextField;
    @FXML
    public TextField authorTextField;

    @FXML
    public TextField fNameField;
    @FXML
    public TextField lNameField;
    @FXML
    public TextField passField;

    public void setService(Service service){
        srv = service;
    }

    public void init(){
        initModel1();
        initialize1();
        initModel2();
        initialize2();
    }

    private void initModel1() {
        Iterable<Book> messages = null;
        try {
            messages = srv.getAllBooks();
            List<Book> messageTaskList = StreamSupport.stream(messages.spliterator(), false)
                    .collect(Collectors.toList());

            model.setAll(messageTaskList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize1() {
        // TODO
        tableColumnId.setCellValueFactory(new PropertyValueFactory<Book, String>("Id"));
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
        tableColumnAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
        tableViewBook.setItems(model);
    }

    private void initModel2() {
        Iterable<Subscriber> messages = null;
        try {
            messages = srv.getAllSubscribers();
            List<Subscriber> messageTaskList = StreamSupport.stream(messages.spliterator(), false)
                    .collect(Collectors.toList());

            model2.setAll(messageTaskList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize2() {
        // TODO
        tableColumnSId.setCellValueFactory(new PropertyValueFactory<Subscriber, String>("Id"));
        tableColumnFName.setCellValueFactory(new PropertyValueFactory<Subscriber, String>("FirstName"));
        tableColumnLName.setCellValueFactory(new PropertyValueFactory<Subscriber, String>("LastName"));
        tableViewSubscribers.setItems(model2);
    }

    public void addBook(){
        String title = titleTextField.getText();
        String author = authorTextField.getText();

        Book book = new Book(title, author);

        srv.saveBook(book);

        init();
        titleTextField.clear();
        authorTextField.clear();
    }

    public void addSubscriber(){
        String firstName = fNameField.getText();
        String lastName = lNameField.getText();
        String pass = passField.getText();

        Subscriber subscriber = new Subscriber(firstName, lastName, pass);

        srv.saveSubscriber(subscriber);

        init();
        fNameField.clear();
        lNameField.clear();
        passField.clear();
    }

}
