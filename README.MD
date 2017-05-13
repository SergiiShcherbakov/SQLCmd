
сonnect
Команда для подключения к соответствующей БД
Формат команды: connect | database | username | password
где: database - имя БД
username -  имя пользователя БД
password - пароль пользователя БД
Формат вывода: текстовое сообщение с результатом выполнения операции

6u [Подключение к базе] Я как ЮЗЕР хочу подключиться к существующей базе данных и вносить в нее правки
    6u.1 [успешно подключились]
        я вижу приветствие от программы
        я ввожу команду: connect|имя базы|юзер|пароль
        я вижу сообщение что идет подключение
        я вижу сообщение что все ок (подключение успешно)
        я вижу приглашение от главного меню :
        "Main menu:"
        "Enter your command or type help to get help:"
        PROFIT
    6u.2 [несуществующая база]
        приветствие
        ввожу имя несуществующей базы c какими-то логином и паролем
        вижу сообщение "FATAL: database "SQLCmd1" does not exist"
        повторное поле ввода
        ввожу все правильно
        я вижу приглашение от главного меню :
        "Main menu:"
        "Enter your command or type help to get help:"
        PROFIT
    6u.3[несуществующая база]
        приветствие
        ввожу имя базы c неверным логином и паролем
        вижу сообщение "FATAL: password authentication failed for user "postgres1"
        повторное поле ввода
        ввожу все правильно
        я вижу приглашение от главного меню :
        "Main menu:"
        "Enter your command or type help to get help:"
        PROFIT
    6u.4 [несуществующий пользователь]
        приветствие
        ввожу имя базы c логином и неверным паролем
        вижу сообщение "FATAL: password authentication failed for user "postgres1"
        повторное поле ввода
        ввожу все правильно
        я вижу приглашение от главного меню :
        "Main menu:"
        "Enter your command or type help to get help:"
        PROFIT
    6u.4 [неверное количество аргументов/ отсутствие имени базы или пароля или имени пользователя / больше данных]
        приветствие
        ввожу имя базы c логином
        вижу сообщение "3 parameters are expected but 2 is entered please, try again"
        повторное поле ввода
        ввожу все правильно
        я вижу приглашение от главного меню :
        "Main menu:"
        "Enter your command or type help to get help:"
        PROFIT


exit
Команда для отключения от БД и выход из приложения
Формат: exit (без параметров)
Формат вывода: текстовое сообщение с результатом выполнения операции

7y [Отключение от базы] Я как ЮЗЕР хочу отключиться от базы данных и выйти из приложения что бы окончить работу
    7y.1 [успешный выход]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "exit"
        программа отключается от базы данных
        я вижу сообщение что все ок
        "Good by. See you soon."
        Программа завершает свою работу
        PROFIT
    7y.2 [неверная комманда exit]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "exitt"
        я вижу сообщение об ошибке и повторном вооде команды
        "exitt does not supported."
        "Enter your command or type help to get help:"
        PROFIT

tables
Команда выводит список всех таблиц
Формат: tables (без параметров)
Формат вывода:
в любом удобном формате
например [table1, table2, table3]

3j [Команда выводит список всех таблиц] Я как ЮЗЕР хочу увидеть все таблицы в базе данных
что бы выбрать конкретную нужную таблицу
    3j.1 [Успешный вывод всех таблиц]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "tables"
        я вижу вывод всех существующих таблиц в базе в формате [таб1, таб2, ... табn]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        PROFIT
    3j.2 [неверная комманда tables]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "tabless"
        я вижу сообщение об ошибке и повторном вооде команды
        "exitt does not supported."
        "Enter your command or type help to get help:"
        PROFIT
    3j.3 [просмотр всех таблиц при пустой базе данных]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "tabless"
        я вижу вывод в формате []
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        PROFIT

drop
Команда удаляет заданную таблицу
Формат: drop | tableName
где tableName - имя удаляемой таблицы
Формат вывода: текстовое сообщение с результатом выполнения операции

