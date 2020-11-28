package com.tictactoe;

import org.scalatest.flatspec.AnyFlatSpec;
import org.scalatest.matchers._;

class MoveAnalyserTest extends AnyFlatSpec with should.Matchers {
  "lineHasBeenFilled" should "inform if a line has been filled" in {
    var score = MoveAnalyser(3);
    score = score.register(Move(0, 0));
    score = score.register(Move(0, 1));
    score = score.register(Move(0, 2));
    score.lineHasBeenFilled should be (true);
  }

  it should "inform if any other line has been filled" in {
    var score = MoveAnalyser(3);
    score = score.register(Move(2, 0));
    score = score.register(Move(2, 1));
    score = score.register(Move(2, 2));
    score.lineHasBeenFilled should be (true);
  }

  it should "inform if a column has been filled" in {
    var score = MoveAnalyser(3);
    score = score.register(Move(0, 0));
    score = score.register(Move(1, 0));
    score = score.register(Move(2, 0));
    score.lineHasBeenFilled should be (true);
  }

  it should "inform if the diagonal has been filled" in {
    var score = MoveAnalyser(3);
    score = score.register(Move(0, 0));
    score = score.register(Move(1, 1));
    score = score.register(Move(2, 2));
    score.lineHasBeenFilled should be (true);
  }

  it should "inform if the inverse diagonal has been filled" in {
    var score = MoveAnalyser(3);
    score = score.register(Move(2, 0));
    score = score.register(Move(1, 1));
    score = score.register(Move(0, 2));
    score.lineHasBeenFilled should be (true);
  }

  it should "return false if no line has been filled" in {
    var score = MoveAnalyser(3);
    score = score.register(Move(0, 0));
    score = score.register(Move(1, 1));
    score = score.register(Move(2, 0));
    score = score.register(Move(1, 2));
    score.lineHasBeenFilled should be (false);
  }
}
