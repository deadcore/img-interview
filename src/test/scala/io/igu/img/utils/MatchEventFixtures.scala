package io.igu.img.utils

import io.igu.img.model.MatchEvent

object MatchEventFixtures {

  val earlyGame = MatchEvent(pointsScored = 2, team = 1, team1Score = 2, team2Score = 0, matchTime = 10)
  val midGame = MatchEvent(pointsScored = 3, team = 0, team1Score = 10, team2Score = 15, matchTime = 600)
  val lateGame = MatchEvent(pointsScored = 2, team = 1, team1Score = 20, team2Score = 30, matchTime = 1400)

}
