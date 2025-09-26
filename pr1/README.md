Практика №1
Установил последнюю версию Android Studio с официального сайта и создал проект Lesson1. Подключился к своему пикселю, чтобы на нем тестить приложения.
В activity_main.xml добавил новый элемент TextView с id “textViewHello” и отцентрировал его по родителю, немного отодвинув выше станадртной надписи “Hello World!”.
 ![image](https://github.com/user-attachments/assets/8cbe2cc5-2ea6-4d54-8956-05f004a71fb5)

Рисунок 1. Новый элемент TextView
Был реализован вид экрана приложений LinearLayout с двумя рядами кнопок.
 ![image](https://github.com/user-attachments/assets/274fd74f-f9cd-413a-a8d9-6a499944c1ef)

Рисунок 2. LinearLayout
Далее идет TableLayout с кучей кнопок и тремя TableRow-рядами.
 ![image](https://github.com/user-attachments/assets/72f85a08-eac3-4c36-a1dd-2ab30afecc9b)

Рисунок 3. TableLayout
В ConstraintLayout были созданы два TextView и одна кнопка. Все элементы привязаны к левому и правому краям. По вертикали элементы привязаны друг к другу.
 ![image](https://github.com/user-attachments/assets/bade1f12-722f-4461-9442-fd9800409cf5)

Рисунок 4. ConstraintLayout
Тоже создал экран с информацией о контакте. Три кнопки, текст, изменяемый текст и кнопка-картинка.
 ![image](https://github.com/user-attachments/assets/d32ea9ed-8935-4589-be0e-be6a8720b2ef)

Рисунок 5. Экран информации о контакте
Далее научился делать вертикальную и горизонтальную разметки. В вертикальной все было стандартно, а в горизонтальной нужно было располагать все элементы в TableRow, ведь используется TableLayout.
 ![image](https://github.com/user-attachments/assets/ad694650-73b6-4789-b357-e6aa0b82618f)

Рисунок 6. Вертикальная развертка
 ![image](https://github.com/user-attachments/assets/b70fae78-98ea-467b-8d92-6f185831a29b)

Рисунок 7. Горизонтальная развертка
Вернулся к activity_main.xml. Нужно было изменить текст у TextView по нажатию кнопки. Назначу это на кнопку «Позвонить», у нее как раз id «button».
Для реализации этого был добавлен обработчик события setOnClickListener() на кнопку. Обращения к элементам происходят через findViewById(). После нажатия на кнопку «Позвонить» номер меняется на указанный текст.
 ![image](https://github.com/user-attachments/assets/1293689f-bbd3-4101-a394-2eb93073ceb8)

Рисунок 8. Текст на экране до нажатия на «Позвонить»
 ![image](https://github.com/user-attachments/assets/3ef3073f-9859-47e8-af54-bf2d1b2dbec1)

Рисунок 9. Текст на экране после нажатия на «Позвонить»
Осталась последняя глава. Здесь в рамках интерфейса был расположен TextView, две кнопки и чекбокс. Для btnWhoAmI реализован обработчик событий. При нажатии выводится номер по списку и активируется чекбокс. Для btnItIsNotMe реализован обработчик через XML. Чекбокс деактивируется и показывается Toast с сообщением.
 ![image](https://github.com/user-attachments/assets/f7f7bb32-4411-4b0d-9569-0393b4dcd125)

Рисунок 10. После нажатия на WhoAmI
 ![image](https://github.com/user-attachments/assets/8fa99798-5a14-4256-98ec-316938ec727c)

Рисунок 11. После нажатия на ItIsNotMe
