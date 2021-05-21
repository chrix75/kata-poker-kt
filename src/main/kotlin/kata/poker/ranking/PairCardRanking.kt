package kata.poker.ranking

import kata.poker.Hand

class PairCardRanking : CardRanking(10) {
    override fun rankedHand(hand: Hand): RankedHand? {
        val pairs = cardValueOccurrences(hand).filter { entry -> entry.value == 2 }
            .map { entry -> entry.key }

        if (pairs.size != 1) {
            return null
        }

        val others = hand.cards.filter { it.value != pairs[0] }.map { it.value }
        return RankedPair(level, pairs[0], others)
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
