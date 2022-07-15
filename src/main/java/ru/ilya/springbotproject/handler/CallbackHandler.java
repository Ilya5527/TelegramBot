package ru.ilya.springbotproject.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import ru.ilya.springbotproject.entity.User;
import ru.ilya.springbotproject.keyboard.inlineKeyboard.CallbackKeyboard;
import ru.ilya.springbotproject.thread.DeleteThread;
import ru.ilya.springbotproject.thread.GetAllUsersThread;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallbackHandler<T> {

    private SendMessage sendMessage = new SendMessage();
    private List<T> sendMessageList = new ArrayList<>();
    private List<User> usersList = new ArrayList<>();
    private String answerOnMessage;
    private GetAllUsersThread thread;
    private CallbackKeyboard callbackKeyboard;
    private int sumPeople;
    private T t;

    public CallbackHandler(GetAllUsersThread thread, CallbackKeyboard callbackKeyboard) {
        this.thread = thread;
        this.callbackKeyboard = callbackKeyboard;
    }

    public List<T> callbackHandler(String chat_id, String callData, ChatMember chatMember){

        answerOnMessage = null;
        sendMessage.setReplyMarkup(null);

        if (chatMember.getStatus().equals("creator") ||
                chatMember.getStatus().equals("administrator") ||
                chatMember.getStatus().equals("member")){
            if (callData.equals("CheckSub")){
                answerOnMessage = "\uD83D\uDCFC Чтобы получить название фильма, нажмите сюда ⤵️";
                sendMessage.setReplyMarkup(callbackKeyboard.showFilms());

            } else if (callData.equals("showPost")){
                answerOnMessage = "Новые фильмы уже в боте\uD83E\uDD16!" +
                        "\n" +
                        "Введи /start, чтобы получить новые фильмы!";
            } else if (callData.equals("countPeople")){

                thread.startThreadForGetUsers();
                sumPeople = thread.getAllUser().size();

                answerOnMessage = "Количество людей в боте: " + sumPeople;
            } else if (callData.equals("posting")){
                answerOnMessage = "Новые фильмы уже в боте\uD83E\uDD16!" +
                        "\n" +
                        "Введи /start, чтобы получить новые фильмы!";


                thread.startThreadForGetUsers();
                usersList = thread.getAllUser();

                for (int i = 0; i < usersList.size(); i++) {
                    sendMessageList.add((T) sendMessage
                                    .builder()
                                        .chatId(usersList.get(i).getUserId())
                                        .text(answerOnMessage)
                                    .build());
                }
                return (List<T>) sendMessageList;


            } else if (callData.equals("changePost")){
                answerOnMessage = "Данная функция пока недоступна";
            }
        } else {
            answerOnMessage = "❌ОШИБКА❌" + "\n\n" + "\uD83D\uDCDD Для использования бота, Вы должны нажать на все кнопки и подписаться на все каналы!";
            sendMessage.setReplyMarkup(callbackKeyboard.checkInlineKeyboard());
        }

        sendMessage.setChatId(chat_id);
        sendMessage.setText(answerOnMessage);
        sendMessageList.add((T) sendMessage);
        return sendMessageList;
    }
}
