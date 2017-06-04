package edu.technopolis.homework.messenger.net;

import edu.technopolis.homework.messenger.messages.*;

import java.util.ArrayList;

/**
 * Простейший протокол передачи данных
 */
public class StringProtocol implements Protocol {

    public static final String DELIMITER = ";";

    @Override
    public Message decode(byte[] bytes) throws ProtocolException {
        String str = new String(bytes);
        //System.out.println("decoded: " + str);
        String[] tokens = str.split(DELIMITER);
        Type type = Type.valueOf(tokens[0]);
        switch (type) {
            case MSG_TEXT:
                TextMessage textMsg = new TextMessage();
                textMsg.setChatId(Long.parseLong(tokens[1]));

                textMsg.setText(tokens[2]); // нужно делиметер передалать
                textMsg.setType(type);
                return textMsg;
            case MSG_LOGIN:
                LoginMessage loginMessage = new LoginMessage();
                loginMessage.setLogin(tokens[1]);
                loginMessage.setPassword(tokens[2]);
                loginMessage.setType(Type.MSG_LOGIN);
                return loginMessage;
            case MSG_CHAT_CREATE:
                ChatCreateMessage chatCreateMessage = new ChatCreateMessage();
                chatCreateMessage.setType(Type.MSG_CHAT_CREATE);
                for(int i=1; i<tokens.length; i++){
                    chatCreateMessage.add(Long.parseLong(tokens[i]));
                }
                return chatCreateMessage;
            case MSG_STATUS:
                StatusMessage statusMessage = new StatusMessage();
                statusMessage.setStatus(Boolean.parseBoolean(tokens[1]));
                statusMessage.setInfo(tokens[2]);
                statusMessage.setType(Type.MSG_STATUS);
                return statusMessage;
            case MSG_CHAT_HIST:
                ChatHistoryMessage chatHistoryMessage=new ChatHistoryMessage();
                chatHistoryMessage.setChatId(Long.parseLong(tokens[1]));
                chatHistoryMessage.setType(Type.MSG_CHAT_HIST);
                return chatHistoryMessage;
            case MSG_CHAT_HIST_RESULT:
                ChatHistoryResult chatHistoryResult = new ChatHistoryResult();
                ArrayList<TextMessage> messages = new ArrayList<>();
                chatHistoryResult.setType(Type.MSG_CHAT_HIST_RESULT);
                for(int i=1; i<tokens.length-1; i++){
                    messages.add(new TextMessage(tokens[i+1],Long.parseLong(tokens[i])));
                    i++;
                }
                chatHistoryResult.setMessages(messages);
                return chatHistoryResult;
            default:
                throw new ProtocolException("Invalid type: " + type);
        }
    }

    @Override
    public byte[] encode(Message msg) throws ProtocolException {
        StringBuilder builder = new StringBuilder();
        Type type = msg.getType();
        builder.append(type).append(DELIMITER);
        switch (type) {
            case MSG_TEXT:
                TextMessage sendMessage = (TextMessage) msg;
                builder.append(sendMessage.getChatId()).append(DELIMITER).append(sendMessage.getText());
                break;
            case MSG_LOGIN:
                LoginMessage loginMessage = (LoginMessage) msg;
                builder.append(loginMessage.getLogin()).append(DELIMITER).append(loginMessage.getPassword());
                break;
            case MSG_CHAT_CREATE:
                ChatCreateMessage chatCreateMessage = (ChatCreateMessage) msg;
                for(long user:chatCreateMessage.getUsers()){
                    builder.append(user);
                    builder.append(DELIMITER);
                }
                builder.replace(builder.length()-1, builder.length(),"");
                break;
            case MSG_STATUS:
                StatusMessage statusMessage = (StatusMessage) msg;
                builder.append(statusMessage.getStatus()).append(DELIMITER).append(statusMessage.getInfo());
                break;
            case MSG_CHAT_HIST:
                ChatHistoryMessage chatHistoryMessage=(ChatHistoryMessage)msg;
                builder.append(chatHistoryMessage.getChatId());
                break;
            case MSG_CHAT_HIST_RESULT:
                ChatHistoryResult chatHistoryResult = (ChatHistoryResult) msg;
                for(int i=0; i<chatHistoryResult.getMessages().size(); i++){
                    builder.append(chatHistoryResult.getMessages().get(i).getSenderId()).append(DELIMITER)
                            .append(chatHistoryResult.getMessages().get(i).getText()).append(DELIMITER);
                }
                builder.replace(builder.length()-1,builder.length(),"");
                break;
            default:
                throw new ProtocolException("Invalid type: " + type);


        }
        //System.out.println("encoded: " + builder);
        return builder.toString().getBytes();
    }

    private Long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            // who care
        }
        return null;
    }
}