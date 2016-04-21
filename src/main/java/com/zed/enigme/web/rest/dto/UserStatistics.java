package com.zed.enigme.web.rest.dto;

import com.zed.enigme.domain.User;

/**
 * Created by Arnaud on 21/04/2016.
 */
public class UserStatistics {

    User user;

    long totalNumberOfTry;

    long totalDuration;

    long currentEnigma;

    public UserStatistics(User user, long totalNumberOfTry, long totalDuration, long currentEnigma) {
        this.user = user;
        this.totalNumberOfTry = totalNumberOfTry;
        this.totalDuration = totalDuration;
        this.currentEnigma = currentEnigma;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTotalNumberOfTry() {
        return totalNumberOfTry;
    }

    public void setTotalNumberOfTry(long totalNumberOfTry) {
        this.totalNumberOfTry = totalNumberOfTry;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public long getCurrentEnigma() {
        return currentEnigma;
    }

    public void setCurrentEnigma(long currentEnigma) {
        this.currentEnigma = currentEnigma;
    }
}
