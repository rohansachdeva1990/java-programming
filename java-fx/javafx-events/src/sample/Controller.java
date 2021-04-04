package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Responsible for listening on events.
 * <p>
 * User wont be able to interact with UI when event is being processed by the event handler.
 * So, it is not asynchronous like GWT.
 * <p>
 * <p>
 * Only UI Thread, can update the nodes associated with the node, we cannot use our defined threads.
 * There is a risk of messing around with the integrity of the javafx graph nodes id we allow custom threads to play
 * with them.
 */
public class Controller {

    // We need to tell Java runtime while creating an instantiating controller class.
    // Should have the same name as defined in  the fxml
    @FXML
    private TextField nameField;

    @FXML
    private Button helloButton;

    @FXML
    private Button byeButton;

    @FXML
    private CheckBox ourCheckbox;

    @FXML
    private Label ourLabel;

    @FXML
    public void initialize() {
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    @FXML // Optional
    public void onButtonClicked(ActionEvent e) {
        if (e.getSource().equals(helloButton)) {
            System.out.println("Hello, " + nameField.getText());
        } else if (e.getSource().equals(byeButton)) {
            System.out.println("Bye, " + nameField.getText());
        }

        Runnable task = () -> {
            try {
                String s = Platform.isFxApplicationThread()? "UI Thread" : "Background Thread";
                System.out.println("I am going to sleep on the: " + s);
                TimeUnit.MILLISECONDS.sleep(10000);
                Platform.runLater(() -> {
                    String st = Platform.isFxApplicationThread()? "UI Thread" : "Background Thread";
                    System.out.println("I am updating the label on the: " + st);
                    ourLabel.setText("We did something");
                });
            } catch (InterruptedException e1) {
            }
        };
        new Thread(task).start();

        if (ourCheckbox.isSelected()) {
            nameField.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }

    public void handleChange() {
        System.out.println("The checkbox is " + ((ourCheckbox.isSelected()) ? "checked" : "not checked"));
    }

}
