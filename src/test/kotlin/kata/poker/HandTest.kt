package kata.poker

import kata.poker.Suit.*
import kata.poker.ranking.*
import kata.poker.ranking.randkedhand.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class HandTest {
    @Test
    internal fun `find high card`() {
        val hand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(2, DIAMOND))
            .with(KingCard(CLUB))
            .with(Card(7, HEART))

        val rankedHand = HighCardRanking().rankedHand(hand)
        assertNotNull(rankedHand)
        assertIs<RankedHighCard>(rankedHand)
    }

    @Test
    internal fun `find one pair`() {
        val hand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(10, DIAMOND))
            .with(KingCard(CLUB))
            .with(Card(7, HEART))

        val rankedHand = PairCardRanking().rankedHand(hand)
        assertNotNull(rankedHand)
        assertIs<RankedPair>(rankedHand)
    }

    @Test
    internal fun `find two pairs`() {
        val hand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(10, DIAMOND))
            .with(KingCard(CLUB))
            .with(JackCard(HEART))

        val rankedHand = TwoPairsCardRanking().rankedHand(hand)
        assertNotNull(rankedHand)
        assertIs<RankedTwoPairs>(rankedHand)
    }

    @Test
    internal fun `find three of kind`() {
        val hand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(10, DIAMOND))
            .with(Card(10, HEART))
            .with(JackCard(HEART))

        val rankedHand = ThreeOfKindCardRanking().rankedHand(hand)
        assertNotNull(rankedHand)
        assertIs<RankedThreeOfKind>(rankedHand)
    }

    @Test
    internal fun `find a straight`() {
        val hand = Hand().with(Card(7, CLUB))
            .with(Card(8, HEART))
            .with(Card(9, SPADE))
            .with(Card(10, DIAMOND))
            .with(JackCard(CLUB))

        val rankedHand = StraightCardRanking().rankedHand(hand)
        assertNotNull(rankedHand)
        assertIs<RankedStraight>(rankedHand)
    }

    @Test
    internal fun `too many cards`() {
        assertThrows<Exception> {
            Hand().with(Card(10, SPADE))
                .with(JackCard(CLUB))
                .with(Card(10, DIAMOND))
                .with(KingCard(CLUB))
                .with(JackCard(HEART))
                .with(QueenCard(HEART))
        }
    }
}