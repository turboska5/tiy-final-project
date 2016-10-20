/**
 * Created by Andrew and Jimmy on 10/14/16.
 */

$(function() {
    $(".GradeInput").on("change", function(){
        var gradeID = $(this).attr("data-gradeID");
        var earnedPoints = $(this).val();
        $.post("/teacherGradePost", {gradeID: gradeID, earnedPoints: earnedPoints});
        setTimeout(refreshStuff, 25);
    });
});

function refreshStuff() {
    classSearchPress(currentPage);
    assignSearchPress(currentAPage);
}