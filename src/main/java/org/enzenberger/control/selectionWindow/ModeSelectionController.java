package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;
import org.enzenberger.model.mode.ClassicGameMode;
import org.enzenberger.model.mode.ForeverGameMode;
import org.enzenberger.model.mode.GameMode;
import org.enzenberger.model.mode.TimeGameMode;

public class ModeSelectionController extends SelectionWindowController {
    @FXML
    private void setGameModeClassic(){
        setGameMode(new ClassicGameMode());
    }

    @FXML
    private void setGameModeTime(){
        setGameMode(new TimeGameMode());
    }

    @FXML
    private void setGameModeForever(){
        setGameMode(new ForeverGameMode());
    }

    @FXML
    private void goBack(){
        this.mainApp.showPlayerSelection();
    }

    private void setGameMode(GameMode gameMode){
        this.game.setGameMode(new ForeverGameMode());
        this.mainApp.startGame();
    }
}
