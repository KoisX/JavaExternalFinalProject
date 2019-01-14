# JavaExternalFinalProject
Final project for the Java External course

Система Быстрого Тестирования Студентов. Студент регистрируется э-мейлом, к нему привязуеться его Успешность и на него будут приходить сообщения о результате тестов. В системе существует каталог Тестов по темам, авторизованный Студент может проходить тесты. В конце теста должна на странице отобразится форма где показано ошибки студента. Все данные об успеваемости и пройденных курсах отображаются на странице Администратора как сводная таблица по всем Студентам.

#To install the project:
Clone it from GitHub git clone https://github.com/KoisX/JavaExternalFinalProject.git

#To run the project
1)Install database from /etc/dbData/*.sql
2)Go to Tomcat root folder /bin and run startup.bin to run Tomcat
3)Build and deploy the project with maven mvn tomcat7:deploy
4)Go to url localhost:8080/WebExam
