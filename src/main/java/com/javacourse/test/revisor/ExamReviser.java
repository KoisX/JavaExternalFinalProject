package com.javacourse.test.revisor;

import com.javacourse.test.answer.Answer;
import com.javacourse.test.commands.CheckExamCommand;
import com.javacourse.test.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class which handles revising exams.
 * Encapsulates core part of the business logic of the application
 */
public class ExamReviser {

    /**
     * Revised the test and returns the ExamResult entity,
     * whicch in fact is a value object for transporting result tuple
     * @param paramMap parameter map of the request from the exam form
     * @param tasks tasks of the current exam
     * @param maxScore maximal score , which could be reached in this test
     * @return ExamResult filled with resulting values
     */
    public ExamResult reviseTest(Map<String, String[]> paramMap, List<Task> tasks, int maxScore){
        List<Long> wrongTasks = new ArrayList<>();
        ExamResult result = new ExamResult();
        int score = 0;
        for(Task task : tasks){
            String[] userAnswersFromRequest = paramMap.get(getRequestParamInputValue(task));
            List<String> userAnswers = new ArrayList<>(Arrays.asList(userAnswersFromRequest));
            List<String> correctAnswers = task.getCorrectAnswers()
                    .stream()
                    .map(Answer::getValue)
                    .collect(Collectors.toList());
            if(userAnswers.containsAll(correctAnswers) && userAnswers.size()==correctAnswers.size()){
                score += task.getPrice();
            }else {
                wrongTasks.add(task.getId());
            }
        }
        result.setWrongTasksIndexes(wrongTasks);
        result.setScore(score);
        result.setPercentageOfSolvedTasks(getPercentageOfSolvedTasks(score, maxScore));
        result.setMaxScore(maxScore);
        return result;
    }

    /*Gets param from a request, with the name, which corresponds to
     * test page naming conventions: field_task-id*/
    private String getRequestParamInputValue(Task task){
        return "field_"+String.valueOf(task.getId());
    }

    private double getPercentageOfSolvedTasks(int score, int maxScore){
        return ((double)score)/maxScore*100;
    }

}
