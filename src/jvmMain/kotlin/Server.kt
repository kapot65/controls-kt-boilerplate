import devices.DemoDevice
import devices.DemoDevice.Companion.onOpen
import kotlinx.coroutines.coroutineScope
import space.kscience.controls.client.launchMagixService
import space.kscience.controls.manager.DeviceManager
import space.kscience.controls.manager.install
import space.kscience.dataforge.context.Context
import space.kscience.dataforge.context.request
import space.kscience.dataforge.meta.Meta
import space.kscience.magix.api.MagixEndpoint
import space.kscience.magix.rsocket.rSocketWithWebSockets
import space.kscience.magix.server.startMagixServer

suspend fun main(): Unit = coroutineScope {
    // create Context
    val context = Context("clientContext") {
        plugin(DeviceManager)
    }

    // get Device Manager and install device into it
    val manager = context.request(DeviceManager)
    val device = DemoDevice.build(context, Meta.EMPTY)
    manager.install("demo", device)
    device.onOpen() // BUG!: you must execute onOpen manually to start device

    // start Magix Loop
    startMagixServer()

    // attach Device Manager to Magix Loop
    run {
        val magixEndpoint = MagixEndpoint.rSocketWithWebSockets("localhost")
        manager.launchMagixService(magixEndpoint)
    }
}