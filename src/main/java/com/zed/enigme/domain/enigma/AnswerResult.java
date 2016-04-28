package com.zed.enigme.domain.enigma;

/**
 * Created by Arnaud on 28/04/2016.
 */
public class AnswerResult {

    private boolean hasBeenFound;
    private int nbWordsFound;
    private long distanceInPercentage;

    public AnswerResult(boolean hasBeenFound, int nbWordsFound, int distanceInPercentage) {
        this.hasBeenFound = hasBeenFound;
        this.nbWordsFound = nbWordsFound;
        this.distanceInPercentage = distanceInPercentage;
    }

    public boolean isHasBeenFound() {
        return hasBeenFound;
    }

    public void setHasBeenFound(boolean hasBeenFound) {
        this.hasBeenFound = hasBeenFound;
    }

    public int getNbWordsFound() {
        return nbWordsFound;
    }

    public void setNbWordsFound(int nbWordsFound) {
        this.nbWordsFound = nbWordsFound;
    }

    public long getDistanceInPercentage() {
        return distanceInPercentage;
    }

    public void setDistanceInPercentage(long distanceInPercentage) {
        this.distanceInPercentage = distanceInPercentage;
    }
}
