package kata.poker.ranking

import kata.poker.Hand

interface CardRanking {
    fun find(hand: Hand) : Ranking?
}