package com.paulienvanalst.rugbymatch.game

import com.paulienvanalst.rugbymatch.team.Team


interface SetPiece {

    val otherTeam : Team

    val teamThrowingIn : Team

    fun isValid() : Boolean
}

data class Scrum(override val otherTeam: Team, override val teamThrowingIn: Team) : SetPiece {

    /**
     * A scrum is valid when both teams participating have a scrum
     */
    override fun isValid(): Boolean {
        return otherTeam.hasScrum() && teamThrowingIn.hasScrum()
    }
}

/**
 * A lineout is valid when both team participating have the same number of players playing the line-out
 */

data class LineOut(override val otherTeam: Team, override val teamThrowingIn: Team, val nr1: Int, val nr2: Int) : SetPiece {

    override fun isValid(): Boolean {
        return nr1 == nr2
    }
}
