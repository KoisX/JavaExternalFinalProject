$(document).on("submit", "#make-public", function(event) {
    let $form = $(this);

    $.post($form.attr("action"), $form.serialize(), function(responseJson) {
        if(responseJson.error){
            $('#status-warn').css('color', 'red');
        }else{
            window.location.href = responseJson.url;
        }
    }, 'json');

    event.preventDefault();
});