package ru.ilya.springbotproject.updateHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import ru.ilya.springbotproject.handler.CallbackHandler;
import ru.ilya.springbotproject.handler.MessageHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateCatcher {
    private List<SendMessage> sendMessageList = new ArrayList<>();
    @Autowired
    private MessageHandler sendMessageHandler;
    @Autowired
    private CallbackHandler callbackHandler;



    public List<SendMessage> updateCatcherMessages(Update update, ChatMember chatMember) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String chatId = update.getMessage().getChatId().toString();
            String messageText = update.getMessage().getText();
            String userId = update.getMessage().getFrom().getId().toString();

            sendMessageList = sendMessageHandler.messageHandler(chatId, messageText, userId);

        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String callData = update.getCallbackQuery().getData();

            sendMessageList = callbackHandler.callbackHandler(chatId, callData, chatMember);
        }

        return sendMessageList;
    }
}
