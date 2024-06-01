package eu.sumindlift.mindlift.data.entity

import eu.sumindlift.mindlift.R

enum class EnergyLevel {

    LOW {
        override fun getTitleResourceId(): Int = R.string.low_energy
        override fun getBatteryLevel(): Int = 0
    },
    HALF {
        override fun getTitleResourceId(): Int = R.string.half_energy
        override fun getBatteryLevel(): Int = 50
    },
    HIGH {
        override fun getTitleResourceId(): Int = R.string.high_energy
        override fun getBatteryLevel(): Int = 100
    };

    abstract fun getTitleResourceId(): Int;
    abstract fun getBatteryLevel(): Int;

}