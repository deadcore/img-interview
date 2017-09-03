package io.igu.img

object Example extends App {

  //  Can parse incoming data items into a data structure suitable for capturing the state of the match

  //  Maintains a match state as additional data items are received. It should be possible to query the match state (via a method) for the following:
  //  ◦ the last event (i.e. which team last scored, how many points, at what point through the match and what the resulting match score was)
  //  ◦ the last n events (where 0 <= n <= Total Items)
  //  ◦ all events in the match so far

  //  Handles cases where an invalid data item is received or where data is inconsistent with previous data received.

  MatchStateProvider.fromResource("sample2.txt") { matchState =>
    println("Received a whole new match state")

    matchState.foreach(println)
  }
}
