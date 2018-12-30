
$(document).on("submit", "#exam-form", function(event) {
    let $form = $(this);

    $.post($form.attr("action"), $form.serialize(), function(responseJson) {
        alert(responseJson.mistakes);
    }, 'json');

    event.preventDefault();
});