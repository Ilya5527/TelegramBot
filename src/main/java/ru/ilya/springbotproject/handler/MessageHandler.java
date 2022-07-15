package ru.ilya.springbotproject.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.ilya.springbotproject.entity.User;
import ru.ilya.springbotproject.keyboard.inlineKeyboard.CallbackKeyboard;
import ru.ilya.springbotproject.keyboard.replyKeyboard.ReplyKeyboard;
import ru.ilya.springbotproject.thread.SaveThread;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageHandler {
    private SendMessage sendMessage = new SendMessage();
    private List<SendMessage> list = new ArrayList<>();
    private String answerOnMessage = null;
    @Autowired
    private SaveThread saveThread;
    @Autowired
    private ReplyKeyboard replyKeyboard;
    @Autowired
    private CallbackKeyboard callbackKeyboard;

    public List<SendMessage> messageHandler (String chat_id, String message_text, String userId){

        answerOnMessage = "❌Упс, похоже бот еще не знает таких функций❌" +
                "\n" +
                "Введите /start, чтобы начать пользоваться ботом! \uD83E\uDD16";
        sendMessage.setReplyMarkup(null);
        list.clear();

        if (message_text.equals("\uD83C\uDFA5ФИЛЬМЫ\uD83C\uDFA5")) {
            answerOnMessage = "\uD83D\uDCDD Для использования бота, Вы должны нажать на все кнопки и подписаться на все каналы!";
            sendMessage.setReplyMarkup(callbackKeyboard.checkInlineKeyboard());
        } else if(message_text.equals("/start")){

            saveThread.startSave(new User(userId));

            answerOnMessage = "\uD83E\uDD64Все фильмы из ТикТока\n" +
                    "\n" +
                    "Жми на кнопку чтобы узнать названия фильмов ⤵";
            sendMessage.setReplyMarkup(replyKeyboard.startKeyboard());
        } else if (message_text.equals("log4152")){
            answerOnMessage = "Доступ к админ-панеле ⤵";
            sendMessage.setReplyMarkup(callbackKeyboard.adminPanel());
        }


        sendMessage.setChatId(chat_id);
        sendMessage.setText(answerOnMessage);
        list.add(sendMessage);

        return list;
    }
}
