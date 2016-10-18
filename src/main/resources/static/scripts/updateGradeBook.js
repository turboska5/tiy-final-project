//global pagination support
var currentPage = 0, lastPage = 0;
var currentAPage = 0, lastAPage = 0;
var currentSPage = 0, lastSPage = 0;

$(function() {
    //button management
    classButtonToggle();
    assignButtonToggle();
    studentButtonToggle();

    //preload tables to display
    classSearch("/classTable?period=" + $("#period").val());
    assignSearch("/assignTable?aPeriod=" + $("#period").val());
    studentSearch("/studentTable?sPeriod=" + $("#period").val());

    //button functions
    //search button
    $("#classSearch").click(function(){
        currentPage = 0;
        classButtonToggle();
        var query = "/classTable?page=" + currentPage + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
        classSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignSearch").click(function(){
        currentAPage = 0;
        assignButtonToggle();
        var query = "/assignTable?page=" + currentAPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
            + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
        assignSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentSearch").click(function(){
        currentSPage = 0;
        studentButtonToggle();
        var query = "/studentTable?page=" + currentSPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
            + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
        studentSearch(query);
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
            var query = "/assignTable?page=" + currentAPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
                + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
            assignButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentNext").click(function(){
        if (currentSPage < lastSPage) {
            currentSPage++;
            var query = "/studentTable?page=" + currentSPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
                + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
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
            var query = "/assignTable?page=" + currentAPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
                + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
            assignButtonToggle();
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentBack").click(function(){
        if (currentSPage > 0) {
            currentSPage--;
            var query = "/studentTable?page=" + currentSPage + "&sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
                + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
            studentSearch(query);
            studentButtonToggle();
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
            lastPage = Math.ceil(totalCount/2) - 1;
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
            lastAPage = Math.ceil(totalCount/2) - 1;
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
            lastSPage = Math.ceil(totalCount/5) - 1;
            // var start = data.number * data.size  + 1;
            // var end = start + data.size;
            $("#descriptionStudent").text("1" + " - " + "2" + " of " + totalCount);
        });
    }
    function classButtonToggle() {
        //disable back button/next button behavior
        if(currentPage == 0) {
            $("#back").prop("disabled", true);
            $("#next").prop("disabled", false);
        } else if (currentPage < lastPage) {
            $("#back").prop("disabled", false);
            $("#next").prop("disabled", false);
        } else if (currentPage == lastPage) {
            $("#back").prop("disabled", false);
            $("#next").prop("disabled", true);
        }
    }
    function assignButtonToggle() {
        //disable back button/next button behavior
        if(currentAPage == 0) {
            $("#assignBack").prop("disabled", true);
            $("#assignNext").prop("disabled", false);
        } else if (currentAPage < lastAPage) {
            $("#assignBack").prop("disabled", false);
            $("#assignNext").prop("disabled", false);
        } else if (currentAPage == lastAPage) {
            $("#assignBack").prop("disabled", false);
            $("#assignNext").prop("disabled", true);
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