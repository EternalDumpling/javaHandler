import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234); // Server socket running on port 1234
        System.out.println("Server started.");

        while (true) {
            Socket socket = serverSocket.accept(); // Waiting for a client to connect
            System.out.println("Client connected.");

            // Create input and output streams for the socket
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Add the output stream to the list of clients
            clients.add(output);

            // Create a new thread to handle messages from the client
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        String message = input.readLine();
                        System.out.println("Received message: " + message);

                        // Send the message to all connected clients
                        for (PrintWriter client : clients) {
                            client.println(message);
                        }
                    }
                } catch (IOException e) {
                    // Client disconnected
                    System.out.println("Client disconnected.");
                    clients.remove(output);
                }
            });
            thread.start();
        }
    }
}
