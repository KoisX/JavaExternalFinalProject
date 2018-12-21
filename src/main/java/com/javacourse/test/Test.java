package com.javacourse.test;

import com.javacourse.shared.dataAccess.Entity;
import com.javacourse.test.topic.Topic;

import java.util.Objects;

/**
 * A model class for test database table
 */
public class Test implements Entity {

  private long id;
  private Topic topic;
  private String description;
  private String header;

  public Test(long id, Topic topic, String description, String header) {
    this.id = id;
    this.topic = topic;
    this.description = description;
    this.header = header;
  }

  public Test() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Test{");
    sb.append("id=").append(id);
    sb.append(", topic=").append(topic);
    sb.append(", description='").append(description).append('\'');
    sb.append(", header=").append(header);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Test)) return false;
    Test test = (Test) o;
    return id == test.id &&
            topic.equals(test.topic) &&
            description.equals(test.description) &&
            header.equals(test.header);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, topic, description, header);
  }
}
