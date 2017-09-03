package io.igu.img

import io.igu.img.model.MatchEvent

import scala.io.Source

object MatchStateProvider {
  def fromResource[U](file: String): ((MatchState) => U) => Unit = fromSource(Source.fromResource(file))


  def fromSource[U](source: Source)(func: MatchState => U): Unit = {
    try {
      source
        .getLines()
        .filter(!_.isEmpty)
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
}