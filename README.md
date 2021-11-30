JavaFx приложение, написанное для CRUD операций с договорами. Запустить данное приложение можно используя систему сборки
проекта Gradle. Gradle build -> build дальше Gradle application -> run. <br />
Приложение подключено к базе данных PostgreSQL. Данные для подключения находятся в методе getConnection() класса Controller.
При желании можно подключиться к своей бд, создав таблицу по скрипту, который находится в файле script.sql в папке main.resources.
После запуска открывается следующее окно (изображение 1). <br /> 
В случае желания добавить запись о договоре - нужно заполнить 4 строки на главной панели (ID (int), Number (int),
Date of conclusion (date - format "yyyy-mm-dd"), Date of last update (date - format "yyyy-mm-dd")) и нажать кнопку "Insert". 
Данные сразу отобразятся в таблице справа. <br /> 
При нажатии на запись в таблице - данные объекта перенесутся на строки в главной панели. При изменении любой строки (строчек)
нажмите кнопку "Update" на главной панели. Таким образом можно обновлять данные в таблице. <br />
Для удаления объекта из таблицы в первую строку главного поля (ID) введите идентификатор объекта, который вы хотите удалить
и нажмите кнопу "Delete" на главной панели. <br /> 
Ниже основных кнопок располагается кнопка "Check relevance" кликнув на которую можно обновить данные в графе "Check box".
Если последнее изменение в данных договора осуществлялось не больше чем 60 дней назад - то в графе отображается "true", 
если больше 60 дней назад - то "false".