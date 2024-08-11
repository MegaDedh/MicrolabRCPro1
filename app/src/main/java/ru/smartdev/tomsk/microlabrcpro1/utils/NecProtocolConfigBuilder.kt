package ru.smartdev.tomsk.microlabrcpro1.utils

class NecProtocolConfigBuilder {

    private var PREAMBLE_0: Int = 4500
    private var PREAMBLE_1: Int = 9000
    private var INTERVAL_0: Int = 1120
    private var INTERVAL_1: Int = 2250
    private var IMPULSE: Int = 560
    private var LAST_IMPULSE: Int = 560

    fun preamble0(value: Int): NecProtocolConfigBuilder {
        this.PREAMBLE_0 = value
        return this
    }

    fun preamble1(value: Int): NecProtocolConfigBuilder {
        this.PREAMBLE_1 = value
        return this
    }

    fun interval0(value: Int): NecProtocolConfigBuilder {
        this.INTERVAL_0 = value
        return this
    }

    fun interval1(value: Int): NecProtocolConfigBuilder {
        this.INTERVAL_1 = value
        return this
    }

    fun impulse(value: Int): NecProtocolConfigBuilder {
        this.IMPULSE = value
        return this
    }

    fun lastImpulse(value: Int): NecProtocolConfigBuilder {
        this.LAST_IMPULSE = value
        return this
    }

    fun build(): NecProtocolConfig = NecProtocolConfig(
        PREAMBLE_0,
        PREAMBLE_1,
        INTERVAL_0,
        INTERVAL_1,
        IMPULSE,
        LAST_IMPULSE
    )
}
