package com.tvd12.freechat.service;

import com.tvd12.freechat.common.entity.ChatUserFirebaseToken;
import com.tvd12.freechat.entity.ChatMessage;
import java.util.Collection;
import java.util.Set;

public interface NotificationService {

    boolean notify(Set<ChatUserFirebaseToken> setUsername, ChatMessage message);

    int notify(Collection<String> tokens, ChatMessage message);

}
