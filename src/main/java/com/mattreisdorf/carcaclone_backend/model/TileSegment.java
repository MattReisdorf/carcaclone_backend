package com.mattreisdorf.carcaclone_backend.model;

import java.util.Set;

import com.mattreisdorf.carcaclone_backend.enums.EdgeDirection;
import com.mattreisdorf.carcaclone_backend.enums.TileFeature;

public class TileSegment {
  private TileFeature tileFeature;
  private Set<EdgeDirection> connects;
  private boolean hasShield;

  public TileSegment() {};

  public TileSegment(TileFeature tileFeature, Set<EdgeDirection> connects) {
    this.tileFeature = tileFeature;
    this.connects = connects;
    this.hasShield = false;
  }

  public TileSegment(TileFeature tileFeature, Set<EdgeDirection> connects, boolean hasShield) {
    this.tileFeature = tileFeature;
    this.connects = connects;
    this.hasShield = hasShield;
  }

  public TileFeature getTileFeature() {
    return tileFeature;
  }
  public void setTileFeature(TileFeature tileFeature) {
    this.tileFeature = tileFeature;
  }

  public Set<EdgeDirection> getConnects() {
    return connects;
  }
  public void setConnects(Set<EdgeDirection> connects) {
    this.connects = connects;
  }

  public boolean hasShield() {
    return hasShield;
  }
  public void setHasShield(boolean hasShield) {
    this.hasShield = hasShield;
  }

  @Override
  public String toString() {
    return (
      "TileSegment: { " +
      "tileFeature: " + tileFeature +
      ", connects: " + connects +
      ", hasShield: " + hasShield + " }"
    );
  }
}
