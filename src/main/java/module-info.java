module com.mycompany.proyecto.ed {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.espol.proyecto.ed to javafx.fxml;
    exports com.espol.proyecto.ed;
}
