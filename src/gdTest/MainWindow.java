package gdTest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	boolean alternate = true;

	public static void main(String[]args){
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		Group root = new Group();

		int stageWidth = 1050;
		int stageHeight = stageWidth/2;

		Scene mainScene = new Scene(root, stageWidth, stageHeight, Color.rgb(225, 236, 255));

		BorderPane border = new BorderPane();
		root.getChildren().add(border);

		HBox hBox1 = new HBox();
		border.setTop(hBox1);

		hBox1.setAlignment(Pos.BASELINE_LEFT);
		hBox1.setPadding(new Insets(10, 7, 10, 7));
		hBox1.setSpacing(10);
		hBox1.setStyle("-fx-background-color: #99ccff;");
		hBox1.prefWidthProperty().bind(stage.widthProperty());

		Button btnNextDay = new Button("Next Day");
		Button btnExampleAnimals = new Button("Example Animals");
		Button btnExit = new Button("Exit");
		Button btnShowAllFriends = new Button("Show All Friends");
		hBox1.getChildren().addAll(btnNextDay, btnShowAllFriends, btnExampleAnimals, btnExit);

		StackPane stackLeft = new StackPane();
		stackLeft.setPadding(new Insets(20, 7, 20, 17));
		border.setLeft(stackLeft);

		ListView listViewAnimals = new ListView();
		listViewAnimals.setMaxWidth(stageWidth/6);
		listViewAnimals.setPadding(new Insets(10, 7, 10, 7));
		stackLeft.getChildren().add(listViewAnimals);

		//The stackpane for the center is needed for the Canvas, and so is created here, but all the actual
		//properties of the pane are set in the CanvasWithAnimals class
		StackPane stackCenter = new StackPane();
		border.setCenter(stackCenter);

		Canvas can = new Canvas();
		can.widthProperty().setValue(600);
		can.heightProperty().setValue(400);

		StackPane stackRight = new StackPane();
		stackRight.setPadding(new Insets(10, 45, 10, 7));
		border.setRight(stackRight);

		Rectangle rectRight = new Rectangle( stageWidth/6,  (can.getHeight()*2)/3 );
		rectRight.setFill(Color.LIGHTBLUE );

		VBox vBox1 = new VBox();
		vBox1.setPadding(new Insets(10, 7, 10, 7));
		vBox1.setSpacing(10);
		vBox1.setAlignment(Pos.CENTER_LEFT);
		stackRight.getChildren().addAll(rectRight, vBox1);

		Label labelName = new Label("Name: ");
		labelName.setMaxWidth(rectRight.getWidth()-10);
		Label labelAnimaltype = new Label("Animal: ");
		Label labelId = new Label("Id: ");
		Label labelFavoritefood = new Label("Favorite food: ");
		Label labelFriends = new Label("Friends: ");
		Label label6 = new Label(" ");
		Label label7 = new Label(" ");
		vBox1.getChildren().addAll(labelName, labelAnimaltype, labelId, labelFavoritefood, labelFriends, label6, label7);
		Label[] labels = {labelName, labelAnimaltype, labelId, labelFavoritefood, labelFriends, label6, label7}; 

		HBox hBox2 = new HBox();
		border.setBottom(hBox2);
		hBox2.setAlignment(Pos.CENTER_RIGHT);
		hBox2.setPadding(new Insets(10, 7, 10, 7));
		hBox2.setSpacing(10);
		hBox2.setStyle("-fx-background-color: #99ccff;");
		hBox2.prefWidthProperty().bind(stage.widthProperty());

		Text textCredits = new Text("Credits");
		textCredits.setFill(Color.GRAY);
		Rectangle rectCredits = new Rectangle(37, 15);
		rectCredits.setFill(Color.TRANSPARENT);

		StackPane stack2 = new StackPane();
		stack2.setPadding(new Insets(0, 30, 0, 0));
		stack2.getChildren().addAll(textCredits, rectCredits);

		hBox2.getChildren().addAll(stack2);


		AnimalManager manager = new AnimalManager();
		CanvasWithAnimals canAnimals = new CanvasWithAnimals(stackCenter, can);
		SelectedAnimalHandler selector = new SelectedAnimalHandler(listViewAnimals, labels);

		//Subclasses needs top communicate with each other, so they are linked
		canAnimals.linkOtherClasses(manager, selector);
		manager.linkOtherClasses(canAnimals, selector);
		selector.linkOtherClasses(canAnimals, manager);

		stage.setScene(mainScene);
		stage.show();


		//-------------------------------------------------------------------------------------
		//Events Setup-----------------------------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------

		btnNextDay.setOnMouseClicked(mouseClickedEvent->{
			canAnimals.clearLines();
			manager.randomizeFriendships();
			canAnimals.drawAllFriendships();
		});

		btnShowAllFriends.setOnMouseClicked(mouseClickedEvent->{
			canAnimals.clearLines();
			selector.deselectAnimal();
			canAnimals.drawAllFriendships();
		});
		
		alternate = true;
		btnExampleAnimals.setOnMouseClicked(mouseClickedEvent->{
			
			if(alternate){
				manager.addExampleAnimals();
				alternate = !alternate;
			}else{
				manager.addFunExampleAnimals();
				alternate = !alternate;
			}
			canAnimals.clearLines();
			canAnimals.clearOutline();
			manager.recalculatePositions();
			canAnimals.drawAnimals();
			listViewAnimals.getItems().clear();
			listViewAnimals.getItems().addAll( manager.getAnimalsList() );
			
		});

		btnExit.setOnMouseClicked(mouseClickedEvent->{
			System.exit(1);
		});


		can.setOnMouseMoved((mouseMovedEvent) ->{
			double mousePosX = mouseMovedEvent.getX();
			double mousePosY = mouseMovedEvent.getY();
			canAnimals.displayAnimal(mousePosX, mousePosY);
		});

		can.setOnMousePressed(mousePressedEvent->{
			double mousePosX = mousePressedEvent.getX();
			double mousePosY = mousePressedEvent.getY();
			canAnimals.selectAnimal(mousePosX, mousePosY);
		});
		

		rectCredits.setOnMouseEntered((mouseEnterEvent)->{
			textCredits.setFill(Color.WHITE);
		});

		rectCredits.setOnMouseExited((mouseExitEvent)->{
			textCredits.setFill(Color.GRAY);
		});

		rectCredits.setOnMouseClicked((mouseClickedEvent)->{
			CreditsWindow.display("Credits", "Oskar Henriksson", "Oskarh_work@outlook.com");
		});

		listViewAnimals.setOnMouseClicked(mouseClickedEvent->{
			
			String selectedItem = (String) listViewAnimals.getSelectionModel().getSelectedItem();
			if(selectedItem!=null){
				canAnimals.clearLines();
				selector.selectAnimal(selectedItem);
			}

		});

		mainScene.setOnMouseClicked(mouseClickedEvent->{
			if(mouseClickedEvent.getButton() == MouseButton.SECONDARY ){
				canAnimals.clearLines();
				selector.deselectAnimal();
			}
		});

	}

}

