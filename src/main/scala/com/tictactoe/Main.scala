package com.tictactoe

object Main {
  def main(args: Array[String]): Unit = {
    val game = new Game();

    while(game.hasNotEnded) {
      println(s"Player ${game.getCurrentPlayer.symbol}, make a move (e. g.: 0 - enter - 0 - enter)");

      val row = readInt;
      val column = readInt;

      game.registerMove(row, column);

      printGrid(game.getGrid);
    }

    game.getResult match {
      case Winner(player) => println(s"Winner is ${player.symbol}");
      case Draw => println("Draw");
    }

    println();
    println("Play again? (y/n)");

    val answer = scala.io.StdIn.readLine();

    if("y".equals(answer)) {
      main(args);
    }
  }

  def readInt: Int = {
    var option: Option[Int] = Option.empty[Int];

    try {
      option = Option(scala.io.StdIn.readInt());
    } catch {
      case _: NumberFormatException => println("Please, provide an integer");
      case e: Exception => println(e);
    }

    option match {
      case Some(result) => result;
      case None => readInt;
    }
  }

  def printGrid(grid: Array[Array[Position]]): Unit = {
    println();
    grid.map(row => row.map { case Player(symbol) => symbol case Empty => "-" }.mkString(" "))
      .foreach(row => println(row));
    println();
  }
}
