package com.tictactoe

object Main {
  def main(args: Array[String]): Unit = {
    val game = new Game();

    while(game.hasNotEnded) {
      println(s"Player ${game.getCurrentPlayer.symbol}, make a move (e. g.: 0 - enter - 0 - enter)");

      val row = scala.io.StdIn.readInt();
      val column = scala.io.StdIn.readInt();

      game.registerMove(row, column);

      printGrid(game.getGrid);
    }

    game.getResult match {
      case Winner(player) => println(s"Winner is ${player.symbol}");
      case Draw => println("Draw");
    }

    println();
    println("Play again? (y/n)");

    val question = scala.io.StdIn.readLine();

    if("y".equals(question)) {
      main(args);
    }
  }

  def printGrid(grid: Array[Array[Position]]): Unit = {
    println();
    grid.map(row => row.map { case Player(symbol) => symbol case Empty => "-" }.mkString(" "))
      .foreach(row => println(row));
    println();
  }
}
