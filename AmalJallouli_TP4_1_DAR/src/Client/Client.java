package Client;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import Server.Server;
public class Client {



    public static void main(String[] args) {
        try {
           
            // creation du socket client
            DatagramSocket socket = new DatagramSocket();
            
            // indiquer l'adresse du serveur au quel le client va se connecter 
            InetAddress serverAddress = InetAddress.getByName("localhost"); 
            
            // lire le nom et prenom du client
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez votre message (Prénom Nom) : ");
            String message = scanner.nextLine();
            
            // Envoie le message du client indiquant le nom et le prenom au serveur 
            
         // Convertir le message en un tableau de bytes 
            byte[] messageData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(messageData, messageData.length, serverAddress, Server.port);
            socket.send(packet);

            // Recevoir la réponse du serveur.
            byte[] buffer = new byte[1024];
            DatagramPacket reponsePacket = new DatagramPacket(buffer, buffer.length);
            
            //recevoir la reponse du serveur
            socket.receive(reponsePacket);
            
            String reponse = new String(reponsePacket.getData(), 0, reponsePacket.getLength());
            
            //afficher la reponse du serveur
            System.out.println("Réponse du serveur : " + reponse);

            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}