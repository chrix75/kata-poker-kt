package kata.poker.ranking

import kata.poker.Card
import kata.poker.Hand

class HighCardRanking : CardRanking {
    override fun find(hand: Hand): Ranking? {
        val sorted = hand.cards.sortedWith(Card::compare)
        return Ranking(sorted[0].value)
    }
}
