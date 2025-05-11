package org.example.postit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PostItBoard extends Application {
    Pane center;
    Button newButton;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        FlowPane top = new FlowPane();
        center = new Pane();
        root.setTop(top);
        root.setCenter(center);
        newButton = new Button("New note");
        newButton.setOnAction(new NewButtonHandler());

        top.getChildren().add(newButton);
        top.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 1000, 500);
        stage.setScene(scene);
        stage.show();
    }

    class NewButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent aEvent) {
            center.setOnMouseClicked(new ClickHandler());
            newButton.setDisable(true);
        }
    }
    class ClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent mEvent) {//determine where click happened, create new postit in center area
            double x = mEvent.getX();
            double y = mEvent.getY();
            PostItNote note = new PostItNote(x, y);
            center.getChildren().add(note); //adds note to center pane
            newButton.setDisable(false); //button clickable again
            center.setOnMouseClicked(null);
        }
    }

    /*class SaveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                WritableImage image = center.snapshot(null, null);

                BufferedImage bufferedImage = SwingFXUtils.fromfXImage(image, null);
                ImageIO.write(bufferedImage, "png", new File("capture.png"));

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "IO Error");
                alert.showAndWait();
            }

        }
    }
    */


    public static void main(String[] args) {
        launch();
    }

}
