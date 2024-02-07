package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NotificationQueries;
import com.dicii.ispw.project.exceptions.DuplicatedNotificationException;
import com.dicii.ispw.project.exceptions.NotExistentNotification;
import com.dicii.ispw.project.models.Notification;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.SubscriptionRequest;
import com.dicii.ispw.project.models.User;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao {
    private static final String SENDER = "Sender";
    private static final String DESTINATION = "Destination";

    private static final String MESSAGE = "Message";

    private static final String DATE = "DateTime";


    public List<Notification> getNotificationListByUser(User user,String type) throws NotExistentNotification{
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        List<Notification> notificationResultList = new ArrayList<>();
        User sender;
        User destination;
        String message;
        String dateTime;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = NotificationQueries.selectListOfNotification(statement,user,type);
            if(!resultSet.isBeforeFirst()){
                throw new NotExistentNotification("There are not notification in this section");
            }
            while(resultSet.next()){
                sender = new User(resultSet.getString(SENDER));
                destination = new User(resultSet.getString(DESTINATION));
                message = resultSet.getString(MESSAGE);
                dateTime = resultSet.getString(DATE);
                notificationResultList.add(new Notification(sender,destination,dateTime,message,type));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return notificationResultList;
    }

    public void setSubscriptionRequest(SubscriptionRequest subscriptionRequest) throws DuplicatedNotificationException{
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        Notification notification = new Notification(subscriptionRequest.getSubscriber(),subscriptionRequest.getNutritionist(),subscriptionRequest.getDateTime(),"Subscription Request","SubscriptionRequest");
        try(Statement statement = connection.createStatement()){
            NotificationQueries.insertNotification(statement,notification);
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedNotificationException("Subscription request already sent to this user");
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void setAcceptedOrRefusedSubscriptionRequest(Notification notification) {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NotificationQueries.deleteSubscriptionRequestNotification(statement,notification);
            NotificationQueries.insertNotification(statement,notification);
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void removeNotification(Notification notification) {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NotificationQueries.deleteNotification(statement,notification);
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void removeSubscriptionRequestNotificationPatient(Patient patient) {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NotificationQueries.deleteAllSubscriptionRequestOfPatient(statement,patient);
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
