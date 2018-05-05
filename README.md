# PixelPi
IoT Android Application that controls NeoPixel devices

## Getting Started

### Prerequisites
Hardware needed:
* Android device API 15+
* At least one Raspberry Pi (RPi), version 2 or higher
* Raspberry Pi power supply, 2.5 A or greater
* 8Gb+ sd card
Tools for connecting Neopixels to RPi. See for details: https://learn.adafruit.com/neopixels-on-raspberry-pi/wiring


#### Installing

Software needed:
* Android application/apk
* Raspbian for sd card OS. See for download: https://www.raspberrypi.org/downloads/raspbian/
* MySQL. See: https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-14-04
* Pip3. Insure pip3 is installed with the command: sudo apt-get install python3-pip
* PyMySQL. Run the command: sudo apt-get install python3-pymysql
* Twisted. Run the command: sudo pip3 install Twisted version=16.0.0
* The latest version of Twisted requires pyopenssl functionality, so version 16.0.0 must be used on the Raspberry Pi.
* Autobahn. Run the command: sudo pip3 install autobahn
* Ensure pigpio or neopixel or both is installed.
* For Neopixel: https://learn.adafruit.com/neopixels-on-raspberry-pi/software
* For pigpio run the command: sudo apt-get install pigpio python-pigpio python3-pigpio

* Working database.  See the SQL code snippet below for details.

```sql
Create a database for this project by running the commands:
CREATE DATABASE PixelPi;
USE PixelPi

CREATE TABLE Tasks(
Id INT(8) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
device_name VARCHAR(4) NOT NULL,
command TEXT NOT NULL);
```

Move the PixelPi folder to a suitable location on the RPi.  For the client, adjust the client_settings.txt file to contain the correct settings.  The proper device codes are listed below:
 - Neopixel, WS2812: NP
 - Neopixel, WS2811: N1
 - 5050 rgbs: 50


### Running Tests

Use Case:
Run the Android application. Click on the gear icon. Input your the IP Address and port. Click on the list icon to view devices received from the server. Click on a device. Click on the plus symbol to add a task. Choose display, then click next. Input RGB values for color each. To add a color, click the plus symbol. When finished selecting colors, click the Done button (not keyboard). The colors of the NeoPixel device should change. 

#### Break down into end to end tests

### Deployment

Run the server, then the client.  They can be run by the command: ```sudo python3 <your file name and path>```
Alternatively, the server and client can both be run on startup by means of the .bashrc file.  This file can be edited on Raspbian by using the command: ```sudo nano /home/pi/.bashrc``` 

## Built With

Gradle - Android build tool 

## Coding Environments

- IDLE - Python
- Android Studio - Java

## Versioning

## Authors

[Jack Bartolone](https://github.com/Jaylooker) and [Ben Osborne](https://github.com/osborn14) 

## License

[MIT License](LICENSE.md)

## Acknowledgements

+ Autobahn 
+ Twisted

