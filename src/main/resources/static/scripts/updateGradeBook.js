//global pagination support
var currentPage = 0;
var lastPage = 0;
var currentAPage = 0;
var lastAPage = 0;
var currentSPage = 0;
var lastSPage = 0;

$(function() {
    //on load
    var page = 0;
    var aPage = 0;
    var sPage = 0;

    //preload tables
    classSearch("/classTable?period=" + $("#period").val());
    assignSearch("/assignTable?aPeriod=" + $("#period").val());
    studentSearch("/studentTable?sPeriod=" + $("#period").val());

    //button functions
    //search
    $("#classSearch").click(function(){
        currentPage = page;
        var query = "/classTable?page=" + page + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
        classSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignSearch").click(function(){
        currentAPage = aPage;
        var query = "/assignTable?aPage=" + aPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
            + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
        assignSearch(query);
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentSearch").click(function(){
        currentSPage = sPage;
        var query = "/studentTable?sPage=" + sPage + "sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
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

    $("#next").click(function(){
        if (currentPage < lastPage) {
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

    $("#back").click(function(){
        if (currentPage > 0) {
            page = (currentPage- 1);
            var query = "/classTable?page=" + page + "&period=" + $("#period").val() + "&name=" + $("#name").val() + "&identifier=" + $("#identifier").val();
            classSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#assignBack").click(function(){
        if (currentAPage > 0) {
            aPage = (currentAPage -1);
            var query = "/assignTable?aPage=" + aPage + "&aPeriod=" + $("#aPeriod").val() + "&aName=" + $("#aName").val() + "&aID=" + $("#aID").val()
                + "&aDate=" + $("#aDate").val() + "&aPoints=" + $("#aPoints").val();
            assignSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    $("#studentBack").click(function(){
        if (currentSPage > 0) {
            sPage = (currentSPage -1);
            var query = "/studentTable?sPage=" + sPage + "sPeriod=" + $("#sPeriod").val() + "&sLastName=" + $("#sLastName").val() + "&sFirstName=" + $("#sFirstName").val()
                + "&sAName=" + $("#sAName").val() + "&sAID=" + $("#sAID").val();
            studentSearch(query);
        }
        return false; // this prevents the form from being submitted when the button is clicked.
    });
    //supporting functions
    function classSearch(classQuery) {
        $.get(classQuery, function(data) {
            $("#classOutput").empty();
            $("#classOutput").append(data);
            lastPage = Math.ceil(total/data.size) - 1;
        })
    }
    function assignSearch(assignQuery) {
        $.get(assignQuery, function(data) {
            $("#assignmentOutput").empty();
            $("#assignmentOutput").append(data);
            lastAPage = Math.ceil(total/data.size) - 1;
        })
    }
    function studentSearch(studentQuery) {
        $.get(studentQuery, function(data) {
            $("#studentOutput").empty();
            $("#studentOutput").append(data);
            lastSPage = Math.ceil(total/data.size) - 1;
        })
    }
});