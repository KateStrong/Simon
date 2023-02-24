/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc.view
 * Class: start
 *
 * Description:
 * View of the start screen, which contains a button for instructions
 *  and a button to enter the game.
 * ****************************************
 */
package simonlightmvc.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StartScreenView {
    private VBox root;
    private Button startButton;
    private Button instructionButton;
    private Label title;

    public VBox getRoot() {
        return root;
    }

    public Button getStartButton() { return startButton; }

    public Button getInstructionButton() { return instructionButton; }

    public StartScreenView(){
        initSceneGraph();
    }

    private void initSceneGraph() {
        this.root = new VBox();

        this.title = new Label("Simon");

        this.startButton = new Button("Enter Game");
        this.instructionButton = new Button("How to Play");

        this.root.getChildren().add(title);
        this.root.getChildren().add(startButton);
        this.root.getChildren().add(instructionButton);
    }
}