package io.igu.img

import io.igu.img.utils.MatchEventFixtures._
import org.scalatest.{Matchers, WordSpec}

class MatchStateSpec extends WordSpec with Matchers {

  private val unorderState = new MatchState(Seq(midGame, lateGame, earlyGame))
  private val orderedState = new MatchState(Seq(earlyGame, midGame, lateGame))

  "MatchState" can {
    "latest" should {
      "from an ordered sequence return the latest match event which happened by time" in {
        orderedState.latest should be(lateGame)
      }

      "from an un-ordered sequence return the latest match event which happened by time" in {
        unorderState.latest should be(lateGame)
      }
    }

    "last" should {
      "from an ordered sequence return the last element to be appended into the sequence" in {
        orderedState.last should be(lateGame)
      }

      "from an un-ordered sequence return the last element to be appended into the sequence" in {
        unorderState.last should be(earlyGame)
      }
    }

    "slice" should {
      "from an ordered sequence return an ordered slice of the match events" in {
        orderedState.slice(0, 1) should be(MatchState(Seq(earlyGame)))
      }

      "from an un-ordered sequence return an ordered slice of the match events" in {
        unorderState.slice(0, 1) should be(MatchState(Seq(earlyGame)))
      }
    }
  }

}
