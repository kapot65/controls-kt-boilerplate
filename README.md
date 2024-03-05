# controls-kt-boilerplate
Boilerplate project for [controls-kt](https://git.sciprog.center/kscience/controls-kt)

## Boilerplate structure
This boilerplate contains minimal setup to work with controls-kt framework
It contains
1. Simple device example
   - [IDemoDevice.kt](src/commonMain/kotlin/devices/IDemoDevice.kt) - device specification
   - [DemoDevice.kt](src/jvmMain/kotlin/devices/DemoDevice.kt) - device realization
2. [Server.kt](src/jvmMain/kotlin/Server.kt) - simple device server. This code:
   - initializes DeviceManager with demo device,
   - starts Magix loop server,
   - attaches DeviceManager to Magix loop.
3. [Main.kt](src/jsMain/kotlin/Main.kt) - simple client example. This code connects 
   to Magix Loop, listens device property change and prints it to console.  

## Setting project
1. change project name
    - inside [settings.gradle.kts](settings.gradle.kts)
    - change js bundle name inside [index.html](src/jsMain/resources/index.html)

## Usage
1. start device server - [Server.kt](src/jvmMain/kotlin/Server.kt) (Ctrl-Shift-F10 from Idea)
2. start client
   ```shell
   ./gradlew jsBrowserRun
   ```
   or from Idea gradle panel
3. check browser console to see device property changes
