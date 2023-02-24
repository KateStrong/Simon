/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc
 * Class: SimonLightMain
 *
 * Description:
 * This class instantiates scenes and
 * contains the main method to play the game.
 * ****************************************
 */
package simonlightmvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simonlightmvc.model.SimonLightModel;
import simonlightmvc.view.InstructionsScreenView;
import simonlightmvc.view.StartScreenView;
import simonlightmvc.view.SimonLightScreenView;

public class SimonLightMain extends Application {
    private Stage stage;

    private SimonLightModel theModel;

    private SimonLightScreenView gameView;

    private StartScreenView startView;

    private InstructionsScreenView instructionsView;

    /** Different Scenes used in Simon */
    private Scene gameScene;
    private Scene startScene;
    private Scene instructionsScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        stage = primaryStage;

        // Creates different scenes
        startScene = createStartScene();
        gameScene = createGameScene();
        instructionsScene = createInstructionsScene();

        stage.setScene(startScene);
        stage.sizeToScene();

        // Make it not resizeable
        stage.setResizable(false);

        // set title
        stage.setTitle("SIMON");
        stage.show();
    }

    private Scene createGameScene() throws InterruptedException {
        gameScene = new Scene(gameView.getRoot());

        // attach a CSS file for styling our app
        gameScene.getStylesheets().add(
                getClass().getResource("/simonmvc.css").toExternalForm());

        SimonLightController theController = new SimonLightController(this.theModel, this.gameView);

        return gameScene;
    }

    /**
     * Creates the Start scene and switches to Game when start button pressed
     *
     * @return startScene
     * @throws InterruptedException
     */
    private Scene createStartScene() throws InterruptedException {
        this.startView.getStartButton().setOnAction(e -> switchScene(gameScene));
        this.startView.getInstructionButton().setOnAction(e -> switchScene(instructionsScene));

        startScene = new Scene(startView.getRoot());

        // attach a CSS file for styling welcome screen
        startScene.getStylesheets().add(
                getClass().getResource("/welcome.css").toExternalForm());

        return startScene;
    }

    private Scene createInstructionsScene() throws InterruptedException {
        this.instructionsView.getReturnButton().setOnAction(e -> switchScene(startScene));

        instructionsScene = new Scene(instructionsView.getRoot());

        // attach a CSS file for styling welcome screen
        instructionsScene.getStylesheets().add(
                getClass().getResource("/welcome.css").toExternalForm());

        return instructionsScene;
    }

    /**
     * Switches between scenes
     *
     * @param scene that is going to get set
     */
    public void switchScene(Scene scene) {
        stage.setScene(scene);
        stage.sizeToScene();
    }

    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new SimonLightModel();
        this.gameView = new SimonLightScreenView(this.theModel);
        this.startView = new StartScreenView();
        this.instructionsView = new InstructionsScreenView();
    }
}
