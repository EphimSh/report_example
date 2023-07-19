# Проект представляет собой автоматизацию тестовых сценариев на примере онлайн магазина спортивных товаров primekraft.ru<br>

> primekraft.ru — онлайн магазин спортивных товаров основанный в г.Санкт-Петербург,<br>преимущество primekraft - собственное производство, которое ничем не хуже западных аналогов, а возможно даже и лучше.

## Содержание:
- [Использованный стек технологий](#computer-использованный-стек-технологий)
- [Запуск автотестов](#arrow_forward-Запуск-автотестов)
- [Сборка в Jenkins](#-Сборка-в-Jenkins)
- [Пример Allure-отчета](#-Пример-Allure-отчета)
- [Интеграция с Allure-TestOps](#-Интеграция-с-Allure-TestOps)
- [Интеграция с Jira](#-Интеграция-с-Jira)
- [Уведомления в Telegram с использованием бота](#-Уведомления-в-Telegram-с-использованием-бота)
- [Видео примера запуска тестов в Selenoid](#-видео-примера-запуска-теста-в-Selenoid)

## Использованный стек технологий

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="media/logos/Intelij_IDEA.svg">
<img width="6%" title="Java" src="media/logos/Java.svg">
<img width="6%" title="Selenide" src="media/logos/Selenide.svg">
<img width="6%" title="Selenoid" src="media/logos/Selenoid.svg">
<img width="6%" title="Allure Report" src="media/logos/Allure_Report.svg">
<img width="5%" title="Allure TestOps" src="media/logos/AllureTestOps.svg">
<img width="6%" title="Gradle" src="media/logos/Gradle.svg">
<img width="6%" title="JUnit5" src="media/logos/JUnit5.svg">
<img width="6%" title="GitHub" src="media/logos/GitHub.svg">
<img width="6%" title="Jenkins" src="media/logos/Jenkins.svg">
<img width="6%" title="Telegram" src="media/logos/Telegram.svg">
<img width="5%" title="Jira" src="media/logos/Jira.svg">
</p>

- В данном проекте автотесты написаны на языке <code>Java</code> с использованием фреймворка для тестирования Selenide. 
- В качестве сборщика был использован - <code>Gradle</code>.  
- Использованы фреймворки <code>JUnit 5</code> и [Selenide](https://selenide.org/).
- При прогоне тестов браузер запускается в [Selenoid](https://aerokube.com/selenoid/).
- Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета<br>и отправкой результатов в <code>Telegram</code> при помощи бота. 
- Осуществлена интеграция с <code>Allure TestOps</code> и <code>Jira</code>

Содержание Allure-отчета:
* Шаги теста;
* Скриншот страницы на последнем шаге;
* Page Source;
* Логи браузерной консоли;
* Видео выполнения автотеста.

## Запуск автотестов

### Возможнный запуск тестов из терминала
```
gradle clean test
gradle clean search_test
gradle clean shopCart_test
gradle clean catalog_test
gradle clean smoke_test
```
При выполнении команд, тесты запустятся удаленно в <code>Selenoid</code>.


При необходимости также можно переопределить параметры запуска
```
clean
${TASK}
"-Dbrowser=${BROWSER}"
"-DbrowserVersion=${BROWSER_VERSION}"
"-DbrowserSize=${BROWSER_SIZE}"
"-DbaseUrl=${BASE_URL}"
"-DremoteDriverUrl=https://user1:1234@${REMOTE_URL}/wd/hub/"
```

### Параметры сборки

* <code>BROWSER_NAME</code> – браузер, в котором будут выполняться тесты. По-умолчанию - <code>chrome</code>.
* <code>BROWSER_VERSION</code> – версия браузера, в которой будут выполняться тесты. По-умолчанию - <code>99.0</code>.
* <code>BROWSER_SIZE</code> – размер окна браузера, в котором будут выполняться тесты.
* <code>BASE_URL</code> – Url, по которому будет открываться тестируемое приложение. По-умолчанию - <code>1920x1080</code>.
* <code>REMOTE_BROWSER_URL</code> – адрес удаленного сервера, на котором будут запускаться тесты.
  
## Попробовать можно здесь 👉🏻 <a href='https://jenkins.autotests.cloud/job/primekraft_tests/build'>собрать<a>

## <img src="media/logos/Jenkins.svg" title="Jenkins" width="4%"/> Сборка в Jenkins 
<p align="center">
<img title="Jenkins Build" src="media/screenshots/jenkins_build.png">
</p>

## <img src="media/logos/Allure_Report.svg" title="Allure Report" width="4%"/> Пример Allure-отчета
### Overview

<p align="center">
<img title="Allure Overview" src="media/screenshots/allure_overview.png">
</p>

### Тест-кейсы

<p align="center">
<img title="Test Results in Alure" src="media/screenshots/allure_test_suite.png">
</p>

<p align="center">
<img title="Test Results in Alure" src="media/screenshots/allure_behavior.png">
</p>

### Графики

<p align="center">
<img title="Test Results in Alure" src="media/screenshots/allure_graphs.png">
</p>

## <img src="media/logos/AllureTestOps.svg" title="Allure TestOps" width="4%"/> Интеграция с Allure TestOps

Выполнена интеграция сборки <code>Jenkins</code> с <code>Allure TestOps</code>.<br>
Результат выполнения автотестов отображается в <code>Allure TestOps</code><br>
На Dashboard в <code>Allure TestOps</code> отображена статистика пройденных тестов.

### Dashboard
<p align="center">
<img title="Allure TestOps DashBoard" src="media/screenshots/allure_testops_dashboard.png">
</p>

### Тест-кейсы
<p align="center">
<img title="Allure TestOps test-case" src="media/screenshots/allure_testops_test_case.png">
</p>
<p align="center">
<img title="Allure TestOps test-case" src="media/screenshots/allure_testops_testcase.png">
</p>



## <img src="media/logos/Jira.svg" title="Jira" width="4%"/> Интеграция с Jira

Реализована интеграция <code>Allure TestOps</code> с <code>Jira</code>, в тикете отображается информация, какие тест-кейсы были написаны в рамках задачи и результат их прогона.

<p align="center">
<img title="Jira Task" src="media/screenshots/jira_integration.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Telegram" src="media/logos/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки, бот созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.

<p align="center">
<img width="70%" title="Telegram Notifications" src="media/screenshots/Telegram_dOO4Xyu1au.png">
</p>

## Видео примера запуска тестов в Selenoid

К каждому тесту в отчете прилагается видео прогона.
<p align="center">
  <img title="Selenoid Video" src="media/screenshots/selenoid_vid.gif">
</p>
