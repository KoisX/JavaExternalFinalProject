let score;
let maxScore;

$(document).on("submit", "#exam-form", function(event) {
    let $form = $(this);

    $.post($form.attr("action"), $form.serialize(), function(responseJson) {
        score = responseJson.score;
        maxScore = responseJson.maxScore;

        setResultMessage(responseJson)
        setSuccessfulTasks();
        setUnsuccessfulTasks(responseJson);
        fixFormAppearance(responseJson);
    }, 'json');

    event.preventDefault();
});


/**
 * Sets the message with the user result for the current exam
 * @param responseJson responseJson JSON with the response from server
 */
function setResultMessage(responseJson) {
    document.getElementById("header").innerText = responseJson.message +responseJson.score+"/"+responseJson.maxScore;

}

/**
 * By default, sets all tasks to success state.
 * Later on those tasks, in which user makes mistakes,
 * will change their status
 */
function setSuccessfulTasks() {
    $('.task-item').addClass('successfulTest');
}

/**
 * Tasks with mistakes change their appearance here
 * @param responseJson JSON with the response from server
 */
function setUnsuccessfulTasks(responseJson) {
    $.each(responseJson.mistakes, function(key, value) {
        $('#task_'+value).addClass('unsuccessfulTest').removeClass('successfulTest');
    });
}

/**
 * Moves page to top and removes submit button from form
 */
function fixFormAppearance(responseJson) {
    $('html, body').animate({ scrollTop: 0 }, 'fast');
    $('#checkBtn').remove();
    $('#progress-bar').width(getPercentageOfSolvedTasks()+'%');
    $('#progress').show();
    $('#email-msg').show();
    $('form#exam-form :input').attr("disabled", true);
}

function getPercentageOfSolvedTasks() {
    if(maxScore===0)
        return 0;
    return score/maxScore*100;
}