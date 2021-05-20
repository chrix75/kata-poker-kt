package kata.poker

class Hand {
    private val _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() = ArrayList(_cards)

    fun with(card: Card) = apply { this._cards.add(card) }
}
