package kata.poker.ranking.randkedhand

class RankedStraight(level: Int, private val value: Int) : RankedHand(level, emptyList()) {
    override fun or(other: RankedHand): RankedHand? {
        return selectedByLevel(other)
            ?: selectedByBestGroup(other as RankedStraight)
    }

    private fun selectedByBestGroup(other: RankedStraight): RankedHand? {
        if (value > other.value) {
            return this
        } else if (value < other.value) {
            return other
        }
        return null
    }
}
