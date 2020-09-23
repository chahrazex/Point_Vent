package sample;

import Connectivity.CennectionClass;
import Model.Client;
import Model.Prduit;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainInterface implements Initializable
{

    @FXML
    private Pane home_pane;

    @FXML
    private Pane FourniseurPane;

    /*-----------------------Produit------------------*/
    @FXML
    private Pane ProduiPane;
    @FXML
    private JFXTextField idProduitText;

    @FXML
    private JFXTextField nomProduitText;

    @FXML
    private JFXTextField prixProduitText;


    @FXML
    private JFXTextField qteProduitText;


    public ObservableList<Prduit> listP;
    public TableView<Model.Prduit> TableProduit ;
    public TableColumn<Prduit,Integer> idProd ;
    public TableColumn<Prduit,String> nameProd ;
    public TableColumn<Prduit,Integer> prixProd ;
    public TableColumn<Prduit,String> qteProd ;

    /*-----------------------Client------------------*/
    @FXML
    private Pane ClientPane;
    @FXML
    private JFXTextField idClientText;

    @FXML
    private JFXTextField nomClientText;

    @FXML
    private JFXTextField telClientText;

    @FXML
    private JFXTextArea adressClientText;
    public ObservableList<Client> listC;
    public TableView<Model.Client> TableClient ;
    public TableColumn<Client,Integer> idClient ;
    public TableColumn<Client,String> nameClient;
    public TableColumn<Client,Integer> telClient ;
    public TableColumn<Client,String> adressClient ;


    public  int indexC=-1 ,indexP=-1;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        /*---------------------------Produit----------------------------------------------*/
        idProd.setCellValueFactory(new PropertyValueFactory<Prduit,Integer>("id"));
        nameProd.setCellValueFactory(new PropertyValueFactory<Prduit,String>("name"));
        prixProd.setCellValueFactory(new PropertyValueFactory<Prduit,Integer>("prix"));
        qteProd.setCellValueFactory(new PropertyValueFactory<Prduit,String>("qte"));
        listP= CennectionClass.getProduit();
        TableProduit.setItems(listP);

        /*---------------------------Client----------------------------------------------*/
        idClient.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        nameClient.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
        telClient.setCellValueFactory(new PropertyValueFactory<Client,Integer>("tel"));
        adressClient.setCellValueFactory(new PropertyValueFactory<Client,String>("adress"));
        listC= CennectionClass.getClient();
        TableClient.setItems(listC);

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
    /*----------------------------------Produit---------------------------*/
    @FXML
    void ShowProduit(ActionEvent event)
    {
        ProduiPane.setVisible(true);
        home_pane.setVisible(false);
        ClientPane.setVisible(false);
        FourniseurPane.setVisible(false);
    }

    public void clearP()
    {
        nomProduitText.clear();
        idProduitText.clear();
        prixProduitText.clear();
        qteProduitText.clear();
    }
    public void AddProduit()
    {
        try {
            CennectionClass cennectionClass=new CennectionClass() ;
            int id =Integer.parseInt(idProduitText.getText());
            String name =nomProduitText.getText() ;
            int prix =Integer.parseInt(prixProduitText.getText());
            String qte =qteProduitText.getText();
            boolean result=cennectionClass.Add("Produit",id,name,prix,qte) ;
            if (!result)
            {
                listP.add(new Prduit(id ,name,prix,qte)) ;
                //numDrinks.setText(CennectionClass.count("No_drink","Drinks")+"");
                clearP();
            }

        }catch (Exception e) {}

    }
    public void  getSelectionP()
    {
        indexP=TableProduit.getSelectionModel().getSelectedIndex() ;
        if(indexP !=-1)
        {
            idProduitText.setText(idProd.getCellData(indexP).toString());
            nomProduitText.setText(nameProd.getCellData(indexP));
            prixProduitText.setText(prixProd.getCellData(indexP).toString());
            qteProduitText.setText(qteProd.getCellData(indexP).toString());
        }
    }
    public  void DeleteProduit()
    {
        try {
            int id =Integer.parseInt(idProduitText.getText());
            CennectionClass cennectionClass=new CennectionClass() ;
            cennectionClass.Delete("Produit","where idProd="+id);
           // numDrinks.setText(CennectionClass.count("idProd","Produit")+"");
            listP.remove(indexP);
        }catch (Exception e){}
        clearP();
    }
    public void  EditProduit()
    {
        try {
            CennectionClass cennectionClass=new CennectionClass() ;
            int id =Integer.parseInt(idProduitText.getText());
            String name =nomProduitText.getText() ;
            int prix =Integer.parseInt(prixProduitText.getText());
            String qte =qteProduitText.getText() ;
            cennectionClass.Edit("Produit","where idProd="+id ,name,prix,qte)  ;
            listP.set(indexP ,new Prduit(listP.get(indexP).getId() ,name,prix,qte)) ;
            clearP();
        }catch (Exception e){}

    }

    /*--------------------------------Client--------------------------------------*/
    @FXML
    void ShowClient(ActionEvent event)
    {
        ClientPane.setVisible(true);
        home_pane.setVisible(false);
        ProduiPane.setVisible(false);
        FourniseurPane.setVisible(false);
    }
    public void AddClient()
    {
        try {
            CennectionClass cennectionClass=new CennectionClass() ;
            int id =Integer.parseInt(idClientText.getText());
            String name =nomClientText.getText() ;
            int tel =Integer.parseInt(telClientText.getText());
            String adress =adressClientText.getText();
            boolean result=cennectionClass.Add("Client",id,name,tel,adress) ;
            if (!result)
            {
                listC.add(new Client(id ,name,tel,adress)) ;
                //numDrinks.setText(CennectionClass.count("No_drink","Drinks")+"");
                clearC();
            }

        }catch (Exception e) {}

    }
    public void  getSelectionC()
    {
        indexC=TableClient.getSelectionModel().getSelectedIndex() ;
        if(indexC !=-1)
        {
            idClientText.setText(idClient.getCellData(indexC).toString());
            nomClientText.setText(nameClient.getCellData(indexC));
            telClientText.setText(telClient.getCellData(indexC).toString());
            adressClientText.setText(adressClient.getCellData(indexC));
        }
    }
    public  void DeleteClient()
    {
        try {
            int id =Integer.parseInt(idClientText.getText());
            CennectionClass cennectionClass=new CennectionClass() ;
            cennectionClass.Delete("Client","where idClient="+id);
            // numDrinks.setText(CennectionClass.count("idProd","Produit")+"");
            listC.remove(indexC);
        }catch (Exception e){}
        clearC();
    }
    public void  EditClient()
    {
        try {
            CennectionClass cennectionClass=new CennectionClass() ;
            int id =Integer.parseInt(idClientText.getText());
            String name =nomClientText.getText() ;
            int tel =Integer.parseInt(telClientText.getText());
            String adress =adressClientText.getText() ;
            cennectionClass.Edit("Client","where idClient="+id ,name,tel,adress)  ;
            listC.set(indexC ,new Client(listC.get(indexC).getId() ,name,tel,adress)) ;
            clearC();
        }catch (Exception e){}

    }
    public void clearC()
    {
        nomClientText.clear();
        idClientText.clear();
        telClientText.clear();
        adressClientText.clear();
    }
    /*-----------------------------------Fourniseur------------------------------------------*/
    @FXML
    void ShowFourniseur(ActionEvent event)
    {
        FourniseurPane.setVisible(true);
        ClientPane.setVisible(false);
        home_pane.setVisible(false);
        ProduiPane.setVisible(false);
    }


}
