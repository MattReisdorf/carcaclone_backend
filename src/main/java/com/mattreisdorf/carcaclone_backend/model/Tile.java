package com.mattreisdorf.carcaclone_backend.model;

import java.util.List;

import com.mattreisdorf.carcaclone_backend.enums.EdgeDirection;
import com.mattreisdorf.carcaclone_backend.enums.TileCode;
import com.mattreisdorf.carcaclone_backend.enums.TileFeature;

public class Tile {
  private TileCode tileCode;
  private List<TileSegment> segments;

  public Tile() {};

  public Tile(TileCode tileCode, List<TileSegment> segments) {
    this.tileCode = tileCode;
    this.segments = segments;
  }

  public TileCode getTileCode() {
    return tileCode;
  }
  public void setTileCode(TileCode tileCode) {
    this.tileCode = tileCode;
  }

  public List<TileSegment> getSegments() {
    return segments;
  }
  public void setSegments(List<TileSegment> segments) {
    this.segments = segments;
  }

  public TileFeature getFeatureAt(EdgeDirection edgeDirection) {
    return segments.stream()
      .filter(segment -> segment.getConnects().contains(edgeDirection))
      .findFirst()
      .map(TileSegment::getTileFeature)
      .orElseThrow(() -> 
        new IllegalStateException("No Segment for " + edgeDirection + " on tile " + tileCode)
      );
  }

  @Override
  public String toString() {
    return (
      "Tile: { " +
      "tileCode: " + tileCode +
      ", segments: " + segments + "}"
    );
  }

}
