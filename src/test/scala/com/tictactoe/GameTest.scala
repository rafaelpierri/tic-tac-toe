package com.tictactoe

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class GameTest extends AnyFlatSpec with should.Matchers {
  "hasNotEnded" should "be true when match starts" in {
    val game = new Game();
    game.hasNotEnded should be (true);
  }

  it should "be false when all moves are taken" in {
    val game = new Game();
    makeDraw(game);

    game.hasNotEnded should be (false);
  }

  it should "be true when player X wins" in {
    val game = new Game();
    makeXWin(game);

    game.hasNotEnded should be (false);
  }

  it should "be true when player O wins" in {
    val game = new Game();
    makeOWin(game);

    game.hasNotEnded should be (false);
  }

  "getCurrentPlayer" should "be X when game starts" in {
    val game = new Game();

    game.getCurrentPlayer should be (Game.X)
  }

  it should "be O after first move" in {
    val game = new Game();
    game.registerMove(0, 0);

    game.getCurrentPlayer should be (Game.O)
  }

  it should "keep current player if move is already taken" in {
    val game = new Game();
    game.registerMove(0, 0);
    game.registerMove(0, 0);

    game.getCurrentPlayer should be (Game.O)
  }

  it should "keep current player if row or column is greater or equal to grid length" in {
    val game = new Game();
    game.registerMove(0, 3);
    game.registerMove(4, 0);

    game.getCurrentPlayer should be (Game.X)
  }

  "getResult" should "display draw when match has not ended" in {
    val game = new Game();

    game.getResult should be (Draw);
  }

  it should "present X as winner when X wins" in {
    val game = new Game();
    makeXWin(game);

    game.getResult should be (Winner(Game.X));
  }

  it should "present O as winner when X wins" in {
    val game = new Game();
    makeOWin(game);

    game.getResult should be (Winner(Game.O));
  }

  it should "display draw when it happens" in {
    val game = new Game();
    makeDraw(game);

    game.getResult should be (Draw);
  }

  "getGrid" should "present a matrix with all positions" in {
    val game = new Game();
    game.registerMove(0, 0);

    game.getGrid should be (Array(Array(Game.X, Empty, Empty), Array(Empty, Empty, Empty), Array(Empty, Empty, Empty)));
  }

  def makeXWin(game: Game) = {
    game.registerMove(0, 0);
    game.registerMove(1, 0);
    game.registerMove(0, 1);
    game.registerMove(1, 1);
    game.registerMove(0, 2);
  }

  def makeOWin(game: Game) = {
    game.registerMove(0, 0);
    game.registerMove(1, 0);
    game.registerMove(2, 0);
    game.registerMove(1, 1);
    game.registerMove(0, 1);
    game.registerMove(1, 2);
  }

  def makeDraw(game: Game) = {
    game.registerMove(0, 1);
    game.registerMove(0, 0);
    game.registerMove(1, 1);
    game.registerMove(2, 1);
    game.registerMove(1, 2);
    game.registerMove(1, 0);
    game.registerMove(2, 0);
    game.registerMove(0, 2);
    game.registerMove(2, 2);
  }
}
