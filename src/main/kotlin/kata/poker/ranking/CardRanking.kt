package kata.poker.ranking

import kata.poker.Hand

abstract class CardRanking(val level: Int) {
    abstract fun rankedHand(hand: Hand): RankedHand?
}