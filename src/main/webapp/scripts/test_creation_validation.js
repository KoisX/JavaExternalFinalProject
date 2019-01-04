$(document).on("submit", "#test-create-form", function(event) {
    let $form = $(this);

    $.post($form.attr("action"), $form.serialize(), function(responseJson) {

    }, 'json');

    event.preventDefault();
});