package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class IsItGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start( Stage stage ) {
        BorderPane borderPane = new BorderPane();

        GridPane gridPane = new GridPane();
        for (int col = 0; col < 2; ++col) {
            Button button = new Button(String.valueOf(col));
            gridPane.add(button, col, 0);
        }
        borderPane.setTop(gridPane);

        HBox hbox = new HBox();
        hbox.getChildren().add(new Label("Happy Summer!"));
        borderPane.setBottom(hbox);

        borderPane.setLeft(new Button("<<"));
        borderPane.setRight(new Button(">>"));

        Button theButton = new Button( "One" );
        borderPane.setCenter(theButton);

        stage.setScene( new Scene( borderPane ) );
        stage.sizeToScene();
        stage.show();
    }

}
