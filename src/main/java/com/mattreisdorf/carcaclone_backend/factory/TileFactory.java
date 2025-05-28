package com.mattreisdorf.carcaclone_backend.factory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Set;

import com.mattreisdorf.carcaclone_backend.enums.EdgeDirection;
import com.mattreisdorf.carcaclone_backend.enums.TileCode;
import com.mattreisdorf.carcaclone_backend.enums.TileFeature;
import com.mattreisdorf.carcaclone_backend.model.Tile;
import com.mattreisdorf.carcaclone_backend.model.TileSegment;

public class TileFactory {
  private static void addTiles(List<Tile> tileBag, TileCode tileCode, List<TileSegment> prototype, int count) {
    for (int i = 0; i < count; i++) {
      tileBag.add(new Tile(tileCode, prototype));
    }
  }

  public static Deque<Tile> createTileBag() {
    List<Tile> tileBag = new ArrayList<>();

    // CCCC
    addTiles(
      tileBag,
      TileCode.CCCC,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST,
            EdgeDirection.SOUTH,
            EdgeDirection.WEST
          ),
          true
        )
      ),
      1
    );

    // CCCF
    // With Shield
    addTiles(
      tileBag,
      TileCode.CCCF_SHIELD,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST,
            EdgeDirection.WEST
          ),
          true
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.SOUTH
          )
        )
      ),
      1
    );
    // Without Shield
    addTiles(
      tileBag,
      TileCode.CCCF,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST,
            EdgeDirection.WEST
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.SOUTH
          )
        )
      ),
      3
    );

    // CCCR
    // With Shield
    addTiles(
      tileBag,
      TileCode.CCCR_SHIELD,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST,
            EdgeDirection.WEST
          ),
          true
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH
          )
        )
      ),
      2
    );
    // Without Shield
    addTiles(
      tileBag,
      TileCode.CCCR,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST,
            EdgeDirection.WEST
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH
          )
        )
      ),
      1
    );

    // CCFF
    // Adjacent Corners
    addTiles(
      tileBag,
      TileCode.CCFF_DISCONNECTED,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH
          )
        ),
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.EAST
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.SOUTH,
            EdgeDirection.WEST
          )
        )
      ),
      2
    );
    // Diagonal With Shield
    addTiles(
      tileBag,
      TileCode.CCFF_SHIELD,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST
          ),
          true
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.SOUTH,
            EdgeDirection.WEST
          )
        )
      ),
      2
    );
    // Diagonal Without Shield
    addTiles(
      tileBag,
      TileCode.CCFF,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.SOUTH,
            EdgeDirection.WEST
          )
        )
      ),
      3
    );

    // CCRR
    // With Shield
    addTiles(
      tileBag,
      TileCode.CCRR_SHIELD,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.WEST
          ),
          true
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH,
            EdgeDirection.EAST
          )
        )
      ),
      2
    );
    // Without Shield
    addTiles(
      tileBag,
      TileCode.CCRR,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.WEST
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH,
            EdgeDirection.EAST
          )
        )
      ),
      3
    );

    // CFCF
    // Connected With Shield
    addTiles(
      tileBag,
      TileCode.CFCF_SHIELD,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.SOUTH
          ),
          true
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.EAST
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.WEST
          )
        )
      ),
      2
    );
    // Without Shield
    addTiles(
      tileBag,
      TileCode.CFCF,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.SOUTH
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.EAST
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.WEST
          )
        )
      ),
      1
    );
    // Separated
    addTiles(
      tileBag,
      TileCode.CFCF_DISCONNECTED,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH
          )
        ),
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.SOUTH
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.EAST,
            EdgeDirection.WEST
          )
        )
      ),
      3
    );

    // CFFF
    addTiles(
      tileBag,
      TileCode.CFFF,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.EAST,
            EdgeDirection.SOUTH,
            EdgeDirection.WEST
          )
        )
      ),
      5
    );

    // CFRR
    addTiles(
      tileBag,
      TileCode.CFRR,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.EAST
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH,
            EdgeDirection.WEST
          )
        )
      ),
      3
    );

    // CRFR
    addTiles(
      tileBag,
      TileCode.CRFR,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.SOUTH
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.EAST,
            EdgeDirection.WEST
          )
        )
      ),
      3
    );

    // CRRF
    addTiles(
      tileBag,
      TileCode.CRRF,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH
          )
        ),
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.WEST
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH,
            EdgeDirection.EAST
          )
        )
      ),
      3
    );

    // CRRR
    addTiles(
      tileBag,
      TileCode.CRRR,
      List.of(
        new TileSegment(
          TileFeature.CITY,
          Set.of(
            EdgeDirection.NORTH
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.EAST
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.WEST
          )
        )
      ),
      3
    );

    // FFFF
    addTiles(
      tileBag,
      TileCode.FFFF,
      List.of(
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST,
            EdgeDirection.SOUTH,
            EdgeDirection.WEST
          )
        )
      ),
      4
    );

    // FFFR
    addTiles(
      tileBag,
      TileCode.FFFR,
      List.of(
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST,
            EdgeDirection.SOUTH
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.WEST
          )
        )
      ),
      2
    );

    // FFRR
    addTiles(
      tileBag,
      TileCode.FFRR,
      List.of(
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.EAST          
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH,
            EdgeDirection.WEST
          )
        )
      ),
      9
    );

    // FRFR
    addTiles(
      tileBag,
      TileCode.FRFR,
      List.of(
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.NORTH,
            EdgeDirection.SOUTH          
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.EAST,
            EdgeDirection.WEST
          )
        )
      ),
      8
    );

    // FRRR
    addTiles(
      tileBag,
      TileCode.FRRR,
      List.of(
        new TileSegment(
          TileFeature.FIELD,
          Set.of(
            EdgeDirection.NORTH         
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.EAST
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.WEST
          )
        )
      ),
      4
    );

    // RRRR
    addTiles(
      tileBag,
      TileCode.RRRR,
      List.of(
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.NORTH         
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.EAST
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.SOUTH
          )
        ),
        new TileSegment(
          TileFeature.ROAD,
          Set.of(
            EdgeDirection.WEST
          )
        )
      ),
      1
    );

    Collections.shuffle(tileBag);
    return new ArrayDeque<>(tileBag);
  }
}
