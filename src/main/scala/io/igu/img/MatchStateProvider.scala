package io.igu.img

import io.igu.img.model.MatchEvent

import scala.io.Source

trait MatchStateProvider {

  def fromResource[U](file: String): ((MatchState) => U) => Unit = fromSource(Source.fromResource(file))

  def fromSource[U](source: Source)(func: MatchState => U): Unit = {
    try {
      source
        .getLines()
        .filter(_.isNotBlank)
        .map(MatchEvent.fromHex)
        .foldLeft(MatchState.empty) { (state, event) =>
          val newState = state :+ event
          func(newState)
          newState
        }
    } finally {
      source.close()
    }
  }

  private implicit class StringPimps(str: String) {
    def isBlank: Boolean = str.trim.length == 0

    def isNotBlank: Boolean = !isBlank
  }

}