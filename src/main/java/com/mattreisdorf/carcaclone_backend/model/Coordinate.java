package com.mattreisdorf.carcaclone_backend.model;

import java.util.Objects;

public class Coordinate {
  private final int x;
  private final int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Coordinate)) {
      return false;
    }
    Coordinate coordinate = (Coordinate) object;
    return (
      x == coordinate.x && y == coordinate.y
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
