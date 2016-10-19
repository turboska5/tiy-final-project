/**
 * Created by Jimmy And Andrew on 10/17/16.
 */

//global pagination support
var classesPerPage = 5, assignPerPage = 5, studentsPerPage = 5;
var currentPage = 0, lastPage = 0;
var currentAPage = 0, lastAPage = 0;
var currentSPage = 0, lastSPage = 0;

$(function() {
    //disable all buttons, run null searches
    onLoad();

    //button functions
    //search button
    $("#classSearch").click(function(){
        currentPage = 0;
        var query = "/classTable?page=" + currentPage + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
        classSearch(query);
        classButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignSearch").click(function(){
        currentAPage = 0;
        var query = "/assignTable?page=" + currentAPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val() + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
        assignSearch(query);
        assignButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentSearch").click(function(){
        currentSPage = 0;
        var query = "/studentTable?page=" + currentSPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
        studentSearch(query);
        studentButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //reset button
    $("#classReset").click(function(){
        $("#period").val("");
        $("#name").val("");
        $("#identifier").val("");
        classSearch("/classTable");
        currentPage = 0;
        classButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignReset").click(function(){
        $("#aPeriod").val("");
        $("#aName").val("");
        $("#aID").val("");
        $("#aDate").val("");
        $("#aPoints").val("");
        assignSearch("/assignTable");
        currentAPage = 0;
        assignButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentReset").click(function(){
        $("#sPeriod").val("");
        $("#sLastName").val("");
        $("#sFirstName").val("");
        $("#sAName").val("");
        $("#sAID").val("");
        studentSearch("/studentTable");
        currentSPage = 0;
        studentButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //next button
    $("#next").click(function(){
        if (currentPage < lastPage) {
            currentPage++;
            var query = "/classTable?page=" + currentPage + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
            classSearch(query);
            classButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignNext").click(function(){
        if (currentAPage < lastAPage) {
            currentAPage++;
            var query = "/assignTable?page=" + currentAPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val() + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
            assignButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentNext").click(function(){
        if (currentSPage < lastSPage) {
            currentSPage++;
            var query = "/studentTable?page=" + currentSPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
            studentSearch(query);
            studentButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //back button
    $("#back").click(function(){
        if (currentPage > 0) {
            currentPage--;
            var query = "/classTable?page=" + currentPage + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
            classSearch(query);
            classButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignBack").click(function(){
        if (currentAPage > 0) {
            currentAPage--;
            var query = "/assignTable?page=" + currentAPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val() + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
            assignButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentBack").click(function(){
        if (currentSPage > 0) {
            currentSPage--;
            var query = "/studentTable?page=" + currentSPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
            studentSearch(query);
            studentButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
});

//supporting functions
//preload tables and enable appropriate buttons
function onLoad() {
    classSearch("/classTable?period=" + $("#period").val());
    assignSearch("/assignTable?aPeriod=" + $("#period").val());
    studentSearch("/studentTable?sPeriod=" + $("#period").val());
};
function classSearch(classQuery) {
    //retrieve results
    $.get(classQuery, function(data) {
        //clear existing data and refresh with new
        $("#classOutput").empty();
        $("#classOutput").append(data);
        //populate pagination based on results
        var totalCount = $("#classListSize").val();
        var totalPages = Math.ceil(totalCount/classesPerPage);
        var onPageCount = $(data).find('.displayedClass').length;
        lastPage = totalPages - 1;
        var start = currentPage * classesPerPage  + 1;
        var end = start + onPageCount - 1;
        $("#description").text(start + " - " + end + " of " + totalCount);
    });
}
function assignSearch(assignQuery) {
    $.get(assignQuery, function(data) {
        //clear existing data and refresh with new
        $("#assignmentOutput").empty();
        $("#assignmentOutput").append(data);
        //populate pagination based on results
        var totalCount = $("#assignListSize").val();
        var totalPages = Math.ceil(totalCount/assignPerPage);
        var onPageCount = $(data).find('.displayedAssignment').length;
        lastAPage = totalPages - 1;
        var start = currentAPage * assignPerPage + 1;
        var end = start + onPageCount - 1;
        $("#descriptionAssign").text(start + " - " + end + " of " + totalCount);
    });
}
function studentSearch(studentQuery) {
    $.get(studentQuery, function(data) {
        //clear existing data and refresh with new
        $("#studentOutput").empty();
        $("#studentOutput").append(data);
        //populate pagination based on results
        var totalCount = $("#studentListSize").val();
        var totalPages = Math.ceil(totalCount/studentsPerPage);
        var onPageCount = $(data).find('.displayedStudent').length;
        lastSPage = totalPages - 1;
        var start = currentSPage * studentsPerPage + 1;
        var end = start + onPageCount - 1;
        $("#descriptionStudent").text(start + " - " + end + " of " + totalCount);
        //execute button logic here
        studentButtonToggleBothOff();
        if (currentSPage > 0 && currentSPage < lastSPage) {
            $("#studentBack").prop("disabled", false);
            $("#studentNext").prop("disabled", false);
        }
        if (currentSPage == lastSPage) {
            $("#studentBack").prop("disabled", false);
            $("#studentNext").prop("disabled", true);
        }
        if(currentSPage == 0) {
            if(lastSPage == 0) {
                $("#studentBack").prop("disabled", true);
                $("#studentNext").prop("disabled", true);
            } else {
                $("#studentBack").prop("disabled", true);
                $("#studentNext").prop("disabled", false);
            }
        }
    });
}
function classButtonToggleBothOff() {
    $("#back").prop("disabled", true);
    $("#next").prop("disabled", true);
}
function assignButtonToggleBothOff() {
    $("#assignBack").prop("disabled", true);
    $("#assignNext").prop("disabled", true);
}
function studentButtonToggleBothOff() {
    $("#studentBack").prop("disabled", true);
    $("#studentNext").prop("disabled", true);
}



function classButtonToggle() {
    //disable back button/next button behavior
    if(currentPage == 0) {
        $("#back").prop("disabled", true);
        $("#next").prop("disabled", false);
    }
    if (currentPage > 0 && currentPage < lastPage) {
        $("#back").prop("disabled", false);
        $("#next").prop("disabled", false);
    }
    if (currentPage == lastPage) {
        $("#back").prop("disabled", false);
        $("#next").prop("disabled", true);
    }
    if (lastPage == 0) {
        $("#back").prop("disabled", true);
        $("#next").prop("disabled", true);
    }
}
function assignButtonToggle() {
    //disable back button/next button behavior
    if(currentAPage == 0) {
        $("#assignBack").prop("disabled", true);
        $("#assignNext").prop("disabled", false);
    }
    if (currentAPage > 0 && currentAPage < lastAPage) {
        $("#assignBack").prop("disabled", false);
        $("#assignNext").prop("disabled", false);
    }
    if (currentAPage == lastAPage) {
        $("#assignBack").prop("disabled", false);
        $("#assignNext").prop("disabled", true);
    }
    if (lastAPage == 0) {
        $("#assignBack").prop("disabled", true);
        $("#assignNext").prop("disabled", true);
    }
}
function studentButtonToggle() {
    //disable back button/next button behavior
}