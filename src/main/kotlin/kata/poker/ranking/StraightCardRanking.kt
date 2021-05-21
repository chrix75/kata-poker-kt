package kata.poker.ranking

import kata.poker.Hand
import kata.poker.ranking.randkedhand.RankedHand
import kata.poker.ranking.randkedhand.RankedStraight

class StraightCardRanking : CardRanking(40) {
    override fun rankedHand(hand: Hand): RankedHand? {
        val sorted = hand.cards.sortedBy { it.value }.map { it.value }
        for (i in 1..4) {
            if (sorted[i - 1] != sorted[i] - 1) {
                return null
            }
        }

        return RankedStraight(level, sorted[4])
    }
}
