package kata.poker

import kata.poker.ranking.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class HandTest {
    @Test
    internal fun `find high card`() {
        val hand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(2, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(Card(7, Suit.HEART))

        val rankedHand = HighCardRanking().rankedHand(hand)
        assertNotNull(rankedHand)
        assertIs<RankedHighCard>(rankedHand)
    }

    @Test
    internal fun `find one pair`() {
        val hand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(10, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(Card(7, Suit.HEART))

        val rankedHand = PairCardRanking().rankedHand(hand)
        assertNotNull(rankedHand)
        assertIs<RankedPair>(rankedHand)
    }

    @Test
    internal fun `find two pairs`() {
        val hand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(10, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(JackCard(Suit.HEART))

        val rankedHand = TwoPairsCardRanking().rankedHand(hand)
        assertNotNull(rankedHand)
        assertIs<RankedTwoPairs>(rankedHand)
    }

    @Test
    internal fun `too many cards`() {
        assertThrows<Exception> {
            Hand().with(Card(10, Suit.SPADE))
                .with(JackCard(Suit.CLUB))
                .with(Card(10, Suit.DIAMOND))
                .with(KingCard(Suit.CLUB))
                .with(JackCard(Suit.HEART))
                .with(QueenCard(Suit.HEART))
        }
    }
}