
$(document).on("submit", "#exam-form", function(event) {
    let $form = $(this);

    $.post($form.attr("action"), $form.serialize(), function(responseJson) {
        document.getElementById("header").innerText = "Good work! Result: "+responseJson.score+"/"+responseJson.maxScore;
        $('html, body').animate({ scrollTop: 0 }, 'fast');
    }, 'json');

    event.preventDefault();
});