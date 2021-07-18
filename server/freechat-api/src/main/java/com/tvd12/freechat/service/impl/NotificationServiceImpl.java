package com.tvd12.freechat.service.impl;

import com.google.firebase.messaging.*;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.freechat.common.entity.ChatUserFirebaseToken;
import com.tvd12.freechat.entity.ChatMessage;
import com.tvd12.freechat.entity.FirebaseClient;
import com.tvd12.freechat.entity.NotifyMessage;
import com.tvd12.freechat.service.NotificationService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@EzySingleton
public class NotificationServiceImpl extends EzyLoggable implements NotificationService {

    @EzyAutoBind
    private FirebaseClient fbClient;

    public boolean notify(Set<ChatUserFirebaseToken> setUserToken, ChatMessage message) {

        NotifyMessage notifyMessage = new NotifyMessage();
        notifyMessage.setBody(message.getMessage());
        notifyMessage.setTitle("Bạn có tin nhắn mới từ "+ message.getSender());
        notifyMessage.setImageURL("https://ibb.co/R703kxf");
        Map<String, String> bodyTest = new HashMap<>();
        bodyTest.put("Nội dung: ", message.getMessage());
        notifyMessage.setData(bodyTest);

        try {
            setUserToken.stream().forEach(userToken->{
                fbClient.notify(userToken.getFirebaseToken(), notifyMessage);
            });
            return true;
        } catch (Exception e) {
            logger.error("notify to:  error",  e);
            return false;
        }
    }

//    public int notify(Collection<String> tokens, ChatMessage message) {
//        NotifyMessage notifyMessage = new NotifyMessage();
//        notifyMessage.setBody(message.getMessage());
//        notifyMessage.setTitle("Bạn có tin nhắn mới từ "+ message.getSender());
//        notifyMessage.setImageURL("https://ibb.co/R703kxf");
//        Map<String, String> bodyTest = new HashMap<>();
//        bodyTest.put("Nội dung: ", message.getMessage());
//        notifyMessage.setData(bodyTest);
//        try {
//            int response = fbClient.notify(tokens,notifyMessage);
//            return response;
//        } catch (Exception e) {
//            logger.error("notify to: {} clients error", tokens.size(), e);
//            return 0;
//        }
//    }
}
