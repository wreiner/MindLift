package eu.sumindlift.mindlift.data.entity

import eu.sumindlift.mindlift.R

enum class EnergyLevel {

    LOW {
        override fun getId(): Int = 1
        override fun getTitleResourceId(): Int = R.string.low_energy
        override fun getBatteryLevel(): Int = 0
    },
    HALF {
        override fun getId(): Int = 2
        override fun getTitleResourceId(): Int = R.string.half_energy
        override fun getBatteryLevel(): Int = 50
    },
    HIGH {
        override fun getId(): Int = 3
        override fun getTitleResourceId(): Int = R.string.high_energy
        override fun getBatteryLevel(): Int = 100
    };

    abstract fun getId(): Int;
    abstract fun getTitleResourceId(): Int;
    abstract fun getBatteryLevel(): Int;

    companion object {
        fun fromId(id: Int): EnergyLevel {
            return entries.first { it.getId() == id }
        }
    }
}