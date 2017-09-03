package io.igu.img

import io.igu.img.model.MatchEvent

import scala.math.Ordering

case class MatchState(events: Seq[MatchEvent]) {
  private val matchtTimeOrdering: Ordering[MatchEvent] = Ordering.by(_.matchTime)
  private val latestOrdering = matchtTimeOrdering.lt _

  //  Maintains a match state as additional data items are received. It should be possible to query the match state (via a method) for the following:
  //  ◦ the last event (i.e. which team last scored, how many points, at what point through the match and what the resulting match score was)
  //  ◦ the last n events (where 0 <= n <= Total Items)
  //  ◦ all events in the match so far

  def latest: MatchEvent = events.sortWith(latestOrdering).last

  def last: MatchEvent = events.last

  def slice(from: Int, to: Int): MatchState = new MatchState(events.sortWith(latestOrdering).slice(from, to))

  def foreach[U](fun: MatchEvent => U): Unit = events.sortWith(latestOrdering).foreach(fun)

  def :+(event: MatchEvent) = new MatchState(events :+ event)

}

object MatchState {
  val empty: MatchState = MatchState(Seq.empty)
}