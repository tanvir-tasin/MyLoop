package com.example.myloop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MessagingController implements Initializable {
@FXML
    Button msgUIbtn;
@FXML
    TextArea textArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void setMsgUIbtn()
    {
textArea.setText("HIIIIII Everyone");
    }
}
