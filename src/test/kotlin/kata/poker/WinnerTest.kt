package kata.poker

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
    internal fun pairBeatsHigCard() {
        val highCardHand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(2, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(Card(7, Suit.HEART))

        val pairCardHand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(10, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(Card(7, Suit.HEART))

        val referee = Referee(cardRankings)
        assertEquals(pairCardHand, referee.chosenWinner(highCardHand, pairCardHand))
    }

    @Test
    internal fun theBestPairWins() {
        val acePairCardHand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(AceCard(Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(AceCard(Suit.HEART))

        val jackPairCardHand = Hand().with(Card(10, Suit.SPADE))
            .with(JackCard(Suit.CLUB))
            .with(Card(10, Suit.DIAMOND))
            .with(KingCard(Suit.CLUB))
            .with(Card(7, Suit.HEART))

        val referee = Referee(cardRankings)
        assertEquals(acePairCardHand, referee.chosenWinner(acePairCardHand, jackPairCardHand))
    }
}