package kata.poker

import kata.poker.ranking.CardRanking

class Referee(private val cardRankings: List<CardRanking>) {
    fun chosenWinner(hand: Hand, otherHand: Hand): Hand {
        val ranking = orderedRanking(hand)
        val otherRanking = orderedRanking(otherHand)

        if (ranking.first < otherRanking.first) {
            return hand
        }

        if (ranking.first > otherRanking.first) {
            return otherHand
        }

        return if (ranking.second > otherRanking.second) hand else otherHand
    }

    private fun orderedRanking(hand: Hand) = cardRankings.mapIndexedNotNull { index, cardRanking ->
        val ranking = cardRanking.find(hand)
        if (ranking == null) {
            null
        } else {
            Pair(index, ranking.highestCardValue)
        }
    }.first()
}
