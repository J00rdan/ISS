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

public class UserMenuController{
    private Service srv;

    private Subscriber user;

    ObservableList<Book> model = FXCollections.observableArrayList();


    @FXML
    public TableColumn<Book, String> tableColumnId;
    @FXML
    public TableColumn<Book, String> tableColumnTitle;
    @FXML
    public TableColumn<Book, String> tableColumnAuthor;

    @FXML
    public TextField idTextField;
    @FXML
    public TextField titleTextField;
    @FXML
    public TextField authorTextField;

    @FXML
    public TableView<Book> tableViewBook;

    public void setService(Service service){
        srv = service;
    }

    public void setUser(Subscriber s){
        user = s;
    }

    public void init(){
        initModel1();
        initialize1();
    }

    private void initModel1() {
        Iterable<Book> messages = null;
        try {
            messages = srv.getAllAvailableBooks();
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

    public void onSelectingItem(javafx.scene.input.MouseEvent mouseEvent) {
        if (tableViewBook.getSelectionModel().getSelectedItem() != null) {
            Book b = tableViewBook.getSelectionModel().getSelectedItem();
            idTextField.setText(String.valueOf(b.getId()));
            titleTextField.setText(b.getTitle());
            authorTextField.setText(b.getAuthor());
        }
    }

    public void borrowBook(){
        int id = Integer.parseInt(idTextField.getText());
        String title = titleTextField.getText();
        String author = authorTextField.getText();

        Book book = new Book(id, title, author);

        srv.borrowBook(book, user);

        init();
        idTextField.clear();
        titleTextField.clear();
        authorTextField.clear();
    }
}
