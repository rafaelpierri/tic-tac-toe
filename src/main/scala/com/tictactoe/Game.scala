package com.tictactoe

trait Position;
case class Player(symbol: String) extends Position;
object Empty extends Position;

trait Result;
case class Winner(player: Player) extends Result;
object Draw extends Result;

class Game {
  val gridLength = 3;
  private var analysers: Map[Player, MoveAnalyser] = _;
  private var currentPlayer: Player = _;
  private var moveCount: Int = _;
  private var grid: Map[Move, Player] = _;

  startGame;

  def registerMove(row: Int, column: Int): Unit = {
    val move = Move(row, column);
    if (isMoveInvalid(move)) return;
    if (isMoveTaken(move)) return;
    grid = grid + (move -> currentPlayer);
    analysers = analysers + (currentPlayer -> analysers(currentPlayer).register(move));
    toggleCurrentPlayer();
    moveCount += 1;
  };

  def hasNotEnded: Boolean = moveCount < (gridLength * gridLength) &&
    !analysers.values.exists(analyser => analyser.lineHasBeenFilled);

  def getCurrentPlayer: Player = currentPlayer;

  def getResult: Result = analysers.find(tuple => tuple._2.lineHasBeenFilled).map(tuple => Winner(tuple._1)).getOrElse(Draw);

  def restart: Unit = { startGame };

  def getGrid: Array[Array[Position]] = {
    val grid: Array[Array[Position]] = Array.ofDim[Position](gridLength, gridLength);
    for {
      row <- 0 until gridLength
      column <- 0 until gridLength
    } if (this.grid.contains(Move(row, column))) grid(row)(column) = this.grid(Move(row, column)) else grid(row)(column) = Empty;
    grid;
  }

  private def startGame: Unit = {
    analysers = Map[Player, MoveAnalyser](Game.X -> MoveAnalyser(gridLength), Game.O -> MoveAnalyser(gridLength));
    currentPlayer = Game.X;
    moveCount = 0;
    grid = Map[Move, Player]();
  }

  private def isMoveTaken(move: Move): Boolean = grid.contains(move);

  private def isMoveInvalid(move: Move): Boolean = move.row >= gridLength || move.column >= gridLength;

  private def toggleCurrentPlayer(): Unit = if (currentPlayer == Game.X) currentPlayer = Game.O else currentPlayer = Game.X;

}

object Game {
  val X = Player("X");
  val O = Player("O");
}
