package client.gui;

import client.controller.ConcentrationController;
import client.model.ConcentrationModel;
import client.model.Observer;
import common.ConcentrationException;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

/**
 * A JavaFX GUI client for the network concentration game.  It represent the "View"
 * component of the MVC architecture in use here.
 *
 * @author RIT CS
 * @author Nihal Wadhwa
 */

public class ConcentrationGUI extends Application implements Observer<ConcentrationModel, ConcentrationModel.CardUpdate> {
    /** the model */
    private ConcentrationModel model;

    /** the controller */
    private ConcentrationController controller;

    /** Moves label */
    private Label moves;

    /** Matches label */
    private Label matches;

    /** OK label */
    private Label gameStatus;

    /** grid of buttons */
    private PokemonButton[][] buttons;

    /***
     * Takes the arguments and creates a host and port. Also creates the model and controller and adds the observer.
     */
    @Override
    public void init() {
        List<String> args = getParameters().getRaw();

        // get host and port from command line
        String host = args.get(0);
        int port = Integer.parseInt(args.get(1));

        // create the model, and add ourselves as an observer
        this.model = new ConcentrationModel();


        // initiate the controller
        try
        {
            this.controller = new ConcentrationController(host, port, this.model);
        }
        catch (ConcentrationException e)
        {
            e.printStackTrace();
        }

        this.model.addObserver(this);
    }

    /***
     * Creates the GUI representation of the Concentration game.
     * @param stage stage of Java GUI
     */
    @Override
    public void start(Stage stage){
        // create the border pane that holds the grid and labels
        BorderPane borderPane = new BorderPane();

        // get the grid pane from the helper method
        GridPane gridPane = makeGridPane(model.getDIM());
        borderPane.setCenter(gridPane);

        //create BorderPane with labels tracking game status

        this.moves = new Label("Moves: ");
        moves.setAlignment(Pos.CENTER);

        this.matches = new Label("Matches: ");
        matches.setAlignment(Pos.CENTER);

        this.gameStatus = new Label("OK ");
        gameStatus.setAlignment(Pos.CENTER);

        BorderPane borderPane2 = new BorderPane();
        borderPane2.setLeft(moves);
        borderPane2.setCenter(matches);
        borderPane2.setRight(gameStatus);
        borderPane.setBottom(borderPane2);

        // store the grid into the scene and display it
        Scene scene = new Scene(borderPane);
        stage.setTitle("Concentration GUI");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }



    /**
     * A helper function that builds a grid of buttons to return.
     *
     * @return the grid pane
     */
    private GridPane makeGridPane(int DIM)
    {
        GridPane gridPane = new GridPane();
        this.buttons = new PokemonButton[DIM][DIM];
        int i = 0;
        for (int row=0; row<DIM; ++row)
        {
            for (int col=0; col<DIM; ++col)
            {
                // get the next type of pokemon and create a button for it
                PokemonButton button = new PokemonButton(row, col);
                gridPane.add(button,col, row);
                buttons[row][col] = button;
            }
        }
        return gridPane;
    }


    /***
     * Updates the GUI based on changes occurring in the game
     * @param model the Concentration Model of the game instance
     * @param card card that is being updated in the game
     */
    @Override
    public void update(ConcentrationModel model, ConcentrationModel.CardUpdate card)
    {
        Platform.runLater(()->
        {

            matches.setText("Matches: " +model.getNumMatches());
            moves.setText("Moves: " +model.getNumMoves());
            if(model.getStatus() == ConcentrationModel.Status.GAME_OVER)
                gameStatus.setText("GAME_OVER");
            else if(model.getStatus() == ConcentrationModel.Status.ERROR)
                gameStatus.setText("ERROR");
            if(card != null)
            {
                int row = card.getRow();
                int col = card.getCol();
                buttons[row][col].updateButton(card);

            }
        });


    }


    /**
     * Stops controller and all it's functions
     */
    @Override
    public void stop()
    {
        if(this.controller != null)
            this.controller.close();
    }


    /***
     * Launches the application using the host and port obtained from args
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2)
        {
            System.out.println("Usage: java ConcentrationGUI host port");
            System.exit(-1);
        }
        else
        {
            Application.launch(args);
        }
    }

    /***
     * Class that emulates a single button in the Concentration GUI
     */
    private class PokemonButton extends Button
    {
        //row of Button
        private int row;

        //column of button
        private int col;

        //Map of all pokemons as well as the card values they correspond to.
            private Map<String, String> pokemons;


        /**
         * Makes new pokemon button and declares all global variables. Also sets image and action of button.
         * @param row row of button in the Grid Pane
         * @param col column of button in the Grid Pane
         */
        public PokemonButton(int row, int col) {

            this.row = row;
            this.col = col;
            this.pokemons = new HashMap<>();

            this.pokemons.put("A", "abra.png");
            this.pokemons.put("B", "bulbasaur.png");
            this.pokemons.put("C", "charizard.png");
            this.pokemons.put("D", "diglett.png");
            this.pokemons.put("E", "golbat.png");
            this.pokemons.put("F", "golem.png");
            this.pokemons.put("G", "jigglypuff.png");
            this.pokemons.put("H", "magikarp.png");
            this.pokemons.put("I", "meowth.png");
            this.pokemons.put("J", "mewtwo.png");
            this.pokemons.put("K", "natu.png");
            this.pokemons.put("L", "pidgey.png");
            this.pokemons.put("M", "pikachu.png");
            this.pokemons.put("N", "poliwag.png");
            this.pokemons.put("O", "psyduck.png");
            this.pokemons.put("P", "rattata.png");
            this.pokemons.put("Q", "slowpoke.png");
            this.pokemons.put("R", "snorlak.png");
            this.pokemons.put("S", "squirtle.png");

            this.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/pokeball.png"))));
            this.setOnAction(event -> controller.revealCard(row,col));


        }


        /***
         * Updates button by changing image and action based on card being updated.
         * @param card card being updated in the Concentration Model
         */
        public void updateButton(ConcentrationModel.CardUpdate card)
        {
            if(card.isRevealed())
            {
                this.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/"+pokemons.get(card.getVal())))));
                this.setOnAction(null);
            }
            else
            {
                this.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/pokeball.png"))));
                this.setOnAction(event -> controller.revealCard(row,col));
            }
        }
    }
}
