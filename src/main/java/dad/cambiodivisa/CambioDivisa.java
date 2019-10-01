package dad.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField divisaOrigen;
	private TextField divisaDestino;
	private ComboBox<String> comboOrigen;
	private ComboBox<String> comboDestino;
	private Button botonCambio;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		divisaOrigen = new TextField();
		divisaOrigen.setPromptText("0");
		divisaOrigen.setMaxWidth(60);
		
		divisaDestino = new TextField();
		divisaDestino.setPromptText("0");
		divisaDestino.setMaxWidth(60);
		divisaDestino.setEditable(false);
		
		comboOrigen = new ComboBox<String>();
		comboOrigen.getItems().addAll("Euro","Libra","Dolar","Yen");
		comboOrigen.setPromptText("Euro");
		comboOrigen.getSelectionModel().selectFirst();
		
		comboDestino = new ComboBox<String>();
		comboDestino.getItems().addAll("Yen", "Euro","Libra","Dolar");
		comboDestino.setPromptText("Yen");
		comboDestino.getSelectionModel().selectFirst();
		
		botonCambio = new Button("Cambiar");
		botonCambio.setDefaultButton(true);
		botonCambio.setOnAction(e -> onCambiarButtonAction(e));
		
		HBox boxCambiar = new HBox(5,divisaOrigen,comboOrigen); 
		boxCambiar.setAlignment(Pos.CENTER);
		
		HBox boxCambio = new HBox(5,divisaDestino,comboDestino);
		boxCambio.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5,boxCambiar,boxCambio,botonCambio);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Iniciar Sesion");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

	private void onCambiarButtonAction(ActionEvent e) {
		try {
			Double cantidad = Double.parseDouble(divisaOrigen.getText());
			String aCambiar = comboOrigen.getSelectionModel().getSelectedItem();
			String Cambiado = comboDestino.getSelectionModel().getSelectedItem();
			
			Divisa euro = new Divisa("Euro", 1.0);
			Divisa libra = new Divisa("Libra", 0.8873);
			Divisa dolar = new Divisa("Dolar", 1.2007);
			Divisa yen = new Divisa("Yen", 133.59);
			Divisa origen = euro;
			Divisa destino = yen;
			switch (aCambiar) {
			case "Euro":
				origen = euro;
				break;
			case "Libra":
				origen = libra;
				break;
			case "Dolar":
				origen = dolar;
				break;
			case "Yen":
				origen = yen;
				break;
			}
			switch (Cambiado) {
			case "Euro":
				destino = euro;
				break;
			case "Libra":
				destino = libra;
				break;
			case "Dolar":
				destino = dolar;
				break;
			case "Yen":
				destino = yen;
				break;
			}
			String resultado = ""+(Divisa.fromTo(origen, destino, cantidad));
			divisaDestino.setText(resultado);
		} catch (Exception e2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cambio Divisa");
			alert.setHeaderText("Error");
			alert.setContentText("El número introducido no es válido");

			alert.showAndWait();
		}
		 
		
	}

	public static void main(String[] args) {

		launch(args);
		
	}

}
