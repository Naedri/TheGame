package view.component;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import view.constant.BordW;
import view.constant.Col;
import view.constant.Rad;

public class DeckView extends Button {

	ICardView card;

	public DeckView(ICardView card) {
		super(Main.d.get("PLAY_draw"));
		this.card = card;
		setStyle();
	}

	public DeckView(ICardView card, String text, Node graphic) {
		super(text, graphic);
		setStyle();
	}

	public DeckView(ICardView card, String text) {
		super(text);
		setStyle();
	}

	private void setStyle() {
		this.setPrefSize(card.getPrefWidth(), card.getPrefHeight());
		this.setFont(card.getFont());
		this.setWrapText(true);
		this.setTextAlignment(TextAlignment.CENTER);
		// this.setRotate(270);
		this.setTextFill(card.getBackgroundColor());
		// this.setBorder(new Border(card.getBorderStrokes()));
		this.setBorder(new Border(new BorderStroke(card.getBackgroundColor(), BorderStrokeStyle.SOLID,
				new CornerRadii(Rad.MEDIUM.getRadius()), new BorderWidths(BordW.HIGH.getWidth()))));
		// this.setBackground(card.getBackground());
		this.setBackground(new Background(
				new BackgroundFill(card.getBorderColor(), new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY)));
		this.setWrapText(true);
	}

	public StackPane makeDeckSupported() {
		StackPane sp = new StackPane();
		sp.getChildren().addAll(this.makeDeckSupport(), this);
		// TODO Erase
		sp.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return sp;
	}

	public Rectangle makeDeckSupport() {
		Rectangle rect = new Rectangle(this.getPrefWidth() * 1.2, this.getPrefHeight() * 1.2, Col.BLACK.getColor());
		rect.setArcHeight(10);
		rect.setArcWidth(100);
		return rect;
	}

	public Rectangle makeCardSupportTrans() {
		Rectangle rect = this.makeDeckSupport();
		rect.setOpacity(rect.getOpacity() * 0.5);
		return rect;
	}

	private static String reverseText(String text) {
		StringBuilder sb = new StringBuilder();
		String sep = System.lineSeparator();
		Character c;
		for (int i = 0; i < text.length(); i++) {
			c = sb.charAt(i);
			sb.append(c);
			if (i < (text.length() - 2)) {
				sb.append(sep);
			}
		}
		return sb.toString();
	}

}
