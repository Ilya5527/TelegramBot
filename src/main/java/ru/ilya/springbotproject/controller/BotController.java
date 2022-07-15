package ru.ilya.springbotproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ilya.springbotproject.thread.DeleteThread;
import ru.ilya.springbotproject.updateHandler.UpdateCatcher;


import java.util.ArrayList;
import java.util.List;

@Controller
public class BotController extends TelegramLongPollingBot implements Runnable {

    @Value("${username}")
    private String username;
    @Value("${token}")
    private String token;
    private ChatMember chatMember;
    private Long userId;
    @Autowired
    private UpdateCatcher updateCatcher;
    @Autowired
    private DeleteThread deleteThread;
    private List<SendMessage> sendMessageList = new ArrayList<>();
    private Thread t;


    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            userId = update.getCallbackQuery().getFrom().getId();
        } catch (NullPointerException e){
            userId = null;
        }

        sendMessageList = updateCatcher.updateCatcherMessages(update, getChatMember(userId));
        startExecution();

    }

    private ChatMember getChatMember(Long userId){
        if (userId == null){
            chatMember = null;
        } else {
            try {
                chatMember = execute(new GetChatMember("@corparationTrash", userId));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        return chatMember;
    };

    private void startExecution(){
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < sendMessageList.size(); i++) {
            try {
                execute(sendMessageList.get(i));
            } catch (TelegramApiException e) {
                deleteThread.startDelete(sendMessageList.get(i).getChatId());
            }

        }
        sendMessageList.clear();
    }
}
