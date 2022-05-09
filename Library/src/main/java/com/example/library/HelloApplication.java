package com.example.library;

import com.example.library.Repository.LibrarianDBRepository;
import com.example.library.Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;

public class HelloApplication{
    static SessionFactory sessionFactory;

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

        LibrarianDBRepository librarianDBRepository = new LibrarianDBRepository((sessionFactory));

        Service srv = new Service(librarianDBRepository);

        GUI gui = new GUI();
        gui.setService(srv);

        try{
            gui.run();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            close();
        }
    }
}