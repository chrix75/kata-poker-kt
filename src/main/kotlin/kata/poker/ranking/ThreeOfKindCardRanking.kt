package kata.poker.ranking

import kata.poker.Hand
import kata.poker.ranking.randkedhand.RankedHand
import kata.poker.ranking.randkedhand.RankedThreeOfKind

class ThreeOfKindCardRanking : CardRanking(30) {
    override fun rankedHand(hand: Hand): RankedHand? {
        val group = cardValueOccurrences(hand).filter { entry -> entry.value == 3 }
            .map { entry -> entry.key }

        if (group.size != 1) {
            return null
        }

        val others = hand.cards.filter { it.value != group[0] }.map { it.value }
        return RankedThreeOfKind(level, group[0], others)
    }

    private fun cardValueOccurrences(hand: Hand): Map<Int, Int> {
        val counters = mutableMapOf<Int, Int>()
        hand.cards.forEach { card ->
            val current = counters.getOrDefault(card.value, 0)
            counters[card.value] = current + 1
        }

        return counters
    }
}
