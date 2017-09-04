package io.igu.img.model

case class MatchEvent(pointsScored: Int,
                      team: Int,
                      team1Score: Int,
                      team2Score: Int,
                      matchTime: Int) {

  override def toString: String = {
    s"DataEvent(pointsScored: $pointsScored, team: $team, team1Score: $team1Score, team2score:$team2Score, matchTime: $matchTime)"
  }

}

object MatchEvent {

  def fromHex(hex: String): MatchEvent = {
    val x = toBinary(Integer.parseInt(hex.replaceFirst("0x", ""), 16), 32)

    val arr = x.toCharArray

    val pointsScored = arr.slice(30, 32).mkString
    val team = arr(29)
    val team2Score = arr.slice(21, 29).mkString
    val team1Score = arr.slice(13, 21).mkString
    val matchTime = arr.slice(1, 13).mkString

    MatchEvent(
      pointsScored = Integer.parseInt(pointsScored, 2),
      team = Integer.parseInt(team.toString, 2) + 1,
      team1Score = Integer.parseInt(team1Score, 2),
      team2Score = Integer.parseInt(team2Score, 2),
      matchTime = Integer.parseInt(matchTime, 2)
    )
  }

  private def toBinary(i: Int, digits: Int) = {
    val binaryString = i.toBinaryString
    String.format(s"%${digits}s", binaryString).replace(' ', '0')
  }

}