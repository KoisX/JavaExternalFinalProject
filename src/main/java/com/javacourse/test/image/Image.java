package com.javacourse.test.image;


import com.javacourse.shared.dataAccess.Entity;

import java.util.Arrays;
import java.util.Objects;

/**
 * A model class for image database table
 */
public class Image implements Entity {

  private long id;
  private byte[] data;

  public Image(long id, byte[] data) {
    this.id = id;
    this.data = data;
  }

  public Image() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Image{");
    sb.append("id=").append(id);
    sb.append(", data=");
    if (data == null) sb.append("null");
    else {
      sb.append('[');
      for (int i = 0; i < data.length; ++i)
        sb.append(i == 0 ? "" : ", ").append(data[i]);
      sb.append(']');
    }
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Image)) return false;
    Image image = (Image) o;
    return id == image.id &&
            Arrays.equals(data, image.data);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id);
    result = 31 * result + Arrays.hashCode(data);
    return result;
  }
}
