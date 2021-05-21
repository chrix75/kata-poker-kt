package kata.poker.ranking

import kata.poker.Hand

class HighCardRanking : CardRanking(1) {
    override fun rankedHand(hand: Hand): RankedHand = RankedHighCard(level, hand.cards.map { it.value })
}
