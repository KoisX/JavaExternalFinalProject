/*Checking if at least one checkbox is checked*/
$(document).ready(function () {
    $('#checkBtn').click(function() {
        let checked = $("input[type=checkbox]:checked").length;

        if(!checked) {
            alert("You must check at least one checkbox in each checkbox group!");
            return false;
        }

    });
});