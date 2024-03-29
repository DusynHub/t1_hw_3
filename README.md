## Домашняя работа № 3 в рамках открытой школы java от Т1

задание представлено в файле **Homework_task_3.md**

**ВАЖНЫЕ ЗАМЕЧАНИЕ**

+ В учебных целях при каждом запуске приложения таблицы в базе данные пересоздаются.
+ Postman тесты в текущей реализации требуют перезапуска приложения для очистки таблиц, так как проверки привязаны к `id` сущностей


<details>
<summary>Краткое описание</summary>

Простой `crud` сервис, логирование вызовов контроллеров и методов сервисов осуществлено с помощью АОП
</details>

<details>
<summary>Как запустить проект?</summary>

**Требования:** Наличие установленного `docker compose`

+ Скачать проект из репозитория
+ Перейти в папку с репозиторием в консоле например `cd t1_hw_3`
+ Находясь с проектом набрать команду `docker compose up`
    - При первом запуске потребуется ожидание `~90-120 сек`  загрузки зависимостей для запуска `maven`
+ Docker скачает необходимые зависимости и запустит контейнеры с проектом
+ **Важно!** В учебных целях при каждом запуске приложения таблицы в базе данныхе пересоздаются.

</details>

<details>
<summary>Как запустить тесты из папки postman-tests ?</summary>

**Требования:** Наличие установленного `Postman` или `newman`
+ Импортировать коллекцию тестов в `Postman` в виде файлов
    - `t1_hw3.postman_collection.json`
+ Запустить проект
+ Запустить коллекцию `t1_hw3.postman_collection.json` в `Postman`
+ логи будут выведены в консоль термининала в котором была применена команда `docker compose up`
+ логи буду записаны в файл в папке `logs` в файл `aop-homework.log`. Папка  `logs` будет создана в корневой папке `t1_hw_3` 
</details>

<details>
<summary>Как получить доступ к swagger документации ?</summary>

Документация доступна после запуска проекта
Необходимые описание эндпоинтов представлено в документации в [swagger](http://localhost:8085/swagger-ui/index.html)
</details>
