package com.javacourse.test;

import java.util.Objects;

/**
 * A model class for topic database table
 */
public class Topic {

  private long id;
  private String name;

  public Topic(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Topic() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Topic{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Topic)) return false;
    Topic topic = (Topic) o;
    return id == topic.id &&
            name.equals(topic.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
