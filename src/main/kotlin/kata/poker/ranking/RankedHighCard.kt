package kata.poker.ranking

class RankedHighCard(level: Int, rest: List<Int>) : RankedHand(level, rest) {
    override fun or(other: RankedHand) = selectedByLevel(other) ?: selectedByStrongestCard(other)
}
