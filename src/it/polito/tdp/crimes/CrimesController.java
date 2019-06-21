/**
 * Sample Skeleton for 'Crimes.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

import it.polito.tdp.model.Distretto;
import it.polito.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CrimesController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {
    	int anno = boxAnno.getValue();
    	model.creaGrafo(anno);
    	
    	for(Distretto d : model.getDistretti()) {
    		txtResult.appendText("Gli adiacenti di " + d + ": ");
    		for(Distretto a : model.getAdiacenti(d)) {
    			txtResult.appendText(a + ", ");
    		}
    		txtResult.appendText("\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	LocalDate data;
    	
    	try {
			data = LocalDate.of(boxAnno.getValue(), boxMese.getValue(), boxGiorno.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			txtResult.setText("Inserisci una data valida");
		}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;

    	boxAnno.getItems().addAll(model.getAnni());
    	boxMese.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
    	for(int i=1; i<=31; i++)
    		boxGiorno.getItems().add(i);
    }
}
