package io.igu.img.model

import org.scalatest.{Matchers, WordSpec}

class MatchEventSpec extends WordSpec with Matchers {

  "MatchEvent" can {
    "fromHex" should {
      "parse 0x1310c8a1 as 10:10, a single point for Team 1 gives them a 5 point lead – 25-20" in {
        val result = MatchEvent.fromHex("0x1310c8a1")

        result should be(MatchEvent(
          pointsScored = 1,
          team = 1,
          team1Score = 25,
          team2Score = 20,
          matchTime = (10 * 60) + 10,
        ))
      }

      "parse 0x29f981a2 as 22:23, a 2-point shot for Team 1 leaves them 4 points behind at 48-52" in {
        val result = MatchEvent.fromHex("0x29f981a2")

        result should be(MatchEvent(
          pointsScored = 2,
          team = 1,
          team1Score = 48,
          team2Score = 52,
          matchTime = (22 * 60) + 23,
        ))
      }

      "parse 0x48332327 as 38:30, a 3-point shot levels the game for Team 2 at 100 points each" in {
        val result = MatchEvent.fromHex("0x48332327")

        result should be(MatchEvent(
          pointsScored = 3,
          team = 2,
          team1Score = 100,
          team2Score = 100,
          matchTime = (38 * 60) + 30,
        ))
      }

      "handle the largest 32 bit integer" in {
        MatchEvent.fromHex("0x7FFFFFFF")
      }

      "throw a [NumberFormatException] if the hexadecimal exceeds 32 bit integer" in {
        a[NumberFormatException] must be thrownBy MatchEvent.fromHex("0xFFFFFFFF")
      }

    }
  }

}
