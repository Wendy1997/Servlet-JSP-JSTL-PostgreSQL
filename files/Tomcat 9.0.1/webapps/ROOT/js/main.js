$(document).ready(function(){
    $("#ajax").click(function() {

        $.ajax({
            type: 'GET',
            url: 'api',
            dataType: 'json',
            success: function (response) {
                $("#title")[0].innerHTML = response.value;
            }
        });


    });
});