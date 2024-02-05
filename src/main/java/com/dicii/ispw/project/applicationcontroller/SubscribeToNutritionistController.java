package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.database.dao_classes.NotificationDao;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.exceptions.NotExistentNotification;
import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.beans.SubscriptionRequestBean;
import com.dicii.ispw.project.patterns.observer.NotificationCatch;
import com.dicii.ispw.project.patterns.singleton.Session;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SubscribeToNutritionistController {
    private final NutritionistDao nutritionistDAO;
    private final PatientDao patientDAO;
    private final NotificationDao notificationDAO;

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
    public void sendSubscriptionRequest(SubscriptionRequestBean subscriptionRequestBean) throws RemoteException,NotBoundException {
        Patient subscriber = new Patient(subscriptionRequestBean.getSubscriber());
        Nutritionist nutritionist = new Nutritionist(subscriptionRequestBean.getNutritionist());
        String currentDate = subscriptionRequestBean.getDateTime();
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest(subscriber,nutritionist,currentDate);
        notificationDAO.setSubscriptionRequest(subscriptionRequest);
        NotificationCatch notificationCatch = new NotificationCatch();
        Notification notification = new Notification(subscriber,nutritionist,currentDate,"Subscription Request",null);
        notificationCatch.notifyUser(notification,nutritionist);

    }

    public List<NotificationBean> getSubscriptionRequestNotifications() throws NotExistentNotification {
        User user = new User(Session.getSessionInstance().getLoggedUser().getEmail());
        List<Notification> notificationList = new ArrayList<>(notificationDAO.getNotificationListByUser(user,"SubscriptionRequest"));
        return setNotificationBeanList(notificationList);
    }

    public void acceptSubscriptionRequest(NotificationBean notificationBean) throws RemoteException,NotBoundException{
        User sender = new User(notificationBean.getSender());
        User receiver = new User(notificationBean.getReceiver());
        String dateTime = notificationBean.getDateTime();
        String message = notificationBean.getMessage();
        Notification notification = new Notification(sender,receiver,dateTime,message,"SubscriptionAccepted");
        notificationDAO.setAcceptedOrRefusedSubscriptionRequest(notification);
        NotificationCatch notificationCatch = new NotificationCatch();
        notificationCatch.notifyUser(notification,receiver);
    }

    public void refuseSubscriptionRequest(NotificationBean notificationBean) throws RemoteException, NotBoundException{
        User sender = new User(notificationBean.getSender());
        User receiver = new User(notificationBean.getReceiver());
        String dateTime = notificationBean.getDateTime();
        String message = notificationBean.getMessage();
        Notification notification = new Notification(sender, receiver, dateTime, message,"SubscriptionRefused");
        notificationDAO.setAcceptedOrRefusedSubscriptionRequest(notification);
        NotificationCatch notificationCatch = new NotificationCatch();
        notificationCatch.notifyUser(notification,receiver);
    }

    public List<NotificationBean> getSubscriptionAcceptedNotifications() throws NotExistentNotification {
        User user = new User(Session.getSessionInstance().getLoggedUser().getEmail());
        List<Notification> notificationList = new ArrayList<>(notificationDAO.getNotificationListByUser(user,"SubscriptionAccepted"));
        return setNotificationBeanList(notificationList);
    }

    public List<NotificationBean> getSubscriptionRefusedNotifications() throws NotExistentNotification{
        User user = new User(Session.getSessionInstance().getLoggedUser().getEmail());
        List<Notification> notificationList = new ArrayList<>(notificationDAO.getNotificationListByUser(user, "SubscriptionRefused"));
        return setNotificationBeanList(notificationList);
    }
    public void removeNotificationFromList(NotificationBean notificationBean) {
        User sender = new User(notificationBean.getSender());
        User destination = new User(notificationBean.getReceiver());
        String message = notificationBean.getMessage();
        String dateTime = notificationBean.getDateTime();
        Notification notification = new Notification(sender,destination,message,dateTime,"SubscriptionRefused");
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

    public void subscribePatientToNutritionist(NutritionistBean nutritionistBean) throws NotBoundException, RemoteException {
        Patient patient = new Patient(Session.getSessionInstance().getLoggedUser().getEmail());
        Nutritionist nutritionist = new Nutritionist(nutritionistBean.getEmail());
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        patientDAO.setSubscriptionRequestPatient(patient,nutritionist);
        notificationDAO.removeSubscriptionRequestNotificationPatient(patient);
        NotificationCatch notificationCatch = new NotificationCatch();
        Notification notification = new Notification(patient,nutritionist,dateTime,"Subscription made",null);
        notificationCatch.notifyUser(notification,nutritionist);
    }
}
