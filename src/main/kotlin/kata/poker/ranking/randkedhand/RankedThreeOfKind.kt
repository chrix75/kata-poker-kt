package kata.poker.ranking.randkedhand

class RankedThreeOfKind(level: Int, private val value: Int, rest: List<Int>) : RankedHand(level, rest) {
    override fun or(other: RankedHand): RankedHand? {
        return selectedByLevel(other)
            ?: selectedByBestGroup(other as RankedThreeOfKind)
            ?: selectedByStrongestCard(other)
    }

    private fun selectedByBestGroup(other: RankedThreeOfKind): RankedHand? {
        if (value > other.value) {
            return this
        } else if (value < other.value) {
            return other
        }
        return null
    }
}
