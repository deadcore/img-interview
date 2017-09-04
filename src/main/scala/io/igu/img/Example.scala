package io.igu.img

object Example extends App {

  MatchState.fromResource("sample2.txt") { matchState =>
    println("********************************************")
    println("***** Received a whole new match state *****")
    println("********************************************")

    // The last event (i.e. which team last scored, how many points, at what point through the match and what the resulting match score was)
    println(s"The last event was ${matchState.last}")
    println(s"The latest event was ${matchState.latest}")

    // The last n events (where 0 <= n <= Total Items)
    println(s"The last n number of events were ${matchState.slice(0, 3)}")

    // All events in the match so far
    matchState.foreach(println)

    println("********************************************")
  }
}
