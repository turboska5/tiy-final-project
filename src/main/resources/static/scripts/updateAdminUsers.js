/**
 * Created by Jimmy And Andrew on 10/18/16.
 */

//global pagination support
var adminPerPage = 5, teacherPerPage = 5, studentsPerPage = 5;
var currentPage = 0, lastPage = 0;
var currentTPage = 0, lastTPage = 0;
var currentSPage = 0, lastSPage = 0;

$(function() {
    adminSearch("/adminUserTable");
    teacherSearch("/teacherUserTable");
    studentSearch("/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val());

    //button functions
    //search button
    $("#adminSearch").click(function(){
        currentPage = 0;
        var query = "/adminUserTable?page=" + currentPage + "&lastName=" + $("#lastName").val() + "&firstName=" + $("#firstName").val() + "&email=" + $("#email").val() + "&title=" + $("#title").val();
        adminSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#teacherSearch").click(function(){
        currentTPage = 0;
        var query = "/teacherUserTable?page=" + currentTPage + "&tLastName=" + $("#tLastName").val() + "&tFirstName=" + $("#tFirstName").val() + "&tEmail=" + $("#tEmail").val() + "&department=" + $("#department").val();
        teacherSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentSearch").click(function(){
        currentSPage = 0;
        var query = "/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val();
        studentSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //reset button
    $("#adminReset").click(function(){
        currentPage = 0;
        $("#lastName").val("");
        $("#firstName").val("");
        $("#email").val("");
        $("#title").val("");
        adminSearch("/adminUserTable");
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#teacherReset").click(function(){
        currentTPage = 0;
        $("#tLastName").val("");
        $("#tFirstName").val("");
        $("#tEmail").val("");
        $("#department").val("");
        teacherSearch("/teacherUserTable");
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentReset").click(function(){
        currentSPage = 0;
        $("#sLastName").val("");
        $("#sFirstName").val("");
        $("#sEmail").val("");
        $("#sID").val("");
        $("#grade").val("");
        studentSearch("/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val());
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //next button
    $("#adminNext").click(function(){
        if (currentPage < lastPage) {
            currentPage++;
            var query = "/adminUserTable?page=" + currentPage + "&lastName=" + $("#lastName").val() + "&firstName=" + $("#firstName").val() + "&email=" + $("#email").val() + "&title=" + $("#title").val();
            adminSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#teacherNext").click(function(){
        if (currentTPage < lastTPage) {
            currentTPage++;
            var query = "/teacherUserTable?page=" + currentTPage + "&tLastName=" + $("#tLastName").val() + "&tFirstName=" + $("#tFirstName").val() + "&tEmail=" + $("#tEmail").val() + "&department=" + $("#department").val();
            teacherSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentNext").click(function(){
        if (currentSPage < lastSPage) {
            currentSPage++;
            var query = "/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val();
            studentSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //back button
    $("#adminBack").click(function(){
        if (currentPage > 0) {
            currentPage--;
            var query = "/adminUserTable?page=" + currentPage + "&lastName=" + $("#lastName").val() + "&firstName=" + $("#firstName").val() + "&email=" + $("#email").val() + "&title=" + $("#title").val();
            adminSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#teacherBack").click(function(){
        if (currentTPage > 0) {
            currentTPage--;
            var query = "/teacherUserTable?page=" + currentTPage + "&tLastName=" + $("#tLastName").val() + "&tFirstName=" + $("#tFirstName").val() + "&tEmail=" + $("#tEmail").val() + "&department=" + $("#department").val();
            teacherSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentBack").click(function(){
        if (currentSPage > 0) {
            currentSPage--;
            var query = "/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val();
            studentSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
});

//supporting functions
function adminSearch(adminQuery) {
    //retrieve results
    $.get(adminQuery, function(data) {
        //clear existing data and refresh with new
        $("#adminUserOutput").empty();
        $("#adminUserOutput").append(data);
        //populate pagination based on results
        var totalCount = $("#adminListSize").val();
        var totalPages = Math.ceil(totalCount/adminPerPage);
        var onPageCount = $(data).find('.displayedAdmin').length;
        lastPage = totalPages - 1;
        var start = currentPage * adminPerPage  + 1;
        var end = start + onPageCount - 1;
        $("#descriptionAdmin").text(start + " - " + end + " of " + totalCount);
        //execute button logic here
        adminUserButtonToggleBothOff();
        buttonValidator(currentPage, lastPage, "#adminBack", "#adminNext");
    });
}
function teacherSearch(teacherQuery) {
    $.get(teacherQuery, function(data) {
        //clear existing data and refresh with new
        $("#teacherUserOutput").empty();
        $("#teacherUserOutput").append(data);
        //populate pagination based on results
        var totalCount = $("#teacherListSize").val();
        var totalPages = Math.ceil(totalCount/teacherPerPage);
        var onPageCount = $(data).find('.displayedTeacher').length;
        lastTPage = totalPages - 1;
        var start = currentTPage * teacherPerPage + 1;
        var end = start + onPageCount - 1;
        $("#descriptionTeacher").text(start + " - " + end + " of " + totalCount);
        //execute button logic here
        teacherUserButtonToggleBothOff();
        buttonValidator(currentTPage, lastTPage, "#teacherBack", "#teacherNext");
    });
}
function studentSearch(studentQuery) {
    $.get(studentQuery, function(data) {
        //clear existing data and refresh with new
        $("#studentUserOutput").empty();
        $("#studentUserOutput").append(data);
        //populate pagination based on results
        var totalCount = $("#studentListSize").val();
        var totalPages = Math.ceil(totalCount/studentsPerPage);
        var onPageCount = $(data).find('.displayedStudent').length;
        lastSPage = totalPages - 1;
        var start = currentSPage * studentsPerPage + 1;
        var end = start + onPageCount - 1;
        $("#descriptionStudent").text(start + " - " + end + " of " + totalCount);
        //execute button logic here
        studentUserButtonToggleBothOff();
        buttonValidator(currentSPage, lastSPage, "#studentBack", "#studentNext");
    });
}
function adminUserButtonToggleBothOff() {
    $("#adminBack").prop("disabled", true);
    $("#adminNext").prop("disabled", true);
}
function teacherUserButtonToggleBothOff() {
    $("#teacherBack").prop("disabled", true);
    $("#teacherNext").prop("disabled", true);
}
function studentUserButtonToggleBothOff() {
    $("#studentBack").prop("disabled", true);
    $("#studentNext").prop("disabled", true);
}
function buttonValidator(currentPage, lastPage, buttonIDBack, buttonIDNext) {
    if (currentPage > 0 && currentPage < lastPage) {
        $(buttonIDBack).prop("disabled", false);
        $(buttonIDNext).prop("disabled", false);
    }
    if (currentPage == lastPage) {
        $(buttonIDBack).prop("disabled", false);
        $(buttonIDNext).prop("disabled", true);
    }
    if (currentPage == 0) {
        if (lastPage == 0) {
            $(buttonIDBack).prop("disabled", true);
            $(buttonIDNext).prop("disabled", true);
        } else {
            $(buttonIDBack).prop("disabled", true);
            $(buttonIDNext).prop("disabled", false);
        }
    }
}