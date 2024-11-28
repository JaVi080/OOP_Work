module com.example.login_form_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.login_form_1 to javafx.fxml;
    exports com.example.login_form_1;
}