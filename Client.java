import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("10.252.1.14", 8080); // Connect to the server running on localhost:1234
        System.out.println("Connected to server.");

        // Create input and output streams for the socket
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        // Read messages from the user and send them to the server
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String message = userInput.readLine();
            output.println(message);

            String response = input.readLine();
            System.out.println("Server response: " + response);
        }
    }
}
