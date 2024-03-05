package commands

import devices.IDemoDevice
import kotlinx.coroutines.coroutineScope
import space.kscience.controls.client.controlsPropertyFlow
import space.kscience.dataforge.names.Name
import space.kscience.magix.api.MagixEndpoint
import space.kscience.magix.rsocket.rSocketWithWebSockets

suspend fun main(): Unit = coroutineScope {
    // connect to Magix
    val sendEndpoint = MagixEndpoint.rSocketWithWebSockets("localhost")
    sendEndpoint.controlsPropertyFlow(
        "controls-kt", Name.of("demo"), IDemoDevice.sin).collect {
        // just print update to console
        println(it)
    }
}
