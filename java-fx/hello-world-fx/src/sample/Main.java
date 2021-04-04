package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // All the gui elements are created at this moment, GridPane is the root node
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Manually doing the same as sample.fxml
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);

        // Base container - primary stage
        primaryStage.setTitle("Hello World JavaFx");
        primaryStage.setScene(new Scene(root, 300, 275));

        // Adding elements
        Label greeting = new Label("Welcome to JavaFx!");

        greeting.setTextFill(Color.GREEN);
        greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));

        // To add an element
        root.getChildren().add(greeting);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
