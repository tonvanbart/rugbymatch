package com.paulienvanalst.rugbymatch.events

import org.springframework.context.ApplicationEvent
import com.paulienvanalst.rugbymatch.team.TeamName
import com.paulienvanalst.rugbymatch.game.*

sealed class SetPieceEvent(source: Any?, val setPiece: SetPiece, val winningTeam : TeamName) : ApplicationEvent(source)

class ScrumWasPlayed(source: Any?, scrum: Scrum, winningTeam: TeamName) : SetPieceEvent(source, scrum, winningTeam)

class LineOutWasPlayed(source: Any?, lineOut: LineOut, winningTeam: TeamName) : SetPieceEvent(source, lineOut, winningTeam)

fun List<SetPieceEvent>.scrumEvents() = this.filter { it is ScrumWasPlayed }

fun List<SetPieceEvent>.lineOutEvents() = this.filter { it is LineOutWasPlayed }

fun List<SetPieceEvent>.wonBy(name : TeamName) = this.filter { it.winningTeam == name }

fun List<SetPieceEvent>.lostBy(name : TeamName) = this.filter { it.winningTeam != name }





