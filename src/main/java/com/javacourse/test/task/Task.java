package com.javacourse.test.task;

import com.javacourse.shared.Entity;
import com.javacourse.test.image.Image;
import com.javacourse.test.answer.Answer;

import java.util.List;
import java.util.Objects;

/**
 * A model class for task database table
 */
public class Task implements Entity {

  private long id;
  private long testId;
  private String question;
  private Image image;

  public Task(long id, long testId, String question, Image image) {
    this.id = id;
    this.testId = testId;
    this.question = question;
    this.image = image;
  }

  public Task() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getTestId() {
    return testId;
  }

  public void setTestId(long testId) {
    this.testId = testId;
  }


  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }


  public Image getImageId() {
    return image;
  }

  public void setImageId(Image imageId) {
    this.image = imageId;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Task{");
    sb.append("id=").append(id);
    sb.append(", testId=").append(testId);
    sb.append(", question='").append(question).append('\'');
    sb.append(", image=").append(image);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Task)) return false;
    Task task = (Task) o;
    return id == task.id &&
            testId == task.testId &&
            question.equals(task.question) &&
            image.equals(task.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, testId, question, image);
  }
}
