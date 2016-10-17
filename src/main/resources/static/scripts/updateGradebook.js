$(function() {
    $.get("/classTable", function(data) {
        $("#classOutput").empty();
        $("#classOutput").append(data);
    })
    $.get("/assignTable", function(data) {
        $("#assignmentOutput").empty();
        $("#assignmentOutput").append(data);
    })
    $.get("/studentTable", function(data) {
        $("#studentOutput").empty();
        $("#studentOutput").append(data);
    })
})