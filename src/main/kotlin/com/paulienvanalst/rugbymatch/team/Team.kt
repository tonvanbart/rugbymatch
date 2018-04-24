package com.paulienvanalst.rugbymatch.team

import org.apache.commons.lang.NotImplementedException


data class Team (val players: List<Player>, val name: TeamName) {
    /**
     * A team has enough players when it has at least 15 players
     */
    val hasEnoughPlayers : Boolean
        get() = players.count() > 14

    /**
     * A team has substitutes when it has more than 15 players
     */
    val hasAnySubstitutes : Boolean
        get() = players.any { it.backNumber > 15 }

    /**
     * A team has enough starting players when there are at least 15 players
     * wearing back numbers 1 until 15
     */
    val hasEnoughStartingPlayers : Boolean
        get() = players.filter { it.isStarting }.count() > 14

    /**
     * The captain, when present, should always where back number 9
     */
    fun captainBackNumber(): Int {
        return scrumhalf()!!.backNumber
    }

    /**
     * When no scrumhalf present (player with back number 9)
     * the captain is wearing the first back number we can find among the starting players.
     */
    fun replacingCaptainBackNumber(): Int? {
        return scrumhalf()?.backNumber ?: players.first { it.isStarting }.backNumber
    }

    fun scrumhalf() : Player? {
        return players.find { it.position == Position.SCRUM_HALF }
    }


}

