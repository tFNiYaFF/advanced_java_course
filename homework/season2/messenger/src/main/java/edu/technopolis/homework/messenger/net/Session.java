package edu.technopolis.homework.messenger.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.technopolis.homework.messenger.User;
import edu.technopolis.homework.messenger.messages.*;

/**
 * Сессия связывает бизнес-логику и сетевую часть.
 * Бизнес логика представлена объектом юзера - владельца сессии.
 * Сетевая часть привязывает нас к определнному соединению по сети (от клиента)
 */
public class Session {

    public SocketChannel getSocket() {
        return socket;
    }

    /**
     * Пользователь сессии, пока не прошел логин, user == null
     * После логина устанавливается реальный пользователь
     */
    // сокет на клиента
    private SocketChannel socket;

    public User getUser() {
        return user;
    }

    /**
     * С каждым сокетом связано 2 канала in/out
     */
    private User user;
    private static StringProtocol protocol = new StringProtocol();


    public Session(SocketChannel socket, User user){
        this.socket = socket;
        this.user = user;
    }

    public void send(Message msg) throws ProtocolException, IOException {
        socket.write(ByteBuffer.wrap(protocol.encode(msg)));
    }

    public void onMessage(Message msg) throws SQLException, IOException, ProtocolException {
        // TODO: Пришло некое сообщение от клиента, его нужно обработать
        Type type = msg.getType();
        long senderId = user.getId();
        switch (type){
            case MSG_CHAT_CREATE:
                if(senderId!=-1) {
                    ChatCreateMessage chatCreateMessage = (ChatCreateMessage) msg;
                    chatCreateMessage.setSenderId(senderId);
                    DB.chatCreate(senderId, chatCreateMessage.getUsers());
                }
                break;
            case MSG_LOGIN:
                LoginMessage loginMsg =  (LoginMessage)msg;
                long userId = DB.onLogin(loginMsg.getLogin(), loginMsg.getPassword());
                if(userId!=-1) {
                    user.setId(userId);
                    StatusMessage sm = new StatusMessage();
                    sm.setStatus(true);
                    sm.setType(Type.MSG_STATUS);
                    sm.setInfo("You login successful!");
                    this.send(sm);
                }
                else{
                    StatusMessage sm = new StatusMessage();
                    sm.setStatus(false);
                    sm.setType(Type.MSG_STATUS);
                    sm.setInfo("Password is wrong!");
                    this.send(sm);
                }

                break;
            case MSG_TEXT:
                StatusMessage sm=new StatusMessage();
                sm.setType(Type.MSG_STATUS);
                boolean flag=false;
                if(senderId!=-1) {
                    TextMessage textMessage = (TextMessage) msg;
                    textMessage.setSenderId(senderId);
                    long chat = textMessage.getChatId();
                    long[] chatUsers = DB.getChatUsers(chat);
                    if (chatUsers != null) {
                        for (int i=0;i<chatUsers.length;i++){
                            if (chatUsers[i]==senderId){
                                DB.saveMessage(senderId, chat, textMessage.getText());
                                sm.setStatus(true);
                                sm.setInfo("Your message was sent");
                                flag=true;
                                break;
                            }
                            if (!flag){
                                sm.setStatus(false);
                                sm.setInfo("You could not send messages in this chat!");
                            }
                        }
                       // NonBlockingServer.sendToOthers(chatUsers,textMessage);

                    }else {
                        sm.setStatus(false);
                        sm.setInfo("This chat does not exist");
                    }

                }else{
                    sm.setStatus(false);
                    sm.setInfo("You could not sent messages without login");
                }
                send(sm);
                break;
            case MSG_CHAT_HIST:
                ChatHistoryMessage chatHistoryMessage = (ChatHistoryMessage) msg;
                //senderId = user.getId();
                long chatId = chatHistoryMessage.getChatId();
                long[] chatUsers = DB.getChatUsers(chatId);
                flag = false;
                if (chatUsers != null) {
                    for (int i=0;i<chatUsers.length;i++){
                        if (chatUsers[i]==senderId){
                            flag=true;
                            ArrayList<TextMessage> messages = DB.getHistory(chatId);
                            ChatHistoryResult historyMessages = new ChatHistoryResult();
                            historyMessages.setType(Type.MSG_CHAT_HIST_RESULT);
                            historyMessages.setMessages(messages);
                            send(historyMessages);
                            break;
                        }
                    }
                    if (!flag){
                        sm = new StatusMessage();
                        sm.setStatus(false);
                        sm.setType(Type.MSG_STATUS);
                        sm.setInfo("You could not read messages from this chat!");
                        this.send(sm);
                    }
                    // NonBlockingServer.sendToOthers(chatUsers,textMessage);

                }else {
                    sm = new StatusMessage();
                    sm.setStatus(false);
                    sm.setType(Type.MSG_STATUS);
                    sm.setInfo("This chat does not exist!");
                    this.send(sm);
                }
                break;
        }
    }

    public void close() {
        // TODO: закрыть in/out каналы и сокет. Освободить другие ресурсы, если необходимо
    }
}