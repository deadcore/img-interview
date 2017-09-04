package io.igu.img

import java.util.IllegalFormatPrecisionException

import io.igu.img.model.MatchEvent
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

import scala.io.Source

class MatchStateProviderSpec extends WordSpec with Matchers with MockitoSugar with BeforeAndAfter {

  private val failingSource = mock[Source]

  private val matchEvents =
    """
      |
      |0x1310c8a1
      |
      |""".stripMargin

  before {
    reset(failingSource)
    when(failingSource.getLines()).thenThrow(new IllegalFormatPrecisionException(5))
  }

  "MatchStateProvider" can {
    "fromSource" should {
      "filter any blank lines out" in {
        val expected = Seq(
          MatchEvent.fromHex("0x1310c8a1")
        )

        MatchState.fromSource(Source.fromString(matchEvents)) { matchState =>
          matchState should be(MatchState(expected))
        }

      }

      "should re-throw any exceptions" in {
        an[IllegalFormatPrecisionException] should be thrownBy MatchState.fromSource(failingSource) { _ => }
      }

      "should close the source on an exception" in {
        the[IllegalFormatPrecisionException] thrownBy MatchState.fromSource(failingSource) { _ => }
        verify(failingSource, times(1)).close()
      }
    }
  }

}
