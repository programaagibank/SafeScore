package com.safescore.model;

import java.util.List;

public class ResultadoScore {
  public int score;
  public List<FatorScore> fatores;

  public ResultadoScore(int score, List<FatorScore> fatores) {
    this.score = score;
    this.fatores = fatores;
  }
}