8t [Команда удаляет заданную таблицу] Я как ЮЗЕР хочу удалить таблицу с заданным именем что бы её небыло в базе
    8t.1 [Успешный удаление таблицы]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "drop|tableName"
        я вижу сообщение tableName was removed
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        PROFIT
    8t.2 [неверная комманда drop]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "dropp|tableName"
        я вижу сообщение об ошибке и повторном вооде команды
        "drop" parameter are expected but "dropp" is entered
        please, try again
        Enter your command or type help to get help:
        PROFIT
    8t.3 [отсутствует параметр имя таблицы]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "drop|"
        я вижу сообщение об ошибке и повторном вооде команды
        2 parameters are expected but 1 is entered
        please, try again
        Enter your command or type help to get help:
        PROFIT
    8t.4 [отсутствует таблица указанная в базе]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "drop|неверное имя таблицы"
        я вижу сообщение об ошибке и повторном вооде команды
        ERROR: table "неверное имя таблицы" does not exist
        please, try again
        Enter your command or type help to get help:
        PROFIT
find
Команда для получения содержимого указанной таблицы
Формат: find | tableName
где tableName - имя таблицы
Формат вывода: табличка в консольном формате
+--------+---------+------------------+
+  col1  +  col2   +       col3       +
+--------+---------+------------------+
+  123   +  stiven +     pupkin       +
+  345   +  eva    +     pupkina      +
+--------+---------+------------------+

9i [Команда для получения содержимого указанной таблицы] Я как ЮЗЕР просмотреть указанную таблицу что
бы уидеть запись в ней.
    9i.1 [Успешный вывод таблицы]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "find|tableName"
        я вывод таблицы в формате:
         +--------+---------+------------------+
         +  col1  +  col2   +       col3       +
         +--------+---------+------------------+
         +  123   +  stiven +     pupkin       +
         +  345   +  eva    +     pupkina      +
         +--------+---------+------------------+
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        PROFIT
    9i.2 [неверная комманда find]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "findd|tableName"
        я вижу сообщение об ошибке и повторном вооде команды
        "find" parameter are expected but "findd" is entered
        please, try again
        Enter your command or type help to get help:
        PROFIT
    9i.3 [отсутствует параметр имя таблицы]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "find|"
        я вижу сообщение об ошибке и повторном вооде команды
        2 parameters are expected but 1 is entered
        please, try again
        Enter your command or type help to get help:
        PROFIT
    9i.4 [отсутствует таблица указанная в базе]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "find|неверное имя таблицы"
        я вижу сообщение об ошибке и повторном вооде команды
        ERROR: table "неверное имя таблицы" does not exist
        please, try again
        Enter your command or type help to get help:
        PROFIT

clear
Команда очищает содержимое указанной (всей) таблицы
Формат: clear | tableName
где tableName - имя очищаемой таблицы
Формат вывода: текстовое сообщение с результатом выполнения операции
12w [Команда очищает содержимое указанной (всей) таблицы] Я как ЮЗЕР хочу очистить всю таблицу что
бы она была пустой.
    12w.1 [Успешный вывод таблицы]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "clear|tableName"
        я вывод вижу подтверждение об успешном действии
        "tableName was cleared"
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        PROFIT
    12w.2 [неверная комманда clear]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "clearr|tableName"
        я вижу сообщение об ошибке и повторном вооде команды
        "find" parameter are expected but "clearr" is entered
        please, try again
        Enter your command or type help to get help:
        PROFIT
    12w.3 [отсутствует параметр имя таблицы]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "clear|"
        я вижу сообщение об ошибке и повторном вооде команды
        2 parameters are expected but 1 is entered
        please, try again
        Enter your command or type help to get help:
        PROFIT
    12w.4 [отсутствует таблица указанная в базе]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "clear|неверное имя таблицы"
        я вижу сообщение об ошибке и повторном вооде команды
        ERROR: table "неверное имя таблицы" does not exist
        please, try again
        Enter your command or type help to get help:
        PROFIT

