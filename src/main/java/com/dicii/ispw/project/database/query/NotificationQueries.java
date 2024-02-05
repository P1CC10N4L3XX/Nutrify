package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.Notification;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NotificationQueries {

    private NotificationQueries() {
        // Do nothing, just to prevent instantiation
    }
    public static ResultSet selectListOfNotification(Statement statement, User user,String type) throws SQLException {
        String query = String.format("SELECT * FROM notifiche WHERE Destination = '%s' AND Type = '%s'",user.getEmail(),type);
        return statement.executeQuery(query);
    }

    public static void insertNotification(Statement statement, Notification notification) throws SQLException{
        String sender = notification.getSender().getEmail();
        String destination = notification.getReceiver().getEmail();
        String message = notification.getMessage();
        String dateTime = notification.getDateTime();
        String type = notification.getType();
        String sql = String.format("INSERT INTO notifiche (Sender,Destination,Message,DateTime,Type) values ('%s','%s','%s','%s','%s')",sender,destination,message,dateTime,type);
        statement.execute(sql);
    }

    public static void deleteNotification(Statement statement, Notification notification) throws SQLException{
        String sender = notification.getSender().getEmail();
        String destination = notification.getReceiver().getEmail();
        String type = notification.getType();
        String sql = String.format("DELETE FROM notifiche WHERE Sender = '%s' AND Destination = '%s' AND Type = '%s'",sender,destination,type);
        statement.execute(sql);
    }

    public static void deleteSubscriptionRequestNotification(Statement statement, Notification notification) throws SQLException{
        String sender = notification.getReceiver().getEmail();
        String destination = notification.getSender().getEmail();
        String type = "SubscriptionRequest";
        String sql = String.format("DELETE FROM notifiche WHERE Sender = '%s' AND Destination = '%s' AND Type = '%s'",sender,destination,type);
        statement.execute(sql);
    }

    public static void deleteAllSubscriptionRequestOfPatient(Statement statement,Patient patient) throws SQLException{
        String sql = String.format("DELETE FROM notifiche WHERE (Sender = '%s' OR Destination = '%s') AND (Type = 'SubscriptionRequest' OR Type = 'SubscriptionAccepted')",patient.getEmail(),patient.getEmail());
        statement.execute(sql);
    }
}
