package org.server.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;

    public Client(Socket socket, String userName){
        try {


            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = userName;
        }
        catch (IOException ex){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageTosend = scanner.nextLine();
                bufferedWriter.write(userName + " " + messageTosend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }

        catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

        public void ListenForMessage(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String messafromGroupChat;
                    while(socket.isConnected()){
                        try{
                            messafromGroupChat = bufferedReader.readLine();
                            System.out.println(messafromGroupChat);
                            closeEverything(socket, bufferedReader, bufferedWriter);
                        }
                        catch (IOException e){
                            closeEverything(socket, bufferedReader, bufferedWriter);
                        }
                    }
                }
            }).start();
        }
        public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
            try{
                if(bufferedReader != null){
                    bufferedReader.close();
                }
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
                if(socket != null){
                    socket.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }



}
