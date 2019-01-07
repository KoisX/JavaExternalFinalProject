function validate(formId) {
    $(document).on("submit", "#"+formId, function(event) {
        let $form = $(this);

        $.post($form.attr("action"), $form.serialize(), function(responseJson) {
            if(responseJson.error){
                $('#error-msg').text(responseJson.error);
            }else{
                window.location.href = responseJson.url;
            }
        }, 'json');

        event.preventDefault();
    });
}