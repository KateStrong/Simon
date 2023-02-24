/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc
 * Class: SimonLightController
 *
 * Description:
 * The SimonLightController binds the model and view of the simon game.
 * It regulates game flow by responding to user clicks and adjusting the
 * game state accordingly.
 * ****************************************
 */
package simonlightmvc;

import javafx.scene.control.Alert;
import javafx.scene.shape.SVGPath;
import simonlightmvc.model.Light;
import simonlightmvc.model.SimonLightModel;
import simonlightmvc.model.SimonLightPattern;
import simonlightmvc.view.SimonLightScreenView;

/**
 * These are the game states used in controlling the game flow
 * in {@link SimonLightController}
 */
enum GameState {
    NEW_GAME,
    GUESS,
    SEQUENCE_BUMP,
    GAME_LOST,
    GAME_WON
}

public class SimonLightController {

    /** Amount of time a light turns on when a pattern is played */
    private static final int ON_MS = 600;

    /** Amount of time a light stays off when a pattern is played */
    private static final int OFF_MS = 400;

    /** number of simon lights */
    private static final int NUM_OF_LIGHTS = 4;

    /** The length of the full pattern */
    private static final int PATTERN_LENGTH = 10;

    /** A pattern instance */
    private SimonLightPattern pattern;

    /** The length of the section of pattern being played */
    private int playLength;

    /** A count that iterates through the section of pattern being played */
    private int trackingCount;

    /** the play state the game is in */
    private GameState state;

    /** The user's score */
    private int score;

    /** an instance of the simon light model */
    private SimonLightModel theModel;

    /** an instance of the simon light view */
    private SimonLightScreenView theView;


    public SimonLightController(SimonLightModel theModel, SimonLightScreenView theView) throws InterruptedException {
        this.theModel = theModel;
        this.theView = theView;

        initBindings();
    }

    /**
     * Bind the view and model
     */
    private void initBindings() {
        //Set display scale for the lights
        for (SVGPath c : theView.getLights()) {
            c.setScaleX(0.5);
            c.setScaleY(0.5);
        }

        // bind the color of the light in the view to the model light color
        for (int i = 0; i < theModel.getLights().size(); i++) {
            Light lightModel = theModel.getLight(i);
            SVGPath lightView = theView.getLight(i);
            int lightInt = i;

            lightView.fillProperty().bind(lightModel.currentColorProperty());

            //set up an event if the user clicks a light
            lightView.onMouseClickedProperty().setValue(event -> {
                serviceLightClick(lightModel, lightInt);
            });
        }

        this.theView.getStartButton().setOnAction(event -> {
            startGame();
        });

        this.theView.getPlayButton().setOnAction(event -> {
            playLights();
        });
    }

    /**
     * Called when a user clicks a light. Blinks the light and checks if
     * the correct light was clicked.
     *
     * @param lightModel
     * @param i the int associated with a specific light.
     */
    private void serviceLightClick(Light lightModel, int i) {
        lightModel.turnOnFOrMs(ON_MS);
        checkUserChoice(i);
    }

    /**
     * Starts the game and instantiates variables.
     */
    private void startGame() {
        this.pattern = new SimonLightPattern(PATTERN_LENGTH, NUM_OF_LIGHTS);
        this.playLength = 1;
        this.trackingCount = 0;

        // sets score to 0 and adjusts label to 0 or back to 0
        this.score=0;
        theView.score.setText("Score: "+score);

        this.state = GameState.NEW_GAME;
        theView.getPlayButton().fire();
    }

    /**
     * Based on game state.
     * Either plays pattern or alertEndGame based on won or lost.
     */
    private void playLights() {
        if (this.state == null) {
            return;
        }
        switch (this.state) {
            case NEW_GAME:
            case SEQUENCE_BUMP:
                playPattern();
                break;
            case GAME_WON:
                this.score++;
                theView.score.setText("Score: "+score);
                alertEndGame("WINNER! Score: " + this.score);
                break;
            case GAME_LOST:
                alertEndGame("LOSER. Score: " + this.score);
                break;
            default:
                break;
        }
    }

    /**
     * Takes in string and creates the alert when game is over
     *
     * @param s string stating either won or lost
     */
    private void alertEndGame(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setContentText(s);
        alert.show();
    }

    /**
     * Plays the light pattern for the current portion of sequence
     */
    private void playPattern() {
        int[] pat = this.pattern.getPortionOfSequence(this.playLength);

        Runnable r = () -> {
            try {
                Thread.sleep(700);
                for (int i = 0; i < pat.length; i++) {
                    this.theModel.getLight(pat[i]).turnOn();
                    Thread.sleep(ON_MS);
                    this.theModel.getLight(pat[i]).turnOff();
                    Thread.sleep(OFF_MS);
                }
            }
            catch (InterruptedException e) {
                System.out.println("in catch");
            }
        };

        // encapsulate our Runnable in a thread and start it
        Thread t = new Thread(r);
        t.start();

        this.state = GameState.GUESS;
    }

    /**
     * Checks user light choice against expected light in pattern.
     * Sees if these lights match.
     * Modifies game state accordingly.
     *
     * @param i the integer associated with a particular light choice.
     */
    private void checkUserChoice(int i) {
        if (this.state == null) {
            return;
        }
        if (this.state != GameState.GUESS) {
            return;
        }
        if (this.pattern.checkLight(this.trackingCount, i)) {
            this.trackingCount++;
            if (this.trackingCount == PATTERN_LENGTH) {
                this.state = GameState.GAME_WON;
                theView.getPlayButton().fire();
                return;
            }

            if (trackingCount == playLength) {
                playLength++;
                this.state = GameState.SEQUENCE_BUMP;
                this.score ++;
                theView.score.setText("Score: "+score);

                trackingCount = 0;
                theView.getPlayButton().fire();
                return;
            }

            this.state = GameState.GUESS;
            return;
        }

        this.state = GameState.GAME_LOST;
        theView.getPlayButton().fire();
    }
}
