package kata.poker

import kata.poker.ranking.CardRanking

class Referee(cardRankings: List<CardRanking>) {
    private val orderedCardRankings = cardRankings.sortedByDescending { it.level }

    fun chosenWinner(hand: Hand, otherHand: Hand): Hand? {
        val rankedHand = bestRanking(hand)
        val otherRankedHand = bestRanking(otherHand)

        return when (rankedHand.or(otherRankedHand)) {
            rankedHand -> hand
            otherRankedHand -> otherHand
            else -> null
        }
    }

    private fun bestRanking(hand: Hand) = orderedCardRankings.firstNotNullOf { it.rankedHand(hand) }
}
