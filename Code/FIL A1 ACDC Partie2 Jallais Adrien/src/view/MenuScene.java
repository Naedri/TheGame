package view;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.button.Button_Load_Scene;

public class MenuScene extends MainScene {
	private VBox vbox;

	private Button_Load_Scene bPH;
	private Button_Load_Scene bPIA;
	private Button_Load_Scene bP;
	private Button_Load_Scene bR;
	private Button_Load_Scene bA;
	private Button_Load_Scene bE;

	public MenuScene() {
		super(new VBox());
		vbox = (VBox) (super.getPane());
		bPH = new Button_Load_Scene(Main.d.get("MENU_button_playHuman"));
		bPIA = new Button_Load_Scene(Main.d.get("MENU_button_playIA"));
		bP = new Button_Load_Scene(Main.d.get("MENU_button_parameters"));
		bR = new Button_Load_Scene(Main.d.get("MENU_button_rules"));
		bA = new Button_Load_Scene(Main.d.get("MENU_button_authors"));
		bE = new Button_Load_Scene(Main.d.get("MENU_button_exit"));
		addingButtonEvent();
		createMenuBox();

		// TODO Erase
		vbox.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void addingButtonEvent() {
		bPH.setOnAction(new ButtonHandler());
		bPIA.setOnAction(new ButtonHandler());
		bP.setOnAction(new ButtonHandler());
		bR.setOnAction(new ButtonHandler());
		bA.setOnAction(new ButtonHandler());
		bE.setOnAction(new ButtonHandler());
	}

	private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			if (e.getSource() == bPH) {
				System.out.println("ererer");
			} else if (e.getSource() == bPIA) {

			} else if (e.getSource() == bP) {
			} else if (e.getSource() == bR) {
			} else if (e.getSource() == bA) {
			} else if (e.getSource() == bE) {
			}
		}
	}

	/*
	 * 
	 */
	private void createMenuBox() {
		vbox.setSpacing(12);
		vbox.setMaxWidth(400);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(bPH, bPIA, bP, bR, bA, bE);
	}

}
