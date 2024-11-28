
package com.example.login_form_1;
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

import java.io.*;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(10));

        Label userNameLabel = new Label("User Name");
        TextField userNameTextField = new TextField();
        Label pass = new Label("Password");
        PasswordField p_wrd = new PasswordField();


        Image topbanner=new Image(this.getClass().getResource("/com/example/login_form_1/form2.png").toExternalForm());
        ImageView imageView=new ImageView(topbanner);
        imageView.fitWidthProperty().bind(gridPane.widthProperty());
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(false);
        gridPane.add(imageView,0,0,2,1);

        //1st hbox for 1st field
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        userNameTextField.setStyle("-fx-border-color:burlywood;-fx-border-width:2px");
        hbox.setStyle("-fx-font-size:12px;-fx-font-weight:bold");
        hbox.getChildren().addAll(userNameLabel, userNameTextField);
        gridPane.add(hbox, 1, 1);

        //2nd hbox for 2nd field
        HBox hbox2 = new HBox();
        hbox2.setSpacing(20);
        pass.setStyle("-fx-border-color:burlywood;-fx-border-width:2px");
        hbox2.setStyle("-fx-font-size:12px;-fx-font-weight:bold");
        hbox2.getChildren().addAll(pass, p_wrd);
        gridPane.add(hbox2, 1, 2);

        try {
            File file = new File("save_file.txt");
            if (!file.exists()) {  // Check if file exists
                file.createNewFile();  // Create new file if it doesn't exist
                System.out.println("File created successfully.");
            }
            // Writing to the file
            FileWriter writer = new FileWriter(file);
            writer.write("Javairia oki.123");
            writer.flush();  // Ensure content is written to the file
            System.out.println("File written successfully.");


        Button login_button =new Button("Login");
        login_button.setOnAction(e->{
            boolean temp = false;
            try {
                String enteredUserName = userNameTextField.getText();
                String enteredPassword = p_wrd.getText();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String userDetails = "";
                while ((userDetails = bufferedReader.readLine()) != null) {
                    String user[] = userDetails.split(" ");

                   if (user[0].equals(enteredUserName) && user[1].equals(enteredPassword))  {
                         temp = true;
                        break;
                    }
                }
                bufferedReader.close();
                if(temp==true){
                    open_next(enteredUserName,stage);
                } else if (temp==false) {
                    Label errorLabel = new Label("Incorrect username or password!");
                    errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                    gridPane.add(errorLabel, 1, 4);
                }
            }
            catch(IOException io){

            }

        });
            login_button.setStyle("-fx-background-color:green;-fx-font-weight:bold;-fx-text-fill:white");
            gridPane.add(login_button,0,5);

        Button exit =new Button("Exit");
            gridPane.add(exit,1,5);
            exit.setOnAction(e->{
                stage.close();
            });
        }catch (IOException e) {
            e.getMessage();
        }

        Scene scene = new Scene(gridPane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }
    private void open_next(String userName, Stage stage) {

        VBox nextPageLayout = new VBox(20);
        nextPageLayout.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("Welcome, " + userName);
        nextPageLayout.getChildren().add(welcomeLabel);
        Scene nextPageScene = new Scene(nextPageLayout, 300, 150);
        stage.setTitle("Welcome");
        stage.setScene(nextPageScene);
    }


    public static void main(String[] args) throws IOException {
        launch();
    }
}