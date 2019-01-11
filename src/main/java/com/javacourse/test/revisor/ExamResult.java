package com.javacourse.test.revisor;

import java.util.List;
import java.util.Objects;

/**
 * Encapsulates comfortable transport of exam result tuple values
 */
public class ExamResult {
    private int score;
    private double percentageOfSolvedTasks;
    private int maxScore;

    /*Tasks, in which user made mistakes*/
    private List<Long> wrongTasksIndexes;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPercentageOfSolvedTasks() {
        return percentageOfSolvedTasks;
    }

    public void setPercentageOfSolvedTasks(double percentageOfSolvedTasks) {
        this.percentageOfSolvedTasks = percentageOfSolvedTasks;
    }

    public List<Long> getWrongTasksIndexes() {
        return wrongTasksIndexes;
    }

    public void setWrongTasksIndexes(List<Long> wrongTasksIndexes) {
        this.wrongTasksIndexes = wrongTasksIndexes;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
