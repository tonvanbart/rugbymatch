package com.paulienvanalst.rugbymatch.game

import com.paulienvanalst.rugbymatch.events.ScoringEvent
import com.paulienvanalst.rugbymatch.events.StartGame
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
open class ScoringBoard {

    private var scoringHistory : List<Score> = emptyList()

    private lateinit var hostingTeam : TeamName

    private lateinit var visitingTeam : TeamName

    fun currentScore(): GameScore {
        val hostScore = scoringHistory.filter { it.forTeam == hostingTeam }.map { it.type.points }.sum()
        val visitScore = scoringHistory.filter { it.forTeam == visitingTeam }.map { it.type.points }.sum()
        return GameScore(Pair(hostingTeam, hostScore), Pair(visitingTeam, visitScore))
    }

    fun clear () {
        scoringHistory = emptyList()
    }

    @EventListener
    fun handleStartGame(startGame: StartGame) {
        this.hostingTeam = startGame.hostingTeam
        this.visitingTeam = startGame.visitingTeam
    }

    @EventListener
    fun handleScore(scoringEvent: ScoringEvent) {
        val score = Score(scoringEvent.team, scoringEvent.type)
        scoringHistory += score
    }
}


