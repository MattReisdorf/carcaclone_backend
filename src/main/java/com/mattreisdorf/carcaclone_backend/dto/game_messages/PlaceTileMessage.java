package com.mattreisdorf.carcaclone_backend.dto.game_messages;

import com.mattreisdorf.carcaclone_backend.model.Tile;

public class PlaceTileMessage {
  private int orientation;
  private int x;
  private int y;
  private Tile drawnTile;

  public int getOrientation() {
    return orientation;
  }
  public void setOrientation(int orientation) {
    this.orientation = orientation;
  }

  public int getX() {
    return x;
  }
  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }
  public void setY(int y) {
    this.y = y;
  }

  public Tile getDrawnTile() {
    return drawnTile;
  }
  public void setDrawnTile(Tile drawnTile) {
    this.drawnTile = drawnTile;
  }


  @Override
  public String toString() {
    return(
      "PlaceTileMessage {" +
      " orientation: " + orientation +
      ", x: " + x + 
      ", y: " + y + 
      ", drawnTile" + drawnTile + " }"
    );
  }
}
