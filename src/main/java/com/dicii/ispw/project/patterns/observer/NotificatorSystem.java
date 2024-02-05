package com.dicii.ispw.project.patterns.observer;

import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.firstview.obj.NotificationPopUpController;
import com.dicii.ispw.project.models.Notification;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NotificatorSystem extends UnicastRemoteObject implements Observer{
    public NotificatorSystem() throws RemoteException {
        super();
    }
    @Override
    public void update(Notification notification) throws RemoteException{
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
                System.out.println(e.getMessage());
            }

        });
    }
}
