package kata.poker

import org.valiktor.functions.isBetween
import org.valiktor.validate

const val JACK_VALUE = 11
const val QUEEN_VALUE = 12
const val KING_VALUE = 13
const val ACE_VALUE = 14

open class Card(val value: Int, private val suit: Suit) {

    init {
        validate(this) {
            validate(Card::value).isBetween(2, 14)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is Card && other.value == value && other.suit == suit
    }
}

class JackCard(suit: Suit) : Card(JACK_VALUE, suit)
class QueenCard(suit: Suit) : Card(QUEEN_VALUE, suit)
class KingCard(suit: Suit) : Card(KING_VALUE, suit)
class AceCard(suit: Suit) : Card(ACE_VALUE, suit)
