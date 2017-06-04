/*package edu.technopolis.homework.messenger.net;

import edu.technopolis.homework.messenger.messages.LoginMessage;
import edu.technopolis.homework.messenger.messages.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessengerServer {

    public static void main(String[] args) {
        DB.createConnection();
        ExecutorService executorService = Executors.newFixedThreadPool(500);
        try (ServerSocket serverSocket = new ServerSocket(19000)) {
            while (true) {
                Socket accept = serverSocket.accept(); //блок
                System.out.println(accept.getInetAddress());
                executorService.submit(() -> handle(accept));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DB.closeConnection();
    }
    private static void handle(Socket accept) {
        try (InputStream inputStream = accept.getInputStream();
             OutputStream outputStream = accept.getOutputStream()) {
            Session session = new Session(accept, inputStream, outputStream);
            Protocol protocol = new StringProtocol();
            while (true){
                final byte[] buffer = new byte[1024*64];
                int read = session.getIn().read(buffer);
                if(read>0){
                    try{
                        LoginMessage msg =  (LoginMessage)protocol.decode(Arrays.copyOf(buffer,read));
                        DB.onLogin(msg.getLogin(), msg.getPassword());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
*/