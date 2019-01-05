package com.javacourse.test.answer;

import com.javacourse.shared.dataAccess.Entity;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * A model class for answer database table
 */
public class Answer implements Entity {

  @PositiveOrZero
  private long id;

  @Size(min = 3, max = 250, message = "{msg.emptyFields}")
  private String value;

  private boolean isCaseSensitive;

  public Answer(long id, String value, boolean isCaseSensitive) {
    this.id = id;
    this.value = value;
    this.isCaseSensitive = isCaseSensitive;
  }

  public Answer() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  public boolean getIsCaseSensitive() {
    return isCaseSensitive;
  }

  public void setIsCaseSensitive(boolean isCaseSensitive) {
    this.isCaseSensitive = isCaseSensitive;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Answer{");
    sb.append("id=").append(id);
    sb.append(", value='").append(value).append('\'');
    sb.append(", isCaseSensitive=").append(isCaseSensitive);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Answer)) return false;
    Answer answer = (Answer) o;
    return id == answer.id &&
            isCaseSensitive == answer.isCaseSensitive &&
            value.equals(answer.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value, isCaseSensitive);
  }
}
