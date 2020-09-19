package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainInterface implements Initializable
{

    @FXML
    private Pane home_pane;
    @FXML
    private Pane ProduiPane;
    @FXML
    private Pane ClientPane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void  MouseEnterdPane(Event event)
    {
        Pane pane = ((Pane)event.getSource()) ;
        pane.setStyle("-fx-background-color :linear-gradient(to top left, #574A98, #9263A6) ; -fx-background-radius : 5");
    }
    public void  MouseExitPane(Event event)
    {
        Pane pane = ((Pane)event.getSource()) ;
        pane.setStyle("-fx-background-color :#ffffff ; -fx-background-radius : 5");
    }
    @FXML
    void ShowHome(ActionEvent event) {
        home_pane.setVisible(true);
        ProduiPane.setVisible(false);
        ClientPane.setVisible(false);

    }
    @FXML
    void ShowProduit(ActionEvent event)
    {
        ProduiPane.setVisible(true);
        home_pane.setVisible(false);
        ClientPane.setVisible(false);
    }
    @FXML
    void ShowClient(ActionEvent event)
    {
        ClientPane.setVisible(true);
        home_pane.setVisible(false);
        ProduiPane.setVisible(false);
    }


}
