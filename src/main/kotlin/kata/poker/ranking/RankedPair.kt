package kata.poker.ranking

class RankedPair(level: Int, private val pair: Int, rest: List<Int>) : RankedHand(level, rest) {
    override fun or(other: RankedHand): RankedHand? {
        return selectedByLevel(other)
            ?: selectedByBestPair(other as RankedPair)
            ?: selectedByStrongestCard(other)
    }

    private fun selectedByBestPair(other: RankedPair): RankedHand? {
        if (pair > other.pair) {
            return this
        } else if (pair < other.pair) {
            return other
        }
        return null
    }
}
