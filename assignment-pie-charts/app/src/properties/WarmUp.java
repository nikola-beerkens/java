package properties;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class WarmUp extends Application {
	private GridPane root = new GridPane();

	@Override
	public void start(Stage primaryStage) {
		root.setAlignment(Pos.CENTER);
		root.setHgap(20);
		root.setVgap(10);

		warmUp1();
		warmUp2();
		warmUp3();

		Scene scene = new Scene(root, 500, 300);
		primaryStage.setTitle("WarmUp Pie");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void warmUp1() {
		TextField textField = new TextField();
		root.add(textField, 0, 0);
		Label label = new Label();
		label.setMinWidth(200);
		label.setMaxWidth(200);

		// bind the text property of the label to the text property of the text
		// field, so that both show the same text
		// BEGIN your code here
		label.textProperty().bind(textField.textProperty());

		// END your code here

		root.add(label, 1, 0);
	}

	private void warmUp2() {
		Slider slider = mkSlider();

		root.add(slider, 0, 1);

		Label label = new Label();
		label.setMinWidth(200);
		label.setMaxWidth(200);

		// Bind the text property of the label to the value property of the slider.
		// The slider value is a Double, but the label expects a string. You have to
		// convert it!
		// BEGIN your code here
		label.textProperty().bind(slider.valueProperty().asString());

		// END your code here

		root.add(label, 1, 1);

	}

	private void warmUp3() {
		Slider slider1 = mkSlider();
		Slider slider2 = mkSlider();
		Slider slider3 = mkSlider();
		slider3.setMax(200);

		root.add(slider1, 0, 2);
		root.add(slider2, 1, 2);
		root.add(slider3, 2, 2);

		// Set the value property of slider3 to the sum of the values of slider1 and slider2.
		// When you move one of the sliders, slider3 should move automatically.
		// BEGIN your code here
		slider3.valueProperty().bind(slider1.valueProperty().add(slider2.valueProperty()));

		// END your code here
	}

	private static Slider mkSlider() {
		Slider slider = new Slider();
		slider.setMin(0);
		slider.setMax(100);
		slider.setValue(40);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(10);
		
		return slider;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
