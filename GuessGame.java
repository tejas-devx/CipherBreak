import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Random;

public class GuessGame extends Application {

    int randomNumber;
    int attempts = 0;

    Random random = new Random();

    @Override
    public void start(Stage stage) {

        randomNumber = random.nextInt(100) + 1;

        Label title = new Label("█ HACK TERMINAL v1.0 █");
        title.setFont(Font.font("Consolas", 26));
        title.setTextFill(Color.LIME);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.LIME);
        glow.setRadius(20);
        title.setEffect(glow);

        Label instruction = new Label("ENTER TARGET CODE (1-100)");
        instruction.setTextFill(Color.LIME);
        instruction.setFont(Font.font("Consolas", 14));

        TextField input = new TextField();
        input.setPromptText("Enter number...");
        input.setMaxWidth(200);

        input.setStyle(
                "-fx-background-color:black;" +
                "-fx-text-fill:#00ff00;" +
                "-fx-border-color:#00ff00;" +
                "-fx-font-family:Consolas;"
        );

        Button guessButton = new Button("EXECUTE");
        guessButton.setStyle(
                "-fx-background-color:black;" +
                "-fx-text-fill:#00ff00;" +
                "-fx-border-color:#00ff00;" +
                "-fx-font-family:Consolas;"
        );

        Button resetButton = new Button("RESET SYSTEM");
        resetButton.setStyle(
                "-fx-background-color:black;" +
                "-fx-text-fill:#00ff00;" +
                "-fx-border-color:#00ff00;" +
                "-fx-font-family:Consolas;"
        );

        Label status = new Label("SYSTEM STATUS: AWAITING INPUT...");
        status.setTextFill(Color.LIME);
        status.setFont(Font.font("Consolas", 13));

        Label attemptLabel = new Label("ATTEMPTS: 0");
        attemptLabel.setTextFill(Color.LIME);
        attemptLabel.setFont(Font.font("Consolas", 13));

        guessButton.setOnAction(e -> {

            try {

                int guess = Integer.parseInt(input.getText());
                attempts++;

                attemptLabel.setText("ATTEMPTS: " + attempts);

                if (guess > randomNumber) {

                    status.setText("ACCESS DENIED: TARGET ABOVE RANGE");

                } else if (guess < randomNumber) {

                    status.setText("ACCESS DENIED: TARGET BELOW RANGE");

                } else {

                    status.setText("ACCESS GRANTED ✔ SYSTEM BREACHED");

                }

            } catch (Exception ex) {

                status.setText("INVALID INPUT DETECTED");

            }

        });

        resetButton.setOnAction(e -> {

            randomNumber = random.nextInt(100) + 1;
            attempts = 0;
            attemptLabel.setText("ATTEMPTS: 0");
            status.setText("SYSTEM RESET COMPLETE");
            input.clear();

        });

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);

        root.setStyle("-fx-background-color:black;");

        root.getChildren().addAll(
                title,
                instruction,
                input,
                guessButton,
                resetButton,
                attemptLabel,
                status
        );

        Scene scene = new Scene(root, 450, 320);

        stage.setTitle("Hacker Guess System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}