insert
Команда для вставки одной строки в заданную таблицу
Формат: insert | tableName | column1 | value1 | column2 | value2 | ... | columnN | valueN
где: tableName - имя таблицы
column1 - имя первого столбца записи
value1 - значение первого столбца записи
column2 - имя второго столбца записи
value2 - значение второго столбца записи
columnN - имя n-го столбца записи
valueN - значение n-го столбца записи
Формат вывода: текстовое сообщение с результатом выполнения операции
17t [Команда для вставки одной строки в заданную таблицу] Я как ЮЗЕР хочу вставить новую строку что
бы она в таблице.
    17t.1 [Успешная вставка записи в таблицу]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "insert|tableName|column1|value1|column2|value2|...|columnN|valueN"
        я вывод вижу подтверждение об успешном действии
        "table row was inset"
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        PROFIT
    17t.2 [колонок меньше чем записей]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду где колонок меньше чем есть в таблице:
        "insert|tableName|column1|value1|column2|value2|...|columnN|valueN"
        я вжу сообщение об успешном действии и что в недостающие колонки были вставлены значения по умолчанию
        "table row was inset
        in column4 insert default value(0)"
        in column5 insert default value("")"
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        PROFIT
    17t.3 [неверная комманда insert]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу неверную команду:  "insertt|tableName|column1|value1|column2|value2|...|columnN|valueN"
        я вижу сообщение об ошибке и повторном вооде команды
        "insert" parameter are expected but "insertt" is entered
        please, try again
        Enter your command or type help to get help:
        PROFIT
    17t.4 [отсутствует параметр имя таблицы]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "insert|column1|value1|column2|value2|...|columnN|valueN"
        я вижу сообщение об ошибке и повторном вооде команды
        "insert wrong number of parameters. An even number of parameters is expected and an odd"
        please, try again
        Enter your command or type help to get help:
        PROFIT
    17t.5 [введено неверное имя таблицы]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "insert|неверное имя таблицы|column1|value1|column2|value2|...|columnN|valueN"
        я вижу сообщение об ошибке и повторном вооде команды
        ERROR: table "неверное имя таблицы" does not exist
        please, try again
        Enter your command or type help to get help:
        PROFIT
    17t.6 [введено неверное колличество параметров]
        я вижу приглашение от главного меню
        "Enter your command or type help to get help:"
        я ввожу команду: "insert|tableName|value1|column2|value2|...|columnN|valueN"
        я вижу сообщение об ошибке и повторном вооде команды
        "insert wrong number of parameters. An even number of parameters is expected and an odd"
        please, try again
        Enter your command or type help to get help:
        PROFIT

create
Команда создает новую таблицу с заданными полями
Формат: create | tableName | column1 | column2 | ... | columnN
где: tableName - имя таблицы
column1 - имя первого столбца записи
column2 - имя второго столбца записи
columnN - имя n-го столбца записи
Формат вывода: текстовое сообщение с результатом выполнения операции



update
Команда обновит запись, установив значение column2 = value2, для которой соблюдается условие column1 = value1
Формат: update | tableName | column1 | value1 | column2 | value2
где: tableName - имя таблицы
column1 - имя столбца записи которое проверяется
value1 - значение которому должен соответствовать столбец column1 для обновляемой записи
column2 - имя обновляемого столбца записи
value2 - значение обновляемого столбца записи
columnN - имя n-го обновляемого столбца записи
valueN - значение n-го обновляемого столбца записи
Формат вывода: табличный, как при find со старыми значениями обновленных записей.

delete
Команда удаляет одну или несколько записей для которых соблюдается условие column = value
Формат: delete | tableName | column | value
где: tableName - имя таблицы
Column - имя столбца записи которое проверяется
value - значение которому должен соответствовать столбец column1 для удаляемой записи
Формат вывода: табличный, как при find со старыми значениями удаляемых записей.

help
Команда выводит в консоль список всех доступных команд
Формат: help (без параметров)
Формат вывода: текст, описания команд с любым форматированием

