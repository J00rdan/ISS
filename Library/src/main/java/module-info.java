module com.example.library {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;

    opens com.example.library to javafx.fxml;
    exports com.example.library;

    requires java.naming;
    requires jakarta.persistence;
}