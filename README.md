# PixelPi
IoT Android Application that controls NeoPixel devices. See [Server](https://github.com/osborn14/PixelPi) for details on its configuration.  

## Getting Started

### Prerequisites
+ Android device API 15+
+ Server

#### Installing

Download the Android apk and install on your device. 

### Running Tests

```./gradlew test```

```./gradlew connectedAndroidTest```

Ad hoc:
Run the Android application. Click on the gear icon. Input your the IP Address and port. Click on the list icon to view devices received from the server. Click on a device. Click on the plus symbol to add a task. Choose display, then click next. Input RGB values for color each. To add a color, click the plus symbol. When finished selecting colors, click the Done button (not keyboard). The colors of the NeoPixel device should change. 

## Authors

[Jack Bartolone](https://github.com/Jaylooker) and [Ben Osborne](https://github.com/osborn14) 

## License

[MIT License](LICENSE.md)

## Acknowledgements

+ [Jackson](https://github.com/FasterXML/jackson) - Java JSON library
