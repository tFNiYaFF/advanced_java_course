package edu.technopolis.homework.messenger.net;

import edu.technopolis.homework.messenger.User;
import edu.technopolis.homework.messenger.messages.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/**
 * Created by timur on 11.05.17.
 */
public class NonBlockingServer {
    private static HashMap<Session, ByteBuffer> map = new HashMap<>();

    public static void main(String[] args) {
        DB.createConnection();

        try (ServerSocketChannel open = openAndBind()) {
            open.configureBlocking(false);
            while (true) {
                SocketChannel accept = open.accept(); //не блокируется
                if (accept != null) {
                    System.out.println(accept.getLocalAddress());
                    accept.configureBlocking(false);
                    Session session = new Session(accept, new User(-1));
                    map.put(session, ByteBuffer.allocateDirect(1024));
                }
                map.keySet().removeIf(sc -> {
                    SocketChannel realSc = sc.getSocket();
                    return !realSc.isOpen();
                });
                Protocol protocol = new StringProtocol();
                map.forEach((sc,byteBuffer) -> {
                    try {
                        int read = sc.getSocket().read(byteBuffer);
                        if (read == -1) {
                            close(sc.getSocket());
                        } else if (read > 0) {
                            byteBuffer.flip();
                            byte[] buffer = new byte[byteBuffer.remaining()];
                            byteBuffer.get(buffer);

                            Message msg = protocol.decode(Arrays.copyOf(buffer,read));
                            sc.onMessage(msg);
                            byteBuffer.compact();
                        }
                    } catch (Exception e) {
                        close(sc.getSocket());
                        e.printStackTrace();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        DB.closeConnection();
    }

   /* public static void sendToOthers(long[] chatUsers,TextMessage textMessage) throws IOException, ProtocolException {
        for (long chatUser : chatUsers) {
            for (Session session : map.keySet()) {
                if (session.getUser().getId() == chatUser) {
                    session.send(textMessage);
                }
            }
        }
    }*/

    private static ServerSocketChannel openAndBind() throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        open.bind(new InetSocketAddress(19000));
        return open;
    }

    private static void close(SocketChannel sc) {
        try {
            sc.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
