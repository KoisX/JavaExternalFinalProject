/*Checking if at least one checkbox is checked*/
$(document).ready(function () {
    $('#checkBtn').click(function() {
        let checkboxes = $("input[type=checkbox]").length;
        let checked = $("input[type=checkbox]:checked").length;

        if(!checked && checkboxes>0) {
            alert("You must check at least one checkbox in each checkbox group!");
            return false;
        }

    });
});