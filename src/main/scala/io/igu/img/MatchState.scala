package io.igu.img

import io.igu.img.model.MatchEvent

import scala.math.Ordering

case class MatchState(events: Seq[MatchEvent]) {

  private val matchtTimeOrdering: Ordering[MatchEvent] = Ordering.by(_.matchTime)
  private val latestOrdering = matchtTimeOrdering.lt _

  def latest: MatchEvent = events.sortWith(latestOrdering).last

  def last: MatchEvent = events.last

  def slice(from: Int, to: Int): MatchState = MatchState(events.sortWith(latestOrdering).slice(from, to))

  def foreach[U](fun: MatchEvent => U): Unit = events.sortWith(latestOrdering).foreach(fun)

  def :+(event: MatchEvent): MatchState = if (events.contains(event)) this else MatchState(events :+ event)

}

object MatchState extends MatchStateProvider {
  val empty: MatchState = MatchState(Seq.empty)
}