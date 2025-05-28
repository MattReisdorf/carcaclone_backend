package com.mattreisdorf.carcaclone_backend.model;

import java.util.List;

import com.mattreisdorf.carcaclone_backend.enums.TileCode;

public class Tile {
  private final TileCode tileCode;
  private final List<TileSegment> segments;

  public Tile(TileCode tileCode, List<TileSegment> segments) {
    this.tileCode = tileCode;
    this.segments = segments;
  }

  public TileCode getTileCode() {
    return tileCode;
  }

  public List<TileSegment> getSegments() {
    return segments;
  }







  // private final List<TileEdge> edges;
  // private final TileCenter center;
  // private final boolean hasShield;
  
  // public Tile(List<TileEdge> edges, TileCenter center, boolean hasShield) {
  //   this.edges = edges;
  //   this.center = center;
  //   this.hasShield = hasShield;
  // }

  // public Tile copy() {
  //   return new Tile(new ArrayList<>(edges), center, hasShield);
  // }

  // public List<TileEdge> getTileEdges() {
  //   return edges;
  // }
  // public TileCenter getTileCenter() {
  //   return center;
  // }
  // public boolean isHasShield() {
  //   return hasShield;
  // }


}
