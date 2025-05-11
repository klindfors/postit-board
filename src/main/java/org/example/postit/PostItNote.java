package org.example.postit;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.w3c.dom.events.Event;

import javax.swing.plaf.basic.BasicComboBoxUI;

public class PostItNote extends BorderPane {

    double startX;
    double startY;

    public PostItNote(double x, double y) {//position for PostIt from mouse click
        relocate(x, y);
        Pane titleBar = new Pane();
        TextArea postIt = new TextArea();
        setTop(titleBar);
        this.setCenter(postIt);
        setPrefSize(200, 200);
        titleBar.setPrefSize(200, 30);
        titleBar.setBackground(Background.fill(Color.YELLOW));
        postIt.setStyle("-fx-font: 14px 'Courier New'; -fx-control-inner-background: #fafa82");

        setOnMouseDragged(new DragHandler());
        setOnMousePressed(new StartDrag());
        setOnKeyPressed(new KeyHandler());

        titleBar.setOnMouseClicked((event) -> {
                    titleBar.setBackground(Background.fill(Color.BLUE));
                    requestFocus();
                }
        );
        focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                requestFocus();
                titleBar.setBackground(Background.fill(Color.YELLOW));
            } else {
                titleBar.setBackground(Background.fill(Color.GRAY));
            }
        });
    }




        class StartDrag implements EventHandler<MouseEvent> {
            @Override
            public void handle(MouseEvent mouseEvent) {
                startX = mouseEvent.getX();
                startY = mouseEvent.getY();
            }
        }

        class DragHandler implements EventHandler<MouseEvent> {
            //updates position for each pixel during the dragging of the postit
            @Override
            public void handle(MouseEvent mouseEvent) {
                double newX = getLayoutX() + mouseEvent.getX() - startX;
                double newY = getLayoutY() + mouseEvent.getY() - startY;
                relocate(newX,newY);
            }
        }

        class KeyHandler implements EventHandler<KeyEvent>{
            @Override
            public void handle(KeyEvent keyEvent) {
                double x = getLayoutX();
                double y = getLayoutY();
                switch(keyEvent.getCode()){//getCode returns enum for all character
                    case DOWN:  y += 10; break;
                    case LEFT:  x -= 10; break;
                    case RIGHT: x += 10; break;
                    case UP:  y -= 10; break;

                }
                keyEvent.consume();
                relocate(x,y);

            }
        }



}
