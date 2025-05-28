package com.mattreisdorf.carcaclone_backend.model;

import java.util.Set;

import com.mattreisdorf.carcaclone_backend.enums.EdgeDirection;
import com.mattreisdorf.carcaclone_backend.enums.TileFeature;

public class TileSegment {
  private final TileFeature tileFeature;
  private final Set<EdgeDirection> connects;
  private final boolean hasShield;

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
  public Set<EdgeDirection> getConnects() {
    return connects;
  }
  public boolean hasShield() {
    return hasShield;
  }
}
