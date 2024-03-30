package com.example.myloop;

import com.example.myloop.serverside.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessagingController implements Initializable {
    private Stage stage;
@FXML
    Button showservtnerb;
@FXML
    TextArea textArea;
@FXML
Button msgSendbtn;
@FXML
Button fileSendbtn;


    @FXML
    private TextField textField;
    BufferedReader br;
    BufferedWriter bw;
    boolean isconnected = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void Sendmsg() {
        String name = textField.getText();
        if (!isconnected) {
            textField.clear();

            if (name == null || name.isEmpty()) {
                textArea.appendText("Enter a valid input.\n");
                return;
            }

            try {
                Socket client = new Socket("localhost", 6666);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(client.getOutputStream());
                bw = new BufferedWriter(outputStreamWriter);
                InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
                br = new BufferedReader(inputStreamReader);
                Thread thread=new Thread(){
                    @Override
                    public void run()
                    {
                        while(true)
                        {
                            try
                            {
                                String msg= br.readLine();

                                textArea.appendText(msg+"\n");
                            }
                            catch (SocketException e) {

                                break;
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                        }
                    }
                };
                thread.start();

                textArea.appendText("Connection is established\n");
                bw.write(name+"\n");
                bw.flush();
                isconnected=true;
                msgSendbtn.setText("Send");
                textField.setPromptText("Write your message");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            String msg=textField.getText();
            textField.clear();
            try {
                if(msg==null||msg.isEmpty())
                {
                    return;    }
                bw.write(msg+"\n");
                bw.flush();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
    @FXML
    public void setMsgUIbtn()
    {


    }
    FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("Text Files", "*.txt");
    FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("All Files", "*.*");


    @FXML
    public void filechoosebtn(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("open files");
        fileChooser.getExtensionFilters().addAll(ex1, ex2);
        File selecfile = fileChooser.showOpenDialog(stage);
        if (selecfile != null) {
            Server filesend=new Server(selecfile.getPath());
            System.out.println(selecfile.getPath());
        }


    }


}
