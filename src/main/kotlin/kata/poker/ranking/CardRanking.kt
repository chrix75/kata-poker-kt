package kata.poker.ranking

import kata.poker.Hand
import kata.poker.ranking.randkedhand.RankedHand

abstract class CardRanking(val level: Int) {
    abstract fun rankedHand(hand: Hand): RankedHand?
}