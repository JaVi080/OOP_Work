module com.example.form_lab_asg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.form_lab_asg to javafx.fxml;
    exports com.example.form_lab_asg;
}