Feature: Тестовое задание
  Scenario: Вход в личный кабинет, очистка просмотренных товаров, просмотр новых товаров, открытие Просмотренных товаров
    Given Войти в личный кабинет "https://my.rozetka.com.ua" через "email" под "kru4enko@ukr.net" с паролем "Test1234567"
    When Очистить список просмотренных товаров
    And Считать детали товара "http://rozetka.com.ua/brateck_lpa52_446/p9212592/"
    And Считать список просмотренных товаров
    Then Сверить детали товаров