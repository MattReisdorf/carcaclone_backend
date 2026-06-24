package com.mattreisdorf.carcaclone_backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mattreisdorf.carcaclone_backend.enums.EdgeDirection;
import com.mattreisdorf.carcaclone_backend.enums.TileFeature;
import com.mattreisdorf.carcaclone_backend.factory.TileFactory;

public class Board {
  private final Map<Coordinate, Tile> tiles = new HashMap<>();
  private final List<TilePlacement> placements = new ArrayList<>();

  // @JsonProperty("placements")
  // public List<TilePlacement> getPlacements() {
  //   return tiles.entrySet().stream()
  //     .map(e -> new TilePlacement(
  //         e.getKey().getX(),
  //         e.getKey().getY(),
  //         e.getKey().getOrientation(),
  //         e.getValue()
  //     ))
  //     .collect(Collectors.toList());
  // }

  @JsonProperty("placements")
  public List<TilePlacement> getPlacements() {
    return Collections.unmodifiableList(placements);
  }

  public boolean canPlaceTile(Coordinate coordinate, Tile tile) {
    if (tiles.containsKey(coordinate)) {
      return false;
    }

    boolean touches = false;
    for (EdgeDirection edgeDirection : EdgeDirection.values()) {
      Coordinate neighbor = move(coordinate, edgeDirection);
      Tile neighborTile = tiles.get(neighbor);
      if (neighborTile != null) {
        touches = true;
        TileFeature here = tile.getFeatureAt(edgeDirection);
        TileFeature there = neighborTile.getFeatureAt(edgeDirection.opposite());
        if (here != there) {
          return false;
        }
      }
    }

    return touches;
  }

  public void placeStartingTile() {
    tiles.put(new Coordinate(0, 0), TileFactory.createFirstTile());
    placements.add(new TilePlacement(0, 0, 0, TileFactory.createFirstTile()));
  }

  public void placeTile(Coordinate coordinate, Tile tile, int orientation) {
    if (!canPlaceTile(coordinate, tile)) {
      throw new IllegalArgumentException(
        "Illegal placement at " + coordinate + " for tile " + tile.getTileCode()
      );
    }
    tiles.put(coordinate, tile);
    placements.add(new TilePlacement(coordinate.getX(), coordinate.getY(), orientation, tile));
  }

  private Coordinate move(Coordinate coordinate, EdgeDirection edgeDirection) {
    switch(edgeDirection) {
      case NORTH: return new Coordinate(coordinate.getX(),     coordinate.getY() + 1);
      case EAST:  return new Coordinate(coordinate.getX() + 1, coordinate.getY());
      case SOUTH: return new Coordinate(coordinate.getX(),     coordinate.getY() - 1);
      case WEST:  return new Coordinate(coordinate.getX() - 1, coordinate.getY());
      default:    throw new IllegalStateException("Unknown dir " + edgeDirection);
    }
  }
}
