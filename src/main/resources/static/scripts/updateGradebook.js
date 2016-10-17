$(function() {
    $.get("/classTable", function(data) {
        $("#ClassOutput").empty();
        $("#ClassOutput").append(data);
    })
    $.get("/assignTable", function(data) {
        $("#ClassOutput").empty();
        $("#ClassOutput").append(data);
    })
    $.get("/studentTable", function(data) {
        $("#ClassOutput").empty();
        $("#ClassOutput").append(data);
    })
})