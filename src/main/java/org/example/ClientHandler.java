package org.example;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            // Create input and output streams for the client socket
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String clientData;
            while ((clientData = reader.readLine()) != null) {
                System.out.println("Received from client: " + clientData);

                // You can process client data here and send a response back

                // Example: Sending a response
                String response = "Server response: Hello, client!";
                output.write(response.getBytes());
                output.flush();
            }
        } catch (SocketException e) {
            // Handle the SocketException (connection terminated by client) here
            System.out.println("Client connection terminated abruptly.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the client socket when done
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

