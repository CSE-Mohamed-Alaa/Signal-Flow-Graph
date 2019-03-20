import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage stage;
    @FXML
    private Button go;
    @FXML
    private TextField nodes;

    @FXML
    private void goDraw() throws IOException {
        if (!nodes.getText().matches("\\d+")) {
            nodes.setText("Invalid Input!!");
            return;
        }
        stage = (Stage) go.getScene().getWindow();
        DrawController.setNodes(Integer.parseInt(nodes.getText()));
        BorderPane root = FXMLLoader.load(getClass().getResource("Design.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
