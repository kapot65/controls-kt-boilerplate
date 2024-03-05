package devices

import space.kscience.controls.api.Device
import space.kscience.controls.spec.*
import space.kscience.dataforge.meta.MetaConverter

interface IDemoDevice : Device {
    var timeScaleState: Double
    var sinScaleState: Double

    fun sinValue(): Double

    companion object : DeviceSpec<IDemoDevice>() {
        val timeScale by mutableProperty(MetaConverter.double, IDemoDevice::timeScaleState)
        val sinScale by mutableProperty(MetaConverter.double, IDemoDevice::sinScaleState)

        val sin by doubleProperty { sinValue() }

        val resetScale by unitAction {
            write(timeScale, 5000.0)
            write(sinScale, 1.0)
        }
    }
}