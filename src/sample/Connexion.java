package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Connexion implements Initializable
{
    @FXML
    private JFXTextField usernameTextField;

    @FXML
    private JFXTextField passwordTextField;

    @FXML
    void SignIn(ActionEvent event)
    {
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(usernameTextField, Validator.createEmptyValidator("Username is Required")) ;
        validationSupport.registerValidator(passwordTextField, Validator.createEmptyValidator("password is Required")) ;

        if (!usernameTextField.getText().isEmpty()&
                !passwordTextField.getText().isEmpty() )
        {
            if (usernameTextField.getText().trim().equals("admin")&& passwordTextField.getText().equals("1234"))
            {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/MainInterface.fxml"));
                    Scene scene =new Scene(root) ;
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
