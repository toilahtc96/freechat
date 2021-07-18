package com.tvd12.freechat.entity;

import java.io.InputStream;
import java.util.Collection;
import java.util.logging.Logger;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.stream.EzyAnywayInputStreamLoader;
import com.tvd12.ezyfox.util.EzyLoggable;

import lombok.Setter;

@Setter
@EzySingleton
public class FirebaseClient extends EzyLoggable {

    protected FirebaseMessaging firebaseMessaging;

    public FirebaseClient() {

        try {
            InputStream inputStream = EzyAnywayInputStreamLoader.builder()
                    .build()
                    .load("message-project-6af76-firebase-adminsdk-wf3g6-f45c6e3a87.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .build();
            FirebaseApp.initializeApp(options);

            firebaseMessaging = FirebaseMessaging.getInstance();
        } catch (Exception e) {
            firebaseMessaging = null;
            logger.info("Loi khi khoi tao firebase: "+ e.getMessage());
        }
    }

    public boolean notify(String token, NotifyMessage message) {
        Message m = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(message.getTitle())
                        .setBody(message.getBody())
                        .setImage(message.getImageURL())
                        .build())
                .putAllData(message.getData())
                .setToken(token)
                .build();
        try {
            firebaseMessaging.send(m);
            return true;
        } catch (Exception e) {
            logger.error("notify to: {} error", token, e);
            return false;
        }
    }

//    public int notify(Collection<String> tokens, NotifyMessage message) {
//        MulticastMessage m = MulticastMessage.builder()
//                .setNotification(Notification.builder()
//                        .setTitle(message.getTitle())
//                        .setBody(message.getBody())
//                        .setImage(message.getImageURL())
//                        .build())
//                .putAllData(message.getData())
//                .addAllTokens(tokens)
//                .build();
//        try {
//            BatchResponse response = firebaseMessaging.sendMulticast(m);
//            return response.getSuccessCount();
//        } catch (FirebaseMessagingException e) {
//            logger.error("notify to: {} clients error", tokens.size(), e);
//            return 0;
//        }
//    }

}