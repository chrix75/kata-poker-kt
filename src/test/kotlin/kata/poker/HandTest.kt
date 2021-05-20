package kata.poker

import kata.poker.ranking.HighCardRanking
import kata.poker.ranking.PairCardRanking
import kata.poker.ranking.TwoPairsCardRanking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class HandTest {
    @Test
    internal fun `find high card`() {
        val hand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(2, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(Card(7, Suit.HEART))

        val ranking = HighCardRanking().find(hand)
        assertNotNull(ranking)
        assertEquals(KING_VALUE, ranking.highestCardValue)
    }

    @Test
    internal fun `find one pair`() {
        val hand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(10, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(Card(7, Suit.HEART))

        val ranking = PairCardRanking().find(hand)
        assertNotNull(ranking)
        assertEquals(10, ranking.highestCardValue)
    }

    @Test
    internal fun `find two pairs`() {
        val hand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(10, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(JackCard(Suit.HEART))

        val ranking = TwoPairsCardRanking().find(hand)
        assertNotNull(ranking)
        assertEquals(JACK_VALUE, ranking.highestCardValue)
    }
}