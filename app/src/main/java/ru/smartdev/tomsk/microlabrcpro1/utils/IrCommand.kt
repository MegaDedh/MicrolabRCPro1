package ru.smartdev.tomsk.microlabrcpro1.utils


class IrCommand(private val cfg: NecProtocolConfig) {
    private val pattern = mutableListOf<Int>()

    fun getPattern(address: UByte, command: UByte): IntArray {
        pattern.clear()
        pattern.addAll(cfg.PREAMBLE)
        addByteToPattern(address)
        addByteToPattern(address.inv())
        addByteToPattern(command)
        addByteToPattern(command.inv())
        pattern.add(cfg.LAST_IMPULSE)
        return pattern.toIntArray()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun addByteToPattern(byte: UByte) {

        byteToBoolArray(byte).forEach {
            if (it) {
                pattern.addAll(cfg.LOGICAL_1)
            } else {
                pattern.addAll(cfg.LOGICAL_0)
            }
        }
    }

    @ExperimentalUnsignedTypes
    private fun byteToBoolArray(byte: UByte) =
        booleanArrayOf(
            (byte and 1u) != 0.toUByte(),
            (byte and 2u) != 0.toUByte(),
            (byte and 4u) != 0.toUByte(),
            (byte and 8u) != 0.toUByte(),
            (byte and 16u) != 0.toUByte(),
            (byte and 32u) != 0.toUByte(),
            (byte and 64u) != 0.toUByte(),
            (byte and 128u) != 0.toUByte()
        )
}
