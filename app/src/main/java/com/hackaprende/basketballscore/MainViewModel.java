package com.hackaprende.basketballscore;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private int localScore = 0;
    private int visitorScore = 0;

    public int getLocalScore() {
        return localScore;
    }

    public int getVisitorScore() {
        return visitorScore;
    }

    void resetScores() {
        localScore = 0;
        visitorScore = 0;
    }

    void addPointsToScore(int points, Boolean isLocal) {
        if (isLocal) {
            localScore += points;
        } else {
            visitorScore += points;
        }
    }

    void decreaseLocal() {
        if (localScore > 0) {
            localScore--;
        }

    }

    void decreaseVisitor() {
        if (visitorScore > 0) {
            visitorScore--;
        }
    }
}
