Feature: Тестовое задание
  Scenario: Вход в личный кабинет, очистка просмотренных товаров, просмотр новых товаров, открытие Просмотренных товаров 2
    Given Войти в личный кабинет "https://my.rozetka.com.ua" через "email" под "kru4enko@ukr.net" с паролем "Test1234567"
    When Очистить список просмотренных товаров
    And Считать детали товара "http://rozetka.com.ua/zte_blade_a610_grey/p11673582/"
    And Считать детали товара "http://rozetka.com.ua/brateck_lpa52_446/p9212592/"
    And Считать детали товара "http://rozetka.com.ua/transcend_microsdhc_16gb_class_10_plus_sd_adapter_ts16gusdhc10/p156560/"
    And Считать список просмотренных товаров
    Then Сверить детали товаров