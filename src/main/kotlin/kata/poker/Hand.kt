package kata.poker

import org.valiktor.functions.hasSize
import org.valiktor.validate

class Hand {
    private val _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() = ArrayList(_cards)

    fun with(card: Card) = apply {
        validate(this) {
            validate(Hand::_cards).hasSize(0, 4)
        }
        this._cards.add(card) }
}
