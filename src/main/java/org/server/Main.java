package org.server;

import org.server.server.Server;

public class Main {
    public static void main(String[] args) {
        int port = 8080; // Change this to the desired port number
        Server server = new Server(port);
        server.start();    }
}