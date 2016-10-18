//global pagination support
var currentPage = 1, lastPage = 1;
var currentAPage = 1, lastAPage = 1;
var currentSPage = 1, lastSPage = 1;

$(function() {
    //on load
    var page = 1;
    var aPage = 1;
    var sPage = 1;

    //preload tables
    classSearch("/classTable?period=" + $("#period").val());
    assignSearch("/assignTable?aPeriod=" + $("#period").val());
    studentSearch("/studentTable?sPeriod=" + $("#period").val());

    //button functions
    //search
    $("#classSearch").click(function(){
        currentPage = page;
        var query = "/classTable?page=" + (page - 1) + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
        classSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignSearch").click(function(){
        currentAPage = aPage;
        var query = "/assignTable?aPage=" + (aPage - 1) + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
            + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
        assignSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentSearch").click(function(){
        currentSPage = sPage;
        var query = "/studentTable?sPage=" + (sPage - 1) + "sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
            + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
        studentSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });

    //reset
    $("#classReset").click(function(){
        $("#period").val("");
        $("#name").val("");
        $("#identifier").val("");
        classSearch("/classTable");
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignReset").click(function(){
        $("#aPeriod").val("");
        $("#aName").val("");
        $("#aID").val("");
        $("#aDate").val("");
        $("#aPoints").val("");
        assignSearch("/assignTable");
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentReset").click(function(){
        $("#sPeriod").val("");
        $("#sLastName").val("");
        $("#sFirstName").val("");
        $("#sAName").val("");
        $("#sAID").val("");
        studentSearch("/studentTable");
        return false; // this prevents the form from being submitted when the button is clicked.
    });

    //next
    $("#next").click(function(){
        alert("sdfgg");

        if (currentPage < lastPage) {
            alert("dogg");
            page = (currentPage + 1);
            var query = "/classTable?page=" + page + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
            classSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignNext").click(function(){
        if (currentAPage < lastAPage) {
            aPage = (currentAPage + 1);
            var query = "/assignTable?aPage=" + aPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
                + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentNext").click(function(){
        if (currentSPage < lastSPage) {
            sPage = (currentSPage + 1);
            var query = "/studentTable?sPage=" + sPage + "sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
                + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
            studentSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });

    //back
    $("#back").click(function(){
        if (currentPage > 1) {
            page = (currentPage - 1);
            var query = "/classTable?page=" + page + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
            classSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignBack").click(function(){
        if (currentAPage > 1) {
            aPage = (currentAPage - 1);
            var query = "/assignTable?aPage=" + aPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
                + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentBack").click(function(){
        if (currentSPage > 1) {
            sPage = (currentSPage - 1);
            var query = "/studentTable?sPage=" + sPage + "sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
                + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
            studentSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });

    //supporting functions
    function classSearch(classQuery) {
        //retrieve results
        $.get(classQuery, function(data) {
            $("#classOutput").empty();
            $("#classOutput").append(data);

            //populate pagination based on results
            //elements at a time: $(data).find('.displayedClass').length
            //what page am I on: currentPage
            //last page is: lastPage

            // var start = data.number * data.size  + 1;
            // var end = start + data.size;
            var total = $(data).find('.displayedClass').length;
            // lastPage = Math.ceil(total/data.size) - 1;
            $("#description").text("1" + " - " + "2" + " of " + total);
        });

    }
    function assignSearch(assignQuery) {
        $.get(assignQuery, function(data) {
            $("#assignmentOutput").empty();
            $("#assignmentOutput").append(data);
            console.log(data);

            //populate pagination based on results
            // var start = data.number * data.size  + 1;
            // var end = start + data.size;
            var total = $(data).find('.displayedAssignment').length;
            // lastPage = Math.ceil(total/data.size) - 1;
            $("#descriptionAssign").text("1" + " - " + "2" + " of " + total);
        });
    }
    function studentSearch(studentQuery) {
        $.get(studentQuery, function(data) {
            $("#studentOutput").empty();
            $("#studentOutput").append(data);

            //populate pagination based on results
            // var start = data.number * data.size  + 1;
            // var end = start + data.size;
            var total = $(data).find('.displayedStudent').length;
            // lastPage = Math.ceil(total/data.size) - 1;
            $("#descriptionStudent").text("1" + " - " + "2" + " of " + total);
        });
    }
});