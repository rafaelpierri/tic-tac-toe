package com.tictactoe

case class Move(row: Int, column: Int);

case class MoveAnalyser(gridLength: Int,
                        columnUsageCount: Map[Int, Int],
                        rowUsageCount: Map[Int, Int],
                        diagonalCount: Int,
                        inverseDiagonalCount: Int) {

  def register(move: Move): MoveAnalyser = {
    val rowUsageCount = this.rowUsageCount + (move.row -> (this.rowUsageCount(move.row) + 1));
    val columnUsageCount = this.columnUsageCount + (move.column -> (this.columnUsageCount(move.column) + 1));
    val diagonalCount = this.diagonalCount + (if(move.row == move.column)  1 else 0);
    val inverseDiagonalCount = this.inverseDiagonalCount + (if(move.row + move.column + 1 == gridLength)  1 else 0);
    MoveAnalyser(3, columnUsageCount, rowUsageCount, diagonalCount, inverseDiagonalCount);
  }

  def lineHasBeenFilled: Boolean = {
    if (rowUsageCount.values.exists(count => count == gridLength)) return true;
    if (columnUsageCount.values.exists(count => count == gridLength)) return true;
    if (diagonalCount == gridLength) return true;
    if (inverseDiagonalCount == gridLength) return true;
    false;
  }
}

object MoveAnalyser {
  def apply(gridLength: Int): MoveAnalyser =
    MoveAnalyser(gridLength, build(gridLength), build(gridLength), 0, 0);

  def build(gridLength: Int): Map[Int, Int] = {
    if (gridLength - 1 == 0) {
      return Map(0 -> 0);
    }

    Map(gridLength - 1 -> 0) ++ build(gridLength - 1);
  }
}
