$(function() {
    $.get("/classTable", function(data) {
        $("#ClassOutput").empty();
        $("#ClassOutput").append(data);
    })
})