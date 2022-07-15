package ru.ilya.springbotproject.keyboard.replyKeyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

@Component
public class ReplyKeyboard {

    public ReplyKeyboardMarkup startKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboardList = new ArrayList<>();
        KeyboardRow firstKeyboardRow1 = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        keyboardList.clear();
        firstKeyboardRow1.clear();
        firstKeyboardRow1.add("\uD83C\uDFA5ФИЛЬМЫ\uD83C\uDFA5");
        keyboardList.add(firstKeyboardRow1);
        replyKeyboardMarkup.setKeyboard(keyboardList);
        return replyKeyboardMarkup;
    }
}
