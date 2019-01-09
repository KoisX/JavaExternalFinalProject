<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:page title="Rules">
    <h1><b>Правила участі в проекті</b></h1>
    <div class="about-text"><img src="${pageContext.request.contextPath}/images/happy_student_3.jpg" width="40%" class="about-photo"/>
        <p>
            <strong>Правила участі прості, але їх варто прочитати дуже уважно.</strong>
            <br/><br/>
            Робота з проектом починається з реєстрації. Потім вже можна почати проходження тестів, відсортованих по темам.<br/>
            Незареєстрований учасним має доступ лише до сторінки реєстрації, авторизації та сторінок із загальною інформацією.<br/><br/>
            Після успішної авторизації учасник вибирає тест з однієї з представлених на сайті тем та проходить його. Після цього перед користувачем відкривається сторінка з результатами, які, до речі, також автоматично надсилаються на електронну пошту учасника.
        </p>
        <ul class="rules">
            <li class="rule-item">Перше і найголовніше правило - спочатку думати, а потім давати відповідь ще кілька раз подумати і лише після цього давати відповідь. Принцип простий - за правильні відповіді учасник отримує бали (від 0 до 5, - залежно від складності задачі), а за неправильні - бали втрачаються (в межах тільки поточної задачі, тобто, неможливо на одній задачі втратити більше балів, ніж та кількість балів, що є вагою задачі).</li>
            <li class="rule-item">Реєструватись можна тільки один раз і лише із власним ПІБ (українською мовою), вказуючи лише достовірні дані (дата народження, e-mail, тощо). Повторна повна реєстрація не припускається (для одержання доступу до подальших розділів/дисцилін достатньо вказувати свій логі і пароль), для відновлення втраченого логіну/паролю використовуйте цю сторінку.</li>
            <li class="rule-item">Можна використовувати лише свої розумові здібності. Категорично забороняється оприлюднювати умови задач та консультуватись у інших людей. Звісно, можна використовувати книги чи інтернет для пошуку необхідної допоміжної інформації (формули, визначення, теореми); але заборонено шукати умови задач та відповіді на них.</li>
            <li class="rule-item">Розв'язувати задачі (отримувати відповідь) треба виключно аналітично (без використання комп'ютера), а комп'ютер можна використовувати лише для перевірки правильності (перебір чи моделювання) отриманого результату</li>
            <li class="rule-item">Категорично заборонений cracking (злам) проекту, а от hacking (на рівні відшукання недоліків з повідомленням поштою) вітається.</li>
            <li class="rule-item">Відповідь не може бути довшою за 200 символів. Якщо відповідь є словом/словосполученням, то найчастіше регістр неважливий. Інакше, на на питанні регістру буде наголошено.</li>
        </ul>
    </div>
    <hr />
</t:page>
