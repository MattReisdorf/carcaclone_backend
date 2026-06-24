package com.mattreisdorf.carcaclone_backend.enums;

public enum EdgeDirection {
  NORTH,
  EAST,
  SOUTH,
  WEST;

  public EdgeDirection opposite() {
    switch (this) {
      case NORTH: return SOUTH;
      case SOUTH: return NORTH;
      case EAST:  return WEST;
      case WEST:  return EAST;
      default:    throw new IllegalStateException("Unknown EdgeDirection: " + this);
    }
  }
}
