package com.example.myloop;


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
@FXML
Text passMissMatch;
@FXML
Text wrongPass;
    @FXML
    TextField NewName;
    @FXML
    TextField Newpass1;
    @FXML
    TextField Newemail;
    @FXML
    TextField Newpass2;
   @FXML
    private VBox vbox=new VBox();
@FXML
TextField email;
@FXML
TextField password;
    @FXML
    private Parent fxml;
    @FXML
    Button LogIn;
    @FXML
    Button SignIn;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
int count=0;

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
  /*  String newname=NewName.getText();
    String newemail=Newemail.getText();
    String newPass1=Newpass1.getText();
    String newPass2=Newpass2.getText();
    String lname=lName.getText();
    String lpass1=lpass.getText();*/
    public void login(ActionEvent e) throws ClassNotFoundException, SQLException, IOException {
        String emailCheck = email.getText();
        String passCheck = password.getText();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/myloop","root","tasin#mysql");
        PreparedStatement ps = con.prepareStatement("select * from userinfo");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String email1 = rs.getString("Email");
            String password1 = rs.getString("Password");
            if (emailCheck.equals(email1) && passCheck.equals(password1)) {
                count = 1;
                Parent root = FXMLLoader.load(getClass().getResource("messagingUI.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
            else
            {
                password.clear();
                wrongPass.setText("Incorrect Password");
            }


        }
        if (count == 0)
            System.out.println("log in failed");


    }
    public void Signin(ActionEvent e) throws ClassNotFoundException, SQLException, IOException {
        String emailCheck = Newemail.getText();
        String passCheck = Newpass1.getText();
        String Name = NewName.getText();
        String passReCheck = Newpass2.getText();
        PreparedStatement pstmt = null;
        Connection conn=null;
        if(passCheck.equals(passReCheck)) {



         Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myloop", "root", "tasin#mysql");

            String query1 = "INSERT INTO userinfo (Name, email, password) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(query1);
            pstmt.setString(1, Name);
            pstmt.setString(2, emailCheck);
            pstmt.setString(3, passCheck);
            pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();

        if (conn != null)
            conn.close();
    }




     else
     {
         Newpass1.clear();
         Newpass2.clear();
         passMissMatch.setText("Password doesn't match");
     }

}}