package controller;

import java.awt.TextField;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class ContRegister {
	 @FXML
	    private TextField name;

	    @FXML
	    private TextField id;

	    @FXML
	    private ChoiceBox<?> gener;

	    @FXML
	    private ChoiceBox<?> song;



	    @FXML
	    void register(ActionEvent event) {

	    }

	    @FXML
	    void Continue(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("Window.fxml"));
	    	Parent viewCampo = loader.load();
	    	Scene scene = new Scene(viewCampo);
	    	Stage windowCampo = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	windowCampo.setScene(scene);
	    	windowCampo.show();
	    }
}
