
import socket




serverAddressPort   = ("192.168.137.202", 8010)

bufferSize          = 1024



# Create a UDP socket at client side

UDPClientSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)



# Send to server using created UDP socket

while True:
    msgFromClient       = input("inserisci il messaggio da mandare \n")
    bytesToSend         = str.encode(msgFromClient)
    UDPClientSocket.sendto(bytesToSend, serverAddressPort)
