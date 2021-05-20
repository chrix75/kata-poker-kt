package kata.poker

class Hand {
    private val _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() = ArrayList(_cards)

    fun with(card: Card) = apply { this._cards.add(card) }

    fun twoPairs(): List<Card> {
        val pairs = cardValueOccurrences().filter { entry -> entry.value == 2 }
            .map { entry -> entry.key }

        if (pairs.size != 2) {
            return emptyList()
        }

        return cards.filter { card -> pairs.contains(card.value) }
    }


    private fun cardValueOccurrences(): Map<Int, Int> {
        val counters = mutableMapOf<Int, Int>()
        _cards.forEach { card ->
            val current = counters.getOrDefault(card.value, 0)
            counters[card.value] = current + 1
        }

        return counters
    }

}
