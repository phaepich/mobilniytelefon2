## Модуль ActivityLifecycle
Создал Lesson2 и в нем модуль ActivityLifecycle. Переопределил основные методы. После этого добавил EditText на разметку.
![image](https://github.com/user-attachments/assets/c6058bfc-f426-46c8-b4c6-0f7ef48aec35)

В логах видно, как вызываются все эти методы в зависимости от действий с активновстью. После возврата в приложение метод onCreate не вызывается и значение editText не меняется. А вот если выйти из приложения через кнопку "назад", то editText обнулится.

## Модуль MultiActivity
Далее был реализован модуль MultiActivity. В первой активности сделал editText и кнопку "отправить". После нажатия на эту кнопку введенный текст переносится на textView второй активности.
![image](https://github.com/user-attachments/assets/65ac6b1e-4649-427d-a3de-c4d59b59f4f3)
![image](https://github.com/user-attachments/assets/99b706c3-163e-46c9-862b-b34842c228a0)

После нажатия на кнопку "отправить" вызываются методы onPause(), onStart() onResume(). Если нажать на кнопку "назад", то для второй активности в конце вызовется onDestroy()
![image](https://github.com/user-attachments/assets/940296a3-a139-459c-a6ad-4685e33be0b4)

## Модуль IntentFilter
В модуле IntentFilter были созданы две кнопки: для открытия сайта МИРЭА и для расшаривания своего ФИО. Для этого были использован метод Uri.parse() и класс Intent. 
![image](https://github.com/user-attachments/assets/97c8bd64-1dbb-4310-91a9-6051d4368aa8)
![image](https://github.com/user-attachments/assets/cb28479b-c716-438c-95c2-5e3527fcd07a)
![image](https://github.com/user-attachments/assets/36b547b2-1185-48e0-a23f-f2417a047ee9)

## Модуль ToastApp
Первый модуль с уведомлениями - ToastApp. В методе onShowToastClick прописал toast-уведомление с моим номером и группой, а также кол-вом символов.
![image](https://github.com/user-attachments/assets/8ee863b5-50c6-43a9-9632-f1789f63c416)

## Модуль NotificationApp
В манифест было вписано разрешение на уведомления. При запуске приложение запрашивает разрешение на уведомления. Проверка на разрешение идет в методе CheclSelfPermission(). Если доступ не дан, то идет запрос разрешения.
![image](https://github.com/user-attachments/assets/c30f6327-646c-4d47-aed6-26c2d9394406)

При нажатии на кнопку "отправить уведомление" уведомление отправляется. Если текста мало, то уведомление небольшое.
![image](https://github.com/user-attachments/assets/73b357a1-01a8-4413-a091-e9338a055f50)

Если текста сильно много, то уведомление раскрывается и там он уже отображается в больших колчиествах. Реализовано через BigTextStyle()
![image](https://github.com/user-attachments/assets/447baacf-7ac6-4992-a788-e6d3f80e2b6f)

Канал для уведомлений называется "основной канал" теперь, а не "ФИО-уведомление", как на прошлых скринах.
![image](https://github.com/user-attachments/assets/9f7f860e-7deb-415a-93c9-fe55941457e3)
![image](https://github.com/user-attachments/assets/394db9f9-73a3-4fe1-b241-b6a7b4f3db42)

## Модуль Dialog
Dialog. По нажатию на кнопку вызывается диалоговое окно с тремя опциями. После нажатия на любую из них высвечивается toast-уведомление с выбранной опцией. Все настройки диалогового окна идут в объекте класса AlertDialog.Builder.
![image](https://github.com/user-attachments/assets/37328f74-a0b2-4dd7-9b64-385eeb22da22)
![image](https://github.com/user-attachments/assets/87aefc64-4961-4f53-add3-354c5610bf84)

В конце еще самостоятельное задание. Добавил 3 кнопки: "время", "дата", "прогресс".
![image](https://github.com/user-attachments/assets/27c077b1-2f78-4c8a-8598-3cad09b3c1ac)

Время реализовано через класс Calendar и метод TimePickerDialog(). После выбора времени высвечивается toast с выбранным вреиенем.
![image](https://github.com/user-attachments/assets/12c65dd0-e86b-4512-83c1-82d1a5a0f76b)

Дата сделана таким же образом, только теперь используется метод DatePickerDialog().
![image](https://github.com/user-attachments/assets/f5ce512d-9992-43b2-b0b2-0c49fa9f8a62)

Для загрузки использован класс ProgressBar с бесконечной анимацией прокрутки. Чтобы выйти из загрузки достаточно нажать на любое место экрана, ведь .setCancelable установлен на true.
![image](https://github.com/user-attachments/assets/17646e55-38ca-4bdf-9af9-c7c229faedb3)

Работа выполнена. Итого в проекте 6 модулей. не считая дефолтного app.
![image](https://github.com/user-attachments/assets/6347bf93-ceb8-4438-8e8b-1920e4532e4b)

















