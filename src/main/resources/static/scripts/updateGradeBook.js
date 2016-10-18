//global pagination support
var currentPage = 0, lastPage = 0;
var currentAPage = 0, lastAPage = 0;
var currentSPage = 0, lastSPage = 0;

$(function() {
    //on load variables
    var page = 0;
    var aPage = 0;
    var sPage = 0;
    //disable back button
    $("#back").prop("disabled", true);
    $("#assignBack").prop("disabled", true);
    $("#studentBack").prop("disabled", true);
    //preload tables to display
    classSearch("/classTable?period=" + $("#period").val());
    assignSearch("/assignTable?aPeriod=" + $("#period").val());
    studentSearch("/studentTable?sPeriod=" + $("#period").val());

    //button functions
    //search button
    $("#classSearch").click(function(){
        currentPage = page;
        var query = "/classTable?page=" + page + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
        classSearch(query);
        $("#back").prop("disabled", true);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignSearch").click(function(){
        currentAPage = aPage;
        var query = "/assignTable?page=" + aPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
            + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
        assignSearch(query);
        $("#assignBack").prop("disabled", true);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentSearch").click(function(){
        currentSPage = sPage;
        var query = "/studentTable?page=" + sPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
            + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
        studentSearch(query);
        $("#studentBack").prop("disabled", true);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //reset button
    $("#classReset").click(function(){
        $("#period").val("");
        $("#name").val("");
        $("#identifier").val("");
        classSearch("/classTable");
        $("#back").prop("disabled", true);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignReset").click(function(){
        $("#aPeriod").val("");
        $("#aName").val("");
        $("#aID").val("");
        $("#aDate").val("");
        $("#aPoints").val("");
        assignSearch("/assignTable");
        $("#assignBack").prop("disabled", true);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentReset").click(function(){
        $("#sPeriod").val("");
        $("#sLastName").val("");
        $("#sFirstName").val("");
        $("#sAName").val("");
        $("#sAID").val("");
        studentSearch("/studentTable");
        $("#studentBack").prop("disabled", true);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //next button
    $("#next").click(function(){
        if (currentPage < lastPage) {
            page = (currentPage + 1);
            var query = "/classTable?page=" + page + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
            classSearch(query);
            $("#back").prop("disabled", false);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignNext").click(function(){
        if (currentAPage < lastAPage) {
            aPage = (currentAPage + 1);
            var query = "/assignTable?page=" + aPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
                + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
            $("#assignBack").prop("disabled", false);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentNext").click(function(){
        if (currentSPage < lastSPage) {
            sPage = (currentSPage + 1);
            var query = "/studentTable?page=" + sPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
                + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
            studentSearch(query);
            $("#studentBack").prop("disabled", false);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //back button
    $("#back").click(function(){
        if (currentPage > 0) {
            page = (currentPage - 1);
            var query = "/classTable?page=" + page + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
            classSearch(query);
        } else {
            $("#back").prop("disabled", true);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignBack").click(function(){
        if (currentAPage > 0) {
            aPage = (currentAPage - 1);
            var query = "/assignTable?page=" + aPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
                + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
        } else {
            $("#assignBack").prop("disabled", true);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentBack").click(function(){
        if (currentSPage > 0) {
            sPage = (currentSPage - 1);
            var query = "/studentTable?page=" + sPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
                + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
            studentSearch(query);
        } else {
            $("#studentBack").prop("disabled", true);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });

    //supporting functions
    function classSearch(classQuery) {
        //retrieve results
        $.get(classQuery, function(data) {
            //clear existing data and refresh with new
            $("#classOutput").empty();
            $("#classOutput").append(data);
            //populate pagination based on results
            var totalCount = $("#classListSize").val();
            var onPageCount = $(data).find('.displayedClass').length;
            lastPage = Math.ceil(totalCount/onPageCount) - 1;
            // var start = data.number * data.size  + 1;
            // var end = start + data.size;
            $("#description").text("1" + " - " + "2" + " of " + totalCount);
        });
    }
    function assignSearch(assignQuery) {
        $.get(assignQuery, function(data) {
            //clear existing data and refresh with new
            $("#assignmentOutput").empty();
            $("#assignmentOutput").append(data);
            //populate pagination based on results
            var totalCount = $("#assignListSize").val();
            var onPageCount = $(data).find('.displayedAssignment').length;
            lastAPage = Math.ceil(totalCount/onPageCount) - 1;
            // var start = data.number * data.size  + 1;
            // var end = start + data.size;
            $("#descriptionAssign").text("1" + " - " + "2" + " of " + totalCount);
        });
    }
    function studentSearch(studentQuery) {
        $.get(studentQuery, function(data) {
            //clear existing data and refresh with new
            $("#studentOutput").empty();
            $("#studentOutput").append(data);
            //populate pagination based on results
            var totalCount = $("#studentListSize").val();
            var onPageCount = $(data).find('.displayedStudent').length;
            lastSPage = Math.ceil(totalCount/onPageCount) - 1;
            // var start = data.number * data.size  + 1;
            // var end = start + data.size;
            $("#descriptionStudent").text("1" + " - " + "2" + " of " + totalCount);
        });
    }
});