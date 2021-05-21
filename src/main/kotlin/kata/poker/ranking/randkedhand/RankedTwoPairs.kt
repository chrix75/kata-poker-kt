package kata.poker.ranking.randkedhand

import org.valiktor.functions.hasSize
import org.valiktor.validate

class RankedTwoPairs(level: Int, private val pairs: List<Int>, rest: List<Int>) : RankedHand(level, rest) {

    init {
        validate(this) {
            validate(RankedTwoPairs::pairs).hasSize(2, 2)
            validate(RankedTwoPairs::rest).hasSize(1, 1)
        }
    }

    override fun or(other: RankedHand): RankedHand? {
        return selectedByLevel(other)
            ?: selectedByBestPair(other as RankedTwoPairs)
            ?: selectedByStrongestCard(other)
    }

    private fun selectedByBestPair(other: RankedTwoPairs): RankedHand? {
        val max = pairs.maxOrNull()!!
        val maxOther = other.pairs.maxOrNull()!!
        if (max > maxOther) {
            return this
        } else if (max < maxOther) {
            return other
        }

        val min = pairs.minOrNull()!!
        val minOther = other.pairs.minOrNull()!!
        if (min > minOther) {
            return this
        } else if (min < minOther) {
            return other
        }

        return null
    }
}
