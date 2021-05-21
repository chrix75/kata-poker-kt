package kata.poker.ranking

abstract class RankedHand(private val level: Int, val rest: List<Int>) {
    abstract fun or(other: RankedHand): RankedHand?

    fun selectedByLevel(other: RankedHand): RankedHand? {
        return when {
            level > other.level -> this
            level < other.level -> other
            else -> null
        }
    }

    fun selectedByStrongestCard(other: RankedHand): RankedHand? {
        val orderedValues = rest.sorted().reversed()
        val otherOrderedValues = other.rest.sorted().reversed()
        for (i in 0..(rest.size)) {
            if (orderedValues[i] > otherOrderedValues[i]) {
                return this
            } else if (orderedValues[i] < otherOrderedValues[i]) {
                return other
            }
        }

        return null
    }
}
