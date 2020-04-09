package org.enzenberger.model.mode;

import org.enzenberger.exceptions.ColumnOverflowException;

public class TimeGameMode extends GameMode implements TimeListener {
    private StopWatch stopWatch;

    public TimeGameMode() {
        this.stopWatch = new StopWatch();
        this.stopWatch.setTimeListener(this);
    }

    public void setTimeLimit(int time) {
        this.stopWatch.setTime(time);
    }

    @Override
    protected void onPlayerChangeHook() {
        this.stopWatch.resetTimer();
    }

    @Override
    protected void resetPlayerMove() {
        this.stopWatch.pauseTimer();
        this.stopWatch.resetTimer();
    }

    @Override
    protected void pausePlayerMove() {
        this.stopWatch.pauseTimer();
    }

    @Override
    public void schedulePlayerMove() {
        this.stopWatch.resumeTimer();
    }

    @Override
    public void notifyTimeOver() {
        boolean turnDone= false;
        while (!turnDone) {
            try {
                this.boardController.dropStone(this.game.getCurrentPlayer(), (int) (Math.random() * 7));
                turnDone = true;
            } catch (ColumnOverflowException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notifyTime(double time) {
        //todo
    }
}
