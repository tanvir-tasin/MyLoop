package com.example.myloop;


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {/*
@FXML
    TextField lName;
@FXML
TextField lpass;
    @FXML
    TextField NewName;
    @FXML
    TextField Newpass1;
    @FXML
    TextField Newemail;
    @FXML
    TextField Newpass2;
*/    @FXML
    private VBox vbox=new VBox();
@FXML
Text text1;
@FXML
Text text2;
    @FXML
    private Parent fxml;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //SwingNode swingNode=new SwingNode();
        TranslateTransition t=new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*15);
        t.play();
        t.setOnFinished((e)->{
                    try {
                        fxml= FXMLLoader.load(getClass().getResource("Login.fxml"));
                        vbox.getChildren().removeAll();
                        vbox.getChildren().setAll(fxml);

                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
        );
    }
    @FXML
    private void opensignin(ActionEvent event)
    {
        TranslateTransition t=new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*15);
        t.play();
        t.setOnFinished((e)->{
                    try {

                        fxml= FXMLLoader.load(getClass().getResource("Login.fxml"));
                        vbox.getChildren().removeAll();
                        vbox.getChildren().setAll(fxml);

                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
        );

    }
    @FXML
    private void opensignup(ActionEvent event)
    {
        TranslateTransition t=new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e)->{
                    try {

                        fxml= FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
                        vbox.getChildren().removeAll();
                        vbox.getChildren().setAll(fxml);

                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
        );

    }
    /*String newname=NewName.getText();
    String newemail=Newemail.getText();
    String newPass1=Newpass1.getText();
    String newPass2=Newpass2.getText();
    String lname=lName.getText();
    String lpass1=lpass.getText();*/

}