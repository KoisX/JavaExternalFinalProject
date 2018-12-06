package com.javacourse.stats;

import com.javacourse.shared.Entity;
import com.javacourse.test.Test;
import com.javacourse.user.User;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * A model class for stats database table
 */
public class Stats implements Entity {

  private long id;
  private User user;
  private Test test;
  private long score;
  private java.sql.Timestamp timePassed;

  public Stats(long id, User user, Test test, long score, Timestamp timePassed) {
    this.id = id;
    this.user = user;
    this.test = test;
    this.score = score;
    this.timePassed = timePassed;
  }

  public Stats() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public User getUserAccountId() {
    return user;
  }

  public void setUserAccountId(User user) {
    this.user = user;
  }


  public Test getTestId() {
    return test;
  }

  public void setTestId(Test test) {
    this.test = test;
  }


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }


  public java.sql.Timestamp getTimePassed() {
    return timePassed;
  }

  public void setTimePassed(java.sql.Timestamp timePassed) {
    this.timePassed = timePassed;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Stats{");
    sb.append("id=").append(id);
    sb.append(", user=").append(user);
    sb.append(", test=").append(test);
    sb.append(", score=").append(score);
    sb.append(", timePassed=").append(timePassed);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Stats)) return false;
    Stats stats = (Stats) o;
    return id == stats.id &&
            score == stats.score &&
            user.equals(stats.user) &&
            test.equals(stats.test) &&
            timePassed.equals(stats.timePassed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, test, score, timePassed);
  }
}
