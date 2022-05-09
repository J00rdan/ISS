
import Controllers.AdminMenuController;
import Controllers.Controller;
import Controllers.LoginController;
import Controllers.UserMenuController;
import Model.Subscriber;
import Repository.BookDBRepository;
import Repository.LibrarianDBRepository;
import Repository.SubscriberDBRepository;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;

public class HelloApplication extends Application {
    static SessionFactory sessionFactory;
    private static Stage stage;

    private AdminMenuController adminMenuController;
    private UserMenuController userMenuController;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception here "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        initialize();

        try{
            launch();
        }catch (Exception e){
            System.err.println("asd" + e.getMessage());
        }finally {
            close();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LibrarianDBRepository librarianDBRepository = new LibrarianDBRepository((sessionFactory));
        BookDBRepository bookDBRepository = new BookDBRepository(sessionFactory);
        SubscriberDBRepository subscriberDBRepository = new SubscriberDBRepository(sessionFactory);

        Service srv = new Service(librarianDBRepository, bookDBRepository, subscriberDBRepository);

        stage = primaryStage;

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1050, 600);
//
//        LoginController loginController = fxmlLoader.getController();
//
//        loginController.setSrv(srv, this);
//
//        primaryStage.setTitle("Social Network");
//        primaryStage.setResizable(false);
//        primaryStage.setScene(scene);
//        primaryStage.show();

        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("Login.fxml"));
        Parent root=loader.load();


        LoginController ctrl =
                loader.<LoginController>getController();
        ctrl.setService(srv);


        FXMLLoader cloader = new FXMLLoader(
                getClass().getClassLoader().getResource("AdminMenu.fxml"));
        Parent croot=cloader.load();


        AdminMenuController adminMenuController =
                cloader.<AdminMenuController>getController();
        adminMenuController.setService(srv);

        ctrl.setAdminMenuCtrl(adminMenuController);
        ctrl.setParent(croot);

        primaryStage.setTitle("MPP chat");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /*public void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = fxmlLoader.load();

        Controller loginController = fxmlLoader.getController();
        loginController.setService(service, this);

        stage.getScene().setRoot(pane);
    }

    public void changeSceneToAdminMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("AdminMenu.fxml"));
        Parent pane = fxmlLoader.load();

        adminMenuController = fxmlLoader.getController();
        adminMenuController.setService(service, this);

        stage.getScene().setRoot(pane);
    }

    public void changeSceneToUserMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserMenu.fxml"));
        Parent pane = fxmlLoader.load();

        userMenuController = fxmlLoader.getController();
        userMenuController.setService(service, this);
        userMenuController.init();

        stage.getScene().setRoot(pane);
    }*/
}