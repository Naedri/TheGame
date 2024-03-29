/**
 * 
 */
package view.scene;

import application.Main;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.WindowEvent;
import view.constant.ColorApp;
import view.constant.InsetsApp;
import view.label.MainLabel;

/**
 * @author Adrien Jallais
 *
 */
public abstract class AMainScene extends Scene {
	private BorderPane border;
	private StackPane vbox;

	public AMainScene(Pane center) {
		super(new BorderPane());
		border = (BorderPane) (this.getRoot());
		border.setPadding(InsetsApp.LITTLE.getInsets()); // top right bottom left
		border.setCenter(center);
		border.setBottom(createSignaturePane());
		this.afterShow();
		// set background
		this.getBorder().setBackground(
				new Background(new BackgroundFill(ColorApp.BADL.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private Node createSignaturePane() {
		Label label = new MainLabel(Main.d.get("MAIN_Signature"));
		vbox = new StackPane();
		vbox.setPadding(new Insets(InsetsApp.HIGH.getTop(), InsetsApp.HIGH.getRight(),
				InsetsApp.HIGH.getBot() - this.getBorder().getInsets().getBottom(), InsetsApp.HIGH.getLeft()));
		vbox.getChildren().add(label);
		return vbox;
	}

	/**
	 * To be used by descendant in order to modify the center of the scene
	 * 
	 * @return the center of the border
	 */
	public Node getPane() {
		return border.getCenter();
	}

	/**
	 * 
	 * @return the center
	 */
	public BorderPane getBorder() {
		return this.border;
	}

	/**
	 * adding effects only when the scene is loaded
	 */
	public void afterShow() {
		Main.mainStage.setOnShown(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				triggerShow();
			}
		});
	}

	/**
	 * what are the effect done after the stage is loaded
	 */
	public void triggerShow() {
		// BY default do nothing
	}
}
