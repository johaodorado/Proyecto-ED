module com.mycompany.proyecto.ed {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyecto.ed to javafx.fxml;
    exports com.mycompany.proyecto.ed;
}
