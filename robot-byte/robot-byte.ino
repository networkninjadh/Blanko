//#define NUMBER_VARIABLES 1
//#define NUMBER_FUNCTIONS 5

// Libraries
#include <Adafruit_CC3000.h>
#include <ccspi.h>
#include <string.h>
#include <SPI.h>
#include <aREST.h>
#include <avr/wdt.h>
#include <Servo.h>

#include <UDPServer.h>

// CC3000 pins
/*#define ADAFRUIT_CC3000_IRQ   3
#define ADAFRUIT_CC3000_VBAT  5
#define ADAFRUIT_CC3000_CS    10*/

//Sensor pins
#define trigPin 3
#define echoPin 2

//Light pin
int lightPin = 22;

int c = 0;

//Initialize servos
Servo leftServo;
Servo rightServo;
Servo laserServo;
int angle = 90;

//Pin  13 led flashes to test for wifi connectivity
int ledPin = 13;

bool flag = true;


/*#define WLAN_SSID       "dlink"
#define WLAN_PASS       "blanko123"
#define WLAN_SECURITY   WLAN_SEC_WPA2
#define LISTEN_PORT           80
#define UDP_READ_BUFFER_SIZE 20

// CC3000 instance
Adafruit_CC3000 cc3000 = Adafruit_CC3000(ADAFRUIT_CC3000_CS, ADAFRUIT_CC3000_IRQ, ADAFRUIT_CC3000_VBAT,
                                         SPI_CLOCK_DIV2);

Adafruit_CC3000_Server server(LISTEN_PORT);
UDPServer udp(LISTEN_PORT);*/

void setup() {
  Serial.begin(9600);
  rightServo.attach(30);
  leftServo.attach(31);
  laserServo.attach(36);
  laserServo.write(angle);
  
  pinMode(13, OUTPUT);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(lightPin, OUTPUT);
  
  /*Serial.println(F("\nInitialising the CC3000 ..."));
  // Set up CC3000 and get connected to the wireless network.
  if (!cc3000.begin())
  {
    Serial.println(F("Unable to initialise the CC3000! Check your wiring?"));
    while(1);
  }

  Serial.println(F("Attempting to connect to Q237"));
  if (!cc3000.connectToAP(WLAN_SSID, WLAN_PASS, WLAN_SECURITY)) 
  {
    Serial.println(F("Failed!"));
    while(1);
  }
  
  Serial.println(F("Request DHCP"));
  while (!cc3000.checkDHCP())
  {
    delay(100);
  }

  while (! displayConnectionDetails()) {
    delay(1000);
  }
  
  // Start server
  server.begin();
  udp.begin();
  Serial.println(F("Listening for connections..."));
  
  displayConnectionDetails(); */

}

void loop() {
  
  /*digitalWrite(lightPin, HIGH);
  delay(1000);
  digitalWrite(lightPin, LOW);
  delay(1000);*/
  
  long duration, distance;
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  distance = (duration/2) / 29.1;

  if (distance >= 4000 || distance <=2){
    Serial.println("Out of range");
  }
  else {
    Serial.print(distance);
    Serial.println(" cm");
  }

  if (distance < 10) {
    flag = false;
    Serial.println(flag + "suck it");
  }
  
  /*if (cc3000.checkConnected()) {
    digitalWrite(ledPin, HIGH);
    delay(1000);
    digitalWrite(ledPin, LOW);
    delay(1000);
  }
  else {
    digitalWrite(ledPin, LOW);
  }*/

  /*if(udp.available()) {
    char buffer[UDP_READ_BUFFER_SIZE];
    int n = udp.readData(buffer, UDP_READ_BUFFER_SIZE);
    Serial.print("n: "); Serial.println(n);
    
    for (int i = 0; i < n; ++i) {
          uint8_t c = buffer[i];
          Serial.print("c: "); Serial.println(c);*/
  if(Serial.available() > 0){
    c = Serial.read();
  }
          
          if (c == 'w') {
            forward();
          }

          if (c == 'a') {
            left();
          }

          if (c == 's') {
            
            if (flag = true){
              backward();
              //distance = (duration/2) / 29.1;
              //Serial.println("stopping");
            }
            else{
              stopMotor();
              Serial.println("going");
            }
            
          }

          if (c == 'd') {
            right();
          }

          if (c == 'q' ) {
            stopMotor();
          }

          if (c == '1') {
            Serial.print("light on");
            lightOn();
          }

          if (c == '0') {
            Serial.print("light off");
            lightOff();
          }

          if (c == '2') {
            laserUp();
          }

          if (c == '3') {
            laserDown();
          }

          //if (c == '4') {
            //laserStop();
          //}
    
  
    
}

void forward() {
  rightServo.write(180);
  leftServo.write(0);
}

void left() {
  rightServo.write(0);
  leftServo.write(0);
}

void backward() {
  rightServo.write(0);
  leftServo.write(180);
}

void right() {
  rightServo.write(180);
  leftServo.write(180);
}

void stopMotor() {
  rightServo.write(90);
  leftServo.write(90);
}

void lightOn() {
  digitalWrite(lightPin, HIGH);
}

void lightOff() {
  digitalWrite(lightPin, LOW);
}

void laserDown() {
  angle = angle + 2;
  if (angle > 120){
    angle = 120;
  }
  Serial.println(angle);
  laserServo.write(angle);
  laserStop();
}

void laserUp() {
  angle = angle - 2;
  if (angle < 0 ){
    angle = 0;
  }
  Serial.println(angle);
  laserServo.write(angle);
  laserStop();
}

void laserStop() {
  laserServo.write(angle);
}

/*
//Diplays Connection details in terminal
bool displayConnectionDetails(void)
{
  uint32_t ipAddress, netmask, gateway, dhcpserv, dnsserv;
  
  if(!cc3000.getIPAddress(&ipAddress, &netmask, &gateway, &dhcpserv, &dnsserv))
  {
    Serial.println(F("Unable to retrieve the IP Address!\r\n"));
    return false;
  }
  else
  {
    Serial.print(F("\nIP Addr: ")); cc3000.printIPdotsRev(ipAddress);
    Serial.print(F("\nNetmask: ")); cc3000.printIPdotsRev(netmask);
    Serial.print(F("\nGateway: ")); cc3000.printIPdotsRev(gateway);
    Serial.print(F("\nDHCPsrv: ")); cc3000.printIPdotsRev(dhcpserv);
    Serial.print(F("\nDNSserv: ")); cc3000.printIPdotsRev(dnsserv);
    Serial.println();
    return true;
  }
}*/

