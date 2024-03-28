module com.example.myloop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.myloop to javafx.fxml;
    exports com.example.myloop;
}