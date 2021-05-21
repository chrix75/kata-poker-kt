package kata.poker

import kata.poker.Suit.*
import kata.poker.ranking.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WinnerTest {
    private lateinit var cardRankings : List<CardRanking>


    @BeforeEach
    internal fun setUp() {
        cardRankings = listOf(
            StraightCardRanking(),
            ThreeOfKindCardRanking(),
            TwoPairsCardRanking(),
            PairCardRanking(),
            HighCardRanking()
        )
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

    @Test
    internal fun `three of kind beat two pairs`() {
        val twoPairsCardHand = Hand().with(Card(10, SPADE))
            .with(Card(10, CLUB))
            .with(Card(2, CLUB))
            .with(Card(2, DIAMOND))
            .with(Card(7, CLUB))

        val threeOfKindCardHand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(10, DIAMOND))
            .with(Card(10, HEART))
            .with(JackCard(HEART))

        val referee = Referee(cardRankings)
        assertEquals(threeOfKindCardHand, referee.chosenWinner(twoPairsCardHand, threeOfKindCardHand))
    }

    @Test
    internal fun `the best three of kind wins`() {
        val threeJackCardHand = Hand().with(JackCard(SPADE))
            .with(JackCard(CLUB))
            .with(Card(2, CLUB))
            .with(JackCard(HEART))
            .with(Card(7, CLUB))

        val threeTenCardHand = Hand().with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(Card(10, DIAMOND))
            .with(Card(10, HEART))
            .with(JackCard(HEART))

        val referee = Referee(cardRankings)
        assertEquals(threeJackCardHand, referee.chosenWinner(threeJackCardHand, threeTenCardHand))
    }

    @Test
    internal fun `the straight beats three of kind`() {
        val threeJackCardHand = Hand().with(JackCard(SPADE))
            .with(JackCard(CLUB))
            .with(Card(2, CLUB))
            .with(JackCard(HEART))
            .with(Card(7, CLUB))

        val straightHand = Hand().with(Card(7, CLUB))
            .with(Card(8, HEART))
            .with(Card(9, SPADE))
            .with(Card(10, DIAMOND))
            .with(JackCard(CLUB))

        val referee = Referee(cardRankings)
        assertEquals(straightHand, referee.chosenWinner(threeJackCardHand, straightHand))
    }

    @Test
    internal fun `the straight with the strongest card wins`() {
        val otherStraightHand = Hand().with(Card(8, CLUB))
            .with(Card(9, HEART))
            .with(Card(10, SPADE))
            .with(JackCard(CLUB))
            .with(QueenCard(CLUB))

        val straightHand = Hand().with(Card(7, CLUB))
            .with(Card(8, HEART))
            .with(Card(9, SPADE))
            .with(Card(10, DIAMOND))
            .with(JackCard(CLUB))

        val referee = Referee(cardRankings)
        assertEquals(otherStraightHand, referee.chosenWinner(otherStraightHand, straightHand))
    }

    @Test
    internal fun `a strongest card wins`() {
        val myHand = Hand().with(Card(3, SPADE))
            .with(Card(5, CLUB))
            .with(Card(6, HEART))
            .with(JackCard(DIAMOND))
            .with(AceCard(DIAMOND))

        val adverse = Hand().with(Card(2, SPADE))
            .with(Card(5, DIAMOND))
            .with(Card(6, CLUB))
            .with(JackCard(HEART))
            .with(AceCard(CLUB))

        val referee = Referee(cardRankings)
        assertEquals(myHand, referee.chosenWinner(myHand, adverse))
    }
}