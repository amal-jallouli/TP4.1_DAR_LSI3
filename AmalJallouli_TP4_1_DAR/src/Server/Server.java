package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class Server {

   public final static int port = 1778;
    private static byte[] buffer = new byte[1024];

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Traitement du message recu par le client).
                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                // réponse du serveur.
             // Séparer la chaîne 'message' en morceaux en utilisant l'espace comme délimiteur.
                String[] parts = message.split(" ");
              //Vérifier si la chaîne 'message' contient exactement 2 parties séparées par un espace
                if (parts.length == 2) {
                	// Stocker la première partie du message dans la variable 'prenom'
                    String prenom = parts[0];
                
                    // Stocker la deuxième partie du message dans la variable 'nom
                    String nom = parts[1];
                    
                    // reponse du serveur formulée
                    String reponse = "Bienvenue " + prenom + " " + nom;
                
                    // Convertir la reponse en un tableau de bytes 
                    byte[] reponseData = reponse.getBytes();
                    
                    //Créer un DatagramPacket pour envoyer la réponse aux coordonnées du client
                    DatagramPacket reponsePacket = new DatagramPacket(reponseData, reponseData.length, clientAddress, clientPort);
                 
                    // Envoie le DatagramPacket à l'aide de la socket UDP.
                    socket.send(reponsePacket);

                    System.out.println("Message reçu du client : " + message);
                    System.out.println("Réponse envoyée au client : " + reponse);
                    System.out.println("Adresse du client : " + clientAddress.getHostAddress());
                    System.out.println("Numéro de port du client : " + clientPort);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
