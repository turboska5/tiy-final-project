/**
 * Created by Jimmy And Andrew on 10/18/16.
 */

//global pagination support
var adminPerPage = 5, teacherPerPage = 5, studentsPerPage = 5;
var currentPage = 0, lastPage = 0;
var currentTPage = 0, lastTPage = 0;
var currentSPage = 0, lastSPage = 0;

$(function() {
    onLoad();

    //button functions
    //search button
    $("#adminSearch").click(function(){
        currentPage = 0;
        adminButtonToggle();
        var query = "/adminUserTable?page=" + currentPage + "&lastName=" + $("#lastName").val() + "&firstName=" + $("#firstName").val() + "&email=" + $("#email").val() + "&title=" + $("#title").val();
        adminSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#teacherSearch").click(function(){
        currentTPage = 0;
        teacherButtonToggle();
        var query = "/teacherUserTable?page=" + currentTPage + "&tLastName=" + $("#tLastName").val() + "&tFirstName=" + $("#tFirstName").val() + "&tEmail=" + $("#tEmail").val() + "&department=" + $("#department").val();
        teacherSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentSearch").click(function(){
        currentSPage = 0;
        studentButtonToggle();
        var query = "/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val();
        studentSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //reset button
    $("#adminReset").click(function(){
        $("#lastName").val("");
        $("#firstName").val("");
        $("#email").val("");
        $("#title").val("");
        adminSearch("/adminUserTable");
        currentPage = 0;
        adminButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#teacherReset").click(function(){
        $("#tLastName").val("");
        $("#tFirstName").val("");
        $("#tEmail").val("");
        $("#department").val("");
        teacherSearch("/teacherUserTable");
        currentTPage = 0;
        teacherButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentReset").click(function(){
        $("#sLastName").val("");
        $("#sFirstName").val("");
        $("#sEmail").val("");
        $("#sID").val("");
        $("#grade").val("");
        studentSearch("/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val());
        currentSPage = 0;
        studentButtonToggle();
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //next button
    $("#adminNext").click(function(){
        if (currentPage < lastPage) {
            currentPage++;
            var query = "/adminUserTable?page=" + currentPage + "&lastName=" + $("#lastName").val() + "&firstName=" + $("#firstName").val() + "&email=" + $("#email").val() + "&title=" + $("#title").val();
            adminSearch(query);
            adminButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#teacherNext").click(function(){
        if (currentTPage < lastTPage) {
            currentTPage++;
            var query = "/teacherUserTable?page=" + currentTPage + "&tLastName=" + $("#tLastName").val() + "&tFirstName=" + $("#tFirstName").val() + "&tEmail=" + $("#tEmail").val() + "&department=" + $("#department").val();
            teacherSearch(query);
            teacherButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentNext").click(function(){
        if (currentSPage < lastSPage) {
            currentSPage++;
            var query = "/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val();
            studentSearch(query);
            studentButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //back button
    $("#back").click(function(){
        if (currentPage > 0) {
            currentPage--;
            var query = "/adminUserTable?page=" + currentPage + "&lastName=" + $("#lastName").val() + "&firstName=" + $("#firstName").val() + "&email=" + $("#email").val() + "&title=" + $("#title").val();
            adminSearch(query);
            adminButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#teacherBack").click(function(){
        if (currentTPage > 0) {
            currentTPage--;
            var query = "/teacherUserTable?page=" + currentTPage + "&tLastName=" + $("#tLastName").val() + "&tFirstName=" + $("#tFirstName").val() + "&tEmail=" + $("#tEmail").val() + "&department=" + $("#department").val();
            teacherSearch(query);
            teacherButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentBack").click(function(){
        if (currentSPage > 0) {
            currentSPage--;
            var query = "/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val();
            studentSearch(query);
            studentButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
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
        });
    }

    //supporting functions
    //preload tables and enable appropriate buttons
    function onLoad() {
        adminSearch("/adminUserTable");
        adminButtonToggle();
        teacherSearch("/teacherUserTable");
        teacherButtonToggle();
        studentSearch("/studentUserTable?page=" + currentSPage + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val() + "&sEmail=" + $("#sEmail").val() + "&sID=" + $("#sID").val() + "&grade=" + $("#grade").val());
        studentButtonToggle();
    };

    function adminButtonToggle() {
        //disable back button/next button behavior
        if(currentPage == 0) {
            $("#adminBack").prop("disabled", true);
            $("#adminNext").prop("disabled", false);
        } else if (currentPage < lastPage) {
            $("#adminBack").prop("disabled", false);
            $("#adminNext").prop("disabled", false);
        } else if (currentPage == lastPage) {
            $("#adminBack").prop("disabled", false);
            $("#adminNext").prop("disabled", true);
        }
    }
    function teacherButtonToggle() {
        //disable back button/next button behavior
        if(currentTPage == 0) {
            $("#teacherBack").prop("disabled", true);
            $("#teacherNext").prop("disabled", false);
        } else if (currentTPage < lastTPage) {
            $("#teacherBack").prop("disabled", false);
            $("#teacherNext").prop("disabled", false);
        } else if (currentTPage == lastTPage) {
            $("#teacherBack").prop("disabled", false);
            $("#teacherNext").prop("disabled", true);
        }
    }
    function studentButtonToggle() {
        //disable back button/next button behavior
        if(currentSPage == 0) {
            $("#studentBack").prop("disabled", true);
            $("#studentNext").prop("disabled", false);
        } else if (currentSPage < lastSPage) {
            $("#studentBack").prop("disabled", false);
            $("#studentNext").prop("disabled", false);
        } else if (currentSPage == lastSPage) {
            $("#studentBack").prop("disabled", false);
            $("#studentNext").prop("disabled", true);
        }
    }
});