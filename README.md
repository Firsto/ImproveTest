Improve* Test Project
===============================
Java Enterprise:
Maven / Servlet / JSP API / JPA (Hibernate)

## Описание

Съешьте ещё этих мягких французских булок, да выпейте же чаю!

## Установка и настройка

#### База данных:
- По умолчанию используется MySQL база данных с названием _`improvetest`_ на локалхосте с логином и паролем _`root`_
- Указать другие настройки подключения можно в <a href="/src/main/resources/hibernate.cfg.xml">/src/main/resources/hibernate.cfg.xml</a>
- Заполнить тестовыми данными можно из <a href="/improvetest.sql">/improvetest.sql</a>

#### Запуск приложения:

- Задеплойте <a href="/improvetest.war">/improvetest.war</a> в TomCat или загрузите проект к себе и соберите сами с _`mvn package`_