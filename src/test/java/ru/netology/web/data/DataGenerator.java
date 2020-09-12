package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String getCity() {
        List<String> cityList = Arrays.asList("Майкоп","Горно-Алтайск","Уфа","Улан-Удэ","Махачкала","Магас","Нальчик","Элиста",
                "Черкесск","Петрозаводск","Сыктывкар","Симферополь","Йошкар-Ола","Саранск","Якутск","Владикавказ","Казань",
                "Кызыл","Ижевск","Абакан","Грозный","Чебоксары","Барнаул","Чита","Петропавловск-Камчатский","Краснодар",
                "Красноярск","Пермь","Владивосток","Ставрополь","Хабаровск","Благовещенск","Архангельск","Астрахань",
                "Белгород","Брянск","Владимир","Волгоград","Вологда","Воронеж","Иваново","Иркутск","Калининград","Калуга",
                "Кемерово","Киров","Кострома","Курган","Курск","Санкт-Петербург","Липецк","Магадан","Москва","Мурманск","Нижний Новгород",
                "Великий Новгород","Новосибирск","Омск","Оренбург","Орёл","Пенза","Псков","Ростов-на-Дону","Рязань","Самара","Саратов",
                "Южно-Сахалинск","Екатеринбург","Смоленск","Тамбов","Тверь","Томск","Тула","Тюмень","Ульяновск","Челябинск","Ярославль",
                "Севастополь","Биробиджан","Нарьян-Мар","Ханты-Мансийск","Анадырь","Салехард");
        Random random = new Random();
        String randomElement = cityList.get(random.nextInt(cityList.size()));
        return randomElement;
    }

    public static String getCurrentDate() {
        LocalDate date = LocalDate.now();
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    public static String getDate() {
        LocalDate date = LocalDate.now().plusDays(3);
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getNewDate() {
        LocalDate date = LocalDate.now().plusDays(10);
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getPhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().cellPhone();
    }
}