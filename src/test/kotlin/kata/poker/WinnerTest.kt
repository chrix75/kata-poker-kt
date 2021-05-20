package kata.poker

import kata.poker.Suit.*
import kata.poker.ranking.CardRanking
import kata.poker.ranking.HighCardRanking
import kata.poker.ranking.PairCardRanking
import kata.poker.ranking.TwoPairsCardRanking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WinnerTest {
    private lateinit var cardRankings : List<CardRanking>


    @BeforeEach
    internal fun setUp() {
        cardRankings = listOf(TwoPairsCardRanking(), PairCardRanking(), HighCardRanking())
    }

    @Test
    internal fun `pair beats higCard`() {
        val highCardHand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(2, DIAMOND))
            .with(KingCard(CLUB))
            .with(Card(7, HEART))

        val pairCardHand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(10, DIAMOND))
            .with(KingCard(CLUB))
            .with(Card(7, HEART))

        val referee = Referee(cardRankings)
        assertEquals(pairCardHand, referee.chosenWinner(highCardHand, pairCardHand))
    }

    @Test
    internal fun `everyone has a pair, the strongest card wins`() {
        val acePairCardHand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(AceCard(DIAMOND))
            .with(KingCard(CLUB))
            .with(AceCard(HEART))

        val jackPairCardHand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(10, DIAMOND))
            .with(KingCard(CLUB))
            .with(Card(7, HEART))

        val referee = Referee(cardRankings)
        assertEquals(acePairCardHand, referee.chosenWinner(acePairCardHand, jackPairCardHand))
    }

    @Test
    internal fun `two pairs beat one pair`() {
        val twoPairsCardHand = Hand().with(Card(10, SPADE))
            .with(Card(10, CLUB))
            .with(Card(2, CLUB))
            .with(Card(2, DIAMOND))
            .with(Card(7, CLUB))

        val onePairCardHand = Hand().with(Card(10, SPADE))
            .with(AceCard(CLUB))
            .with(AceCard(DIAMOND))
            .with(KingCard(CLUB))
            .with(Card(7, HEART))

        val referee = Referee(cardRankings)
        assertEquals(twoPairsCardHand, referee.chosenWinner(twoPairsCardHand, onePairCardHand))
    }
}