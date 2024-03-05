package devices

import kotlinx.coroutines.launch
import space.kscience.controls.spec.*
import space.kscience.dataforge.context.Context
import space.kscience.dataforge.context.Factory
import space.kscience.dataforge.meta.Meta
import java.time.Instant
import kotlin.math.sin
import kotlin.time.Duration.Companion.seconds

class DemoDevice(context: Context, meta: Meta) : DeviceBySpec<DemoDevice>(IDemoDevice, context, meta), IDemoDevice {
    override var timeScaleState = 5000.0
    override var sinScaleState = 1.0

    private fun time(): Instant = Instant.now()

    override fun sinValue(): Double = sin(time().toEpochMilli().toDouble() / timeScaleState) * sinScaleState

    companion object : DeviceSpec<IDemoDevice>(), Factory<DemoDevice> {
        override fun build(context: Context, meta: Meta) = DemoDevice(context, meta)
        override suspend fun IDemoDevice.onOpen() {
            launch {
                read(IDemoDevice.sinScale)
                read(IDemoDevice.timeScale)
            }
            doRecurring(1.seconds) {
                println("tick")
                read(IDemoDevice.sin)
            }
        }
    }
}