## Клиент-серверное взаимодействие. Blocking и Non-Blocking IO.
## Задача 2. Долой пробелы
## Описание
#### Необходимо написать решение используя использования `Non-Blocking IO`, включающее в себя клиент и сервер. 
1. Сервер умеет удалять из любых строк все пробельные символы. 
2. Клиент должен "продемонстрировать" функциональность сервера, используя его
## Работа программы
- Клиент бесконечно просит пользователя вводить строки с пробелами
- Каждая строка передается на сервер
- Сервер читает все, что ему передали, удаляет пробельные символы и результат отправляет обратно
- Клиент отображает результат
- Если пользователь вводит end - клиент завершается
