package edu.technopolis.homework.messenger.net;

import edu.technopolis.homework.messenger.messages.TextMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Никита on 21.05.2017.
 */
public class DB {
    private static final String host = "jdbc:mysql://localhost:3306/messenger";
    private static final String user = "root";
    private static final String password = "";
    private static Connection connection;

    private static final String loginPasswordQuery = "SELECT count(*),id FROM user WHERE login = ? AND password = ?";
    private static final String loginQuery = "SELECT count(*) FROM user WHERE login=?";
    private static final String registryQuery = "INSERT INTO user (login,password) VALUES(?,?)";
    private static final String checkUsers = "SELECT chat_id FROM chat WHERE participants= ?";
    private static final String insertChat = "INSERT INTO chat (participants) values (?)";
    private static final String insertMessage = "INSERT INTO message (sender_id, chat_id, text) VALUES (?,?,?)";
    private static final String getChatUsers = "SELECT participants FROM chat WHERE chat_id = ?";
    private static final String getChatHistory = "SELECT sender_id, text FROM message WHERE chat_id = ? ORDER BY messageTime";

    public static void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(host, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static long chatCreate(long senderId, HashSet<Long> users) throws SQLException {
        long[] arr = new long[users.size()+1];
        int i = 0;
        for(Object x: users.toArray()){
            arr[i++] = (long)x;
        }
        arr[i] = senderId;
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for(i=1; i<arr.length; i++){
            sb.append(" "+arr[i]);
        }
        PreparedStatement stmt = connection.prepareStatement(checkUsers);
        stmt.setString(1, sb.toString());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return rs.getLong(1);
        }
        else{
            stmt = connection.prepareStatement(insertChat);
            stmt.setString(1,sb.toString());
            stmt.executeUpdate();
            stmt = connection.prepareStatement(checkUsers);
            stmt.setString(1, sb.toString());
            rs = stmt.executeQuery();
            rs.next();
            return rs.getLong(1);
        }
    }

    public static void saveMessage(long senderId, long chatId, String text) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(insertMessage);
        stmt.setLong(1,senderId);
        stmt.setLong(2,chatId);
        stmt.setString(3, text);
        stmt.executeUpdate();
    }

    public static long[] getChatUsers(long chatId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(getChatUsers);
        stmt.setLong(1, chatId);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        String participants = rs.getString(1);
        String[] participantsArr = participants.split(" ");
        long[] result = new long[participantsArr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Long.parseLong(participantsArr[i]);
        }
        return result;
    }

    public static ArrayList<TextMessage> getHistory(long chatId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(getChatHistory);
        stmt.setLong(1,chatId);
        ArrayList<TextMessage> result = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(new TextMessage(rs.getString(2),rs.getLong(1)));
        }
        return result;
    }

    public static long onLogin(String login, String password) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(loginQuery);
        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        if (rs.getInt(1) == 0) {
            stmt = connection.prepareStatement(registryQuery);
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.executeUpdate();
        }
        stmt = connection.prepareStatement(loginPasswordQuery);
        stmt.setString(1, login);
        stmt.setString(2, password);
        rs = stmt.executeQuery();
        rs.next();
        if (rs.getInt(1) == 0) {
            return -1;
        } else {
            return rs.getInt(2);
        }
    }
}
