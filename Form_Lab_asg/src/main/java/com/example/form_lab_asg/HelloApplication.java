package com.example.form_lab_asg;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    ArrayList<Person> list=new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {

        //Label and TextFields
        Label userNameLabel= new Label("User Name");
        TextField userNameTextField= new TextField();
        Label father_n= new Label("User's Father Name");
        TextField user_father= new TextField();
        Label cnic= new Label("User's CNIC Number");
        TextField cnic_num= new TextField();

        //DatePicker for selecting date of Birth
        DatePicker dob=new DatePicker();
        Label dob_label=new Label("Date of Birth ");

        //Use of RadioButtons and ToggleGroup
        Label gender= new Label("Gender Status ");
        RadioButton male=new RadioButton("Male");
        RadioButton female=new RadioButton("Female");
        ToggleGroup toggle=new ToggleGroup();
        male.setToggleGroup(toggle);
        female.setToggleGroup(toggle);

        //ComboBox use --for combining drop down list with the textfield
        Label cities=new Label("Select the Cities");
        ComboBox<String> comboBox=new ComboBox();
        comboBox.getItems().addAll("Pakistan","Saudi Arabia","Turkey","Manchester");

        //Layout--GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(10));

        //Topbanner
        Image topbanner=new Image(this.getClass().getResource("/com/example/form_lab_asg/form2.png").toExternalForm());
        ImageView imageView=new ImageView(topbanner);
        imageView.fitWidthProperty().bind(gridPane.widthProperty());
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(false);
        gridPane.add(imageView,0,0,2,1);

        //1st hbox for 1st field
        HBox hbox=new HBox();
        hbox.setSpacing(20);
       userNameTextField.setStyle("-fx-border-color:burlywood;-fx-border-width:2px");
        hbox.setStyle("-fx-font-size:12px;-fx-font-weight:bold");
        hbox.getChildren().addAll(userNameLabel,userNameTextField);
        gridPane.add(hbox,1,1);

        //2nd hbox for 2nd field
        HBox hbox2=new HBox();
        hbox2.setSpacing(20);
        user_father.setStyle("-fx-border-color:burlywood;-fx-border-width:2px");
        hbox2.setStyle("-fx-font-size:12px;-fx-font-weight:bold");
        hbox2.getChildren().addAll(father_n,user_father);
        gridPane.add(hbox2,1,2);

        //3rd hbox
        HBox hbox3=new HBox();
       // hbox3.setAlignment(Pos.TOP_LEFT);
        hbox3.setSpacing(20);
        cnic_num.setStyle("-fx-border-color:burlywood;-fx-border-width:2px");
        hbox3.setStyle("-fx-font-size:12px;-fx-font-weight:bold");
        hbox3.getChildren().addAll(cnic,cnic_num);
        gridPane.add(hbox3,1,3);

        //4th Hbox
        HBox hbox4=new HBox();
        hbox4.setSpacing(20);
        dob.setStyle("-fx-border-color:burlywood;-fx-border-width:2px");
       hbox4.setStyle("-fx-font-size:12px;-fx-font-weight:bold;");
        hbox4.getChildren().addAll(dob_label,dob);
        gridPane.add(hbox4,1,4);

        //5th Hbox--gen_box
        HBox gen_box=new HBox();
        gen_box.setSpacing(30);
        gen_box.setStyle("-fx-font-size:12px;-fx-font-weight:bold;");
        gen_box.getChildren().addAll(gender,male,female);
        gridPane.add(gen_box,1,5);

        //6th Hbox
        HBox combo=new HBox();
        combo.setSpacing(30);
        comboBox.setStyle("-fx-font-size:12px;-fx-font-weight:bold;-fx-border-color:burlywood;-fx-border-width:2px");
        combo.setStyle("-fx-border-width:2px;-fx-font-weight:bold;");
        combo.getChildren().addAll(cities,comboBox);
        gridPane.add(combo,1,6);

        //Save button and the actions
        Button savebutton =new Button("Submit");
        savebutton.setOnAction(e->{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved");
            alert.setHeaderText("Information");
            alert.setContentText("Successfully Saved Info");
            alert.showAndWait();
            RadioButton selected=(RadioButton) toggle.getSelectedToggle();
            String gender_selected=selected.getText();
            //Storing information of user in ArrayList
            Person p=new Person(userNameTextField.getText(),user_father.getText(),cnic_num.getText(),dob.getValue().toString(),gender_selected,comboBox.getValue());
            list.add(p);
            if(!list.isEmpty()) {
                System.out.println("Person Info : ");
                //System.out.println(p);
                for(Person person: list){
                    System.out.println(person);
                }
            }
        });
        Button clear=new Button("Clear Information");
        clear.setOnAction(e->{
            //Clearing all the fields
            userNameTextField.clear();
            user_father.clear();
            cnic_num.clear();

            dob.setValue(null);
            toggle.selectToggle(null);
            comboBox.setValue(null);

        });

        VBox vbox=new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        Label file_img_label=new Label("Select Image");
        file_img_label.setStyle("-fx-font-weight:bold;");

        Button fileButton = new Button("Upload Image");
        fileButton.setStyle("-fx-background-color:green;-fx-font-weight:bold;-fx-text-fill:white");
        ImageView select_img=new ImageView();
        select_img.setFitWidth(200);
        select_img.setFitHeight(200);
        select_img.setPreserveRatio(true);


        fileButton.setOnAction(e -> {
            FileChooser filechooser=new FileChooser();
            filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp"));

            File selectedFile = filechooser.showOpenDialog(stage);
            if (selectedFile != null) {
                file_img_label.setText("Selected File: " + selectedFile.getName());
                Image img=new Image(selectedFile.toURI().toString());
                select_img.setImage(img);

            }
        });
        vbox.getChildren().addAll(file_img_label,fileButton,select_img);
        vbox.setAlignment(Pos.CENTER);
      gridPane.add(vbox, 1, 7);

//Adding buttons in gridpane
      savebutton.setStyle("-fx-background-color:green;-fx-font-weight:bold;-fx-text-fill:white");
       gridPane.add(savebutton,0,7);
        clear.setStyle("-fx-background-color:burlywood;-fx-font-weight:bold;");
        gridPane.add(clear,1,7);


        Scene scene = new Scene(gridPane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}