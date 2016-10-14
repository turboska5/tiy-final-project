/**
 * Created by Andrew and Jimmy on 10/14/16.
 */

//on load
$(function() {
    //TODO: test on navigation to another link or close tab
    //TODO: only works on first item in list
    var grade;
    //get value on click in
    $(".GradeInput").on("focus", function(){
        grade = $(".GradeInput").val();
    });
    //blur save feature
    $(".GradeInput").on("blur", function(){
        if($(".GradeInput").val() != grade) {
            var gradeID = $(".gradeID").val();
            var earnedPoints = $(".GradeInput").val();
            alert("Grade ID: " + gradeID + " with new points: " + earnedPoints);
            $.post("/teacherGradePost", {gradeID: gradeID, earnedPoints: earnedPoints});
        }
    });
});