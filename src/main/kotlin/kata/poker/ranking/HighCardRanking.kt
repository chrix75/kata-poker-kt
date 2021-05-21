package kata.poker.ranking

import kata.poker.Hand
import kata.poker.ranking.randkedhand.RankedHand
import kata.poker.ranking.randkedhand.RankedHighCard

class HighCardRanking : CardRanking(1) {
    override fun rankedHand(hand: Hand): RankedHand = RankedHighCard(level, hand.cards.map { it.value })
}
