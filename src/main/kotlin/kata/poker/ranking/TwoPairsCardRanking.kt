package kata.poker.ranking

import kata.poker.Hand

class TwoPairsCardRanking : CardRanking {
    override fun find(hand: Hand): Ranking? {
        val pairs = cardValueOccurrences(hand).filter { entry -> entry.value == 2 }
            .map { entry -> entry.key }

        if (pairs.size != 2) {
            return null
        }

        return Ranking(pairs.maxOrNull()!!)
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
