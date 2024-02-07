package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.database.dao_classes.NotificationDao;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.exceptions.DuplicatedNotificationException;
import com.dicii.ispw.project.exceptions.NotExistentNotification;
import com.dicii.ispw.project.exceptions.NotOnlineUserException;
import com.dicii.ispw.project.firstview.obj.NotificationPopUpController;
import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.beans.SubscriptionRequestBean;
import com.dicii.ispw.project.patterns.observer.NotificationCatch;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SubscribeToNutritionistController implements Serializable {
    private final NutritionistDao nutritionistDAO;
    private final PatientDao patientDAO;
    private final NotificationDao notificationDAO;
    private static final String SUBSCRIPTION_REQUEST = "SubscriptionRequest";
    private static final String SUBSCRIPTION_REFUSED = "SubscriptionRefused";
    private static final String SUBSCRIPTION_ACCEPTED = "SubscriptionAccepted";

    public SubscribeToNutritionistController(){
        nutritionistDAO = new NutritionistDao();
        patientDAO = new PatientDao();
        notificationDAO = new NotificationDao();
    }
    public List<NutritionistBean> getNutritionistList(int limitNumber,int offset){
        List<Nutritionist> nutritionistList = new ArrayList<>(nutritionistDAO.getNutritionistList(limitNumber,offset));
        List<NutritionistBean> nutritionistBeanList = new ArrayList<>();
        String email;
        String name;
        String surname;
        String description;
        String dateOfBirth;
        String iban;
        String cost;
        for (Nutritionist nutritionist : nutritionistList) {
            email = nutritionist.getEmail();
            name = nutritionist.getName();
            surname = nutritionist.getSurname();
            description = nutritionist.getDescription();
            dateOfBirth = nutritionist.getDateOfBirth();
            iban = nutritionist.getIban();
            cost = nutritionist.getCosto();
            nutritionistBeanList.add(new NutritionistBean(email, name, surname, description, dateOfBirth, iban, cost));
        }
        return nutritionistBeanList;
    }
    public void sendSubscriptionRequest(SubscriptionRequestBean subscriptionRequestBean) throws NotOnlineUserException, DuplicatedNotificationException {
        Patient subscriber = new Patient(subscriptionRequestBean.getSubscriber());
        Nutritionist nutritionist = new Nutritionist(subscriptionRequestBean.getNutritionist());
        String currentDate = subscriptionRequestBean.getDateTime();
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest(subscriber,nutritionist,currentDate);
        notificationDAO.setSubscriptionRequest(subscriptionRequest);
        sendNotificationPopUp(subscriber,nutritionist,currentDate,"Subscription request");

    }

    public List<NotificationBean> getSubscriptionRequestNotifications() throws NotExistentNotification {
        User user = new User(Session.getSessionInstance().getLoggedUser().getEmail());
        List<Notification> notificationList = new ArrayList<>(notificationDAO.getNotificationListByUser(user,SUBSCRIPTION_REQUEST));
        return setNotificationBeanList(notificationList);
    }

    public void acceptSubscriptionRequest(NotificationBean notificationBean) throws NotOnlineUserException{
        User sender = new User(notificationBean.getSender());
        User receiver = new User(notificationBean.getReceiver());
        String dateTime = notificationBean.getDateTime();
        String message = notificationBean.getMessage();
        Notification notification = new Notification(sender,receiver,dateTime,message,SUBSCRIPTION_ACCEPTED);
        notificationDAO.setAcceptedOrRefusedSubscriptionRequest(notification);
        sendNotificationPopUp(sender,receiver,dateTime,message);
    }

    public void refuseSubscriptionRequest(NotificationBean notificationBean) throws NotOnlineUserException {
        User sender = new User(notificationBean.getSender());
        User receiver = new User(notificationBean.getReceiver());
        String dateTime = notificationBean.getDateTime();
        String message = notificationBean.getMessage();
        Notification notification = new Notification(sender, receiver, dateTime, message,SUBSCRIPTION_REFUSED);
        notificationDAO.setAcceptedOrRefusedSubscriptionRequest(notification);
        sendNotificationPopUp(sender,receiver,dateTime,message);
    }

    public List<NotificationBean> getSubscriptionAcceptedNotifications() throws NotExistentNotification {
        User user = new User(Session.getSessionInstance().getLoggedUser().getEmail());
        List<Notification> notificationList = new ArrayList<>(notificationDAO.getNotificationListByUser(user,SUBSCRIPTION_ACCEPTED));
        return setNotificationBeanList(notificationList);
    }

    public List<NotificationBean> getSubscriptionRefusedNotifications() throws NotExistentNotification{
        User user = new User(Session.getSessionInstance().getLoggedUser().getEmail());
        List<Notification> notificationList = new ArrayList<>(notificationDAO.getNotificationListByUser(user, SUBSCRIPTION_REFUSED));
        return setNotificationBeanList(notificationList);
    }
    public void removeNotificationFromList(NotificationBean notificationBean) {
        User sender = new User(notificationBean.getSender());
        User destination = new User(notificationBean.getReceiver());
        String message = notificationBean.getMessage();
        String dateTime = notificationBean.getDateTime();
        Notification notification = new Notification(sender,destination,message,dateTime,SUBSCRIPTION_REFUSED);
        notificationDAO.removeNotification(notification);
    }
    private List<NotificationBean> setNotificationBeanList(List<Notification> notificationList){
        List<NotificationBean> notificationBeanList = new ArrayList<>();
        String sender;
        String destination;
        String message;
        String dateTime;
        for(Notification notification : notificationList){
            sender = notification.getSender().getEmail();
            destination = notification.getReceiver().getEmail();
            message = notification.getMessage();
            dateTime = notification.getDateTime();
            notificationBeanList.add(new NotificationBean(sender,destination,dateTime,message));
        }
        return notificationBeanList;
    }

    public void subscribePatientToNutritionist(NutritionistBean nutritionistBean) throws NotOnlineUserException {
        Patient patient = new Patient(Session.getSessionInstance().getLoggedUser().getEmail());
        Nutritionist nutritionist = new Nutritionist(nutritionistBean.getEmail());
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        patientDAO.setSubscriptionRequestPatient(patient,nutritionist);
        notificationDAO.removeSubscriptionRequestNotificationPatient(patient);
        sendNotificationPopUp(patient,nutritionist,dateTime,"Subscription made");
    }
    private void sendNotificationPopUp(User sender,User destination,String dateTime,String message) throws NotOnlineUserException{
        try {
            NotificationCatch notificationCatch = new NotificationCatch();
            Notification notification = new Notification(sender, destination, dateTime, message, null);
            notificationCatch.notifyUser(notification, destination);
        }catch (RemoteException | NotBoundException e ){
            throw new NotOnlineUserException("The user is currently offline he will see the notification of subscription request");
        }
    }

    public void showNotification(Notification notification){
        String sender = notification.getSender().getEmail();
        String receiver = notification.getReceiver().getEmail();
        String dateTime = notification.getDateTime();
        String message = notification.getMessage();
        NotificationBean notificationBean = new NotificationBean(sender,receiver,dateTime,message);
        Platform.runLater(() ->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/firstGui/notification/NotificationPopUp.fxml"));
                Parent root = loader.load();

                NotificationPopUpController notificationPopUpController = loader.getController();

                Scene scene = new Scene(root);
                scene.setFill(null);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);

                Screen screen = Screen.getPrimary();
                stage.setX(screen.getBounds().getMaxX() - 333);
                stage.setY(screen.getBounds().getMaxY() -165);
                notificationPopUpController.initNotification(notificationBean,stage);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
