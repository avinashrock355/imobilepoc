class WordFind {
  List<List<String>> puzzle;

  WordFind({
    required this.puzzle,
  });

  @override
  String toString() {
    String puzzleString = '';
    for (var i = 0, height = puzzle.length; i < height; i++) {
      final List<String> row = puzzle[i];
      for (var j = 0, width = row.length; j < width; j++) {
        puzzleString += (row[j] == '' ? ' ' : row[j]) + ' ';
      }
      puzzleString += '\n';
    }
    return puzzleString;
  }

  WSolved solvePuzzle(
    // The current state of the puzzle
    List<List<String>> puzzle,

    /// The words to be searched in the puzzle
    List<String> words,
  ) {
    // Create new instance of the solved interface
    WSolved output = WSolved(found: [], notFound: []);

    for (var i = 0, len = words.length; i < len; i++) {
      final String word = words[i];
      List<WLocation> locations =
          _findBestLocations(puzzle, puzzle.length, puzzle[0].length, word);
      if (locations.isNotEmpty && locations[0].overlap == word.length) {
        locations[0].word = word;
        output.found.add(locations[0]);
      } else {
        output.notFound.add(word);
      }
    }
    // Return the output
    return output;
  }

  List<WLocation> _findBestLocations(
    /// The current state of the puzzle
    List<List<String>> puzzle,

    /// The options to use when filling the puzzle
    int height,
    int width,

    /// The word to fit into the puzzle
    String word,
  ) {
    List<WLocation> locations = [];

    int wordLength = word.length;
    // we'll start looking at overlap = 0
    int maxOverlap = 0;

    // loop through all of the possible orientations at this position
    for (var i = 0; i < 3; i++) {
      //// final WSOrientation orientation = options.orientations[i];
      final WSOrientationFn next = wsOrientations[i]!;
      final WSCheckOrientationFn check = wsCheckOrientations[i]!;
      final WSOrientationFn skip = wsSkipOrientations[i]!;
      int x = 0;
      int y = 0;
      // loop through every position on the board
      while (y < height) {
        // see if this orientation is even possible at this location
        if (check(x, y, height, width, wordLength)) {
          // determine if the word fits at the current position
          final int overlap = _calcOverlap(word, puzzle, x, y, next);
          // if the overlap was bigger than previous overlaps that we've seen
          if (overlap >= maxOverlap || (false && overlap > -1)) {
            maxOverlap = overlap;
            locations.add(
              WLocation(
                column: x,
                row: y,
                orientation: i,
                overlap: overlap,
                word: word,
              ),
            );
          }
          // increment x position
          x += 1;
          if (x >= width) {
            x = 0;
            // increment y position
            y += 1;
          }
        } else {
          // if current cell is invalid, then skip to the next cell where
          // this orientation is possible. this greatly reduces the number
          // of checks that we have to do overall
          WPosition nextPossible = skip(x, y, wordLength);
          x = nextPossible.column;
          y = nextPossible.row;
        }
      }
    }

    // finally prune down all of the possible locations we found by
    // only using the ones with the maximum overlap that we calculated
    return _pruneLocations(locations, maxOverlap);
  }

  List<WLocation> _pruneLocations(
    /// The set of locations to prune
    List<WLocation> locations,

    /// The required level of overlap
    int overlap,
  ) {
    List<WLocation> pruned = [];
    for (var i = 0; i < locations.length; i++) {
      if (locations[i].overlap >= overlap) {
        pruned.add(locations[i]);
      }
    }
    return pruned;
  }

  int _calcOverlap(
    /// The word to fit into the puzzle
    String word,

    /// The current state of the puzzle
    List<List<String>> puzzle,

    /// The x position to check
    int x,

    /// The y position to check
    int y,

    /// Function that returns the next square
    WSOrientationFn fnGetSquare,
  ) {
    int overlap = 0;

    // traverse the squares to determine if the word fits
    for (var i = 0; i < word.length; i++) {
      final WPosition next = fnGetSquare(x, y, i);
      String? square;
      try {
        square = puzzle[next.row][next.column];
      } catch (_e) {
        square = null;
      }
      // if the puzzle square already contains the letter we
      // are looking for, then count it as an overlap square
      if (square == word[i]) {
        overlap++;
      }
      // if it contains a different letter, than our word doesn't fit
      // here, return -1
      else if (square != '') {
        return -1;
      }
    }

    // if the entire word is overlapping, skip it to ensure words aren't
    // hidden in other words
    return overlap;
  }
}

class WSolved {
  /// List of words found by solving the puzzle
  List<WLocation> found;

  /// List of words that were not found while solving the puzzle
  List<String> notFound;

  WSolved({
    required List<WLocation> found,
    required List<String> notFound,
  })  : found = found,
        notFound = notFound;
}

class WGrid {
  int row;
  int column;

  WGrid({
    required this.column,
    required this.row,
  });
}

/// Word location interface
class WLocation implements WPosition {
  /// The column position where the word starts
  final int column;

  /// The row position where the word starts
  final int row;

  /// The orientation the word placed in the puzzle
  final int orientation; //0 horizontal 1 vertical 2 diagonal

  /// The numbed of overlaps the word has
  final int overlap;

  /// The word used
  String word;

  WLocation({
    required this.column,
    required this.row,
    required this.orientation,
    required this.overlap,
    required this.word,
  });
}

// Word position interface
class WPosition {
  final int column;

  final int row;

  WPosition({
    this.column = 0,
    this.row = 0,
  });
}

final Map<int, WSCheckOrientationFn> wsCheckOrientations = {
  0: (int x, int y, int h, int w, int l) {
    // horizontal
    return w >= x + l;
  },
  1: (int x, int y, int h, int w, int l) {
    //vertical
    return h >= y + l;
  },
  2: (int x, int y, int h, int w, int l) {
    //diagonal
    return w >= x + l && h >= y + l;
  }
};

final Map<int, WSOrientationFn> wsOrientations = {
  0: (int x, int y, int i) {
    return WPosition(column: x + i, row: y);
  },
  1: (int x, int y, int i) {
    return WPosition(column: x, row: y + i);
  },
  2: (int x, int y, int i) {
    return WPosition(column: x + i, row: y + i);
  },
};

final Map<int, WSOrientationFn> wsSkipOrientations = {
  0: (int x, int y, int l) {
    return WPosition(column: 0, row: y + 1);
  },
  1: (int x, int y, int l) {
    return WPosition(column: 0, row: y + 100);
  },
  2: (int x, int y, int l) {
    return WPosition(column: 0, row: y + 1);
  },
};

/// Check orientation function interface
typedef WSCheckOrientationFn = bool Function(int x, int y, int h, int w, int l);
typedef WSOrientationFn = WPosition Function(int x, int y, int i);
