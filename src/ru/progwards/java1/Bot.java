package ru.progwards.java1;

import org.telegram.telegrambots.ApiContextInitializer;
import ru.progwards.java1.testlesson.ProgwardsTelegramBot;

public class Bot extends ProgwardsTelegramBot {

    public static void main(String[] args) {
        System.out.println("Hellow Bot");

        ApiContextInitializer.init();

        Bot bot = new Bot();
        bot.username = "имя бота";
        bot.token = "токен";
        bot.start();
    }
}
