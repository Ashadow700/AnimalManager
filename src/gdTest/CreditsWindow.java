package gdTest;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreditsWindow {
	
	public static void display(String windowTitle, String creator, String email){
		Stage popupWindow = new Stage();
		popupWindow.initModality(Modality.APPLICATION_MODAL);
		popupWindow.setTitle(windowTitle);
		
		Label labelCreator = new Label("Created by: "+creator);
		Label labelEmail = new Label("E-mail: "+email);
		
		Button btnClose = new Button("Close");
		btnClose.setOnMouseClicked((mouseClickedEvent)-> popupWindow.close() );
		
		VBox layout = new VBox();
		layout.getChildren().addAll(labelCreator, labelEmail, btnClose);
		layout.setSpacing(10);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10, 7, 10, 7));
		
		Scene scene = new Scene(layout);
		popupWindow.setScene(scene);
		popupWindow.showAndWait();
		
	}

}
