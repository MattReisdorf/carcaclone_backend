package com.mattreisdorf.carcaclone_backend.model;

public class TilePlacement {
  private final int x;
  private final int y;
  private final int orientation;
  private final Tile tile;

  
  public TilePlacement(int x, int y, int orientation, Tile tile) {
    this.x = x;
    this.y = y;
    this.orientation = orientation;
    this.tile = tile;
  }

  public int getX()     { return x; }
  public int getY()     { return y; }
  public int getOrientation() {return orientation;}
  public Tile getTile() { return tile; }
}
