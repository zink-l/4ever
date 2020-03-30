package org.enzenberger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.enzenberger.control.SelectionController;
import org.enzenberger.model.Game;
import org.enzenberger.view.BoardView;

import java.io.IOException;

public class MainApp extends Application {


    private Stage primaryStage;
    private AnchorPane rootLayout;

    private final Game game;

    public static void main(String[] args) {
        launch(args);
    }

    public MainApp() {
        this.game = new Game();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("4ever");

        initRootLayout();
        showBoard();
        showSetup();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showBoard() {
        BoardView boardView = BoardView.getInstance();
        boardView.setScene(this.primaryStage.getScene());
        boardView.setGame(this.game);
        this.rootLayout.getChildren().add(boardView.getBoardGroup());
    }

    private void showSetup() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("SelectionWindow.fxml"));
            StackPane selectionWindow = loader.load();
            SelectionController selectionController = loader.getController();

            selectionWindow.setAlignment(Pos.CENTER);
            selectionWindow.setPrefHeight(rootLayout.getHeight());
            selectionWindow.setPrefWidth(rootLayout.getWidth());
            rootLayout.widthProperty().addListener(observable -> selectionWindow.setPrefWidth(rootLayout.getWidth()));
            rootLayout.heightProperty().addListener(observable -> selectionWindow.setPrefHeight(rootLayout.getHeight()));


            this.rootLayout.getChildren().add(selectionWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}