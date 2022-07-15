package ru.ilya.springbotproject.keyboard.inlineKeyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
@Component
public class CallbackKeyboard {

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    public InlineKeyboardMarkup checkInlineKeyboard() {

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();

        keyboardButtonsRow1.add(new InlineKeyboardButton().builder().text("Подписаться на 1 канал☑️").url("https://t.me/cringecinema").build());
        keyboardButtonsRow2.add(new InlineKeyboardButton().builder().text("Подписаться на 2 канал☑️").url("https://t.me/+8aZ16ZOef1k5NGRi").build());
        keyboardButtonsRow3.add(new InlineKeyboardButton().builder().text("Проверка подписки❗").callbackData("CheckSub").build());

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup showFilms() {

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

        keyboardButtonsRow1.add(new InlineKeyboardButton().builder().text("\uD83D\uDCF7Фильмы из TikTok\uD83D\uDCF7").url("https://t.me/tiktok_filmZZZ").build());

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;

    }

    public InlineKeyboardMarkup adminPanel() {

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(new InlineKeyboardButton().builder().text("Показать пост").callbackData("showPost").build());
        keyboardButtonsRow1.add(new InlineKeyboardButton().builder().text("Количество людей").callbackData("countPeople").build());
        keyboardButtonsRow2.add(new InlineKeyboardButton().builder().text("Запустить рассылку").callbackData("posting").build());
        keyboardButtonsRow2.add(new InlineKeyboardButton().builder().text(" ̶И̶з̶м̶е̶н̶и̶т̶ь̶ ̶п̶о̶с̶т̶").callbackData("changePost").build());

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);


        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
