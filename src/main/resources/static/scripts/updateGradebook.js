/**
 * Modified from Doug's widget example.
 * Created by Andrew and Jimmy on 10/17/16.
 */

var currentPage = 0;
var lastPage = 0;

//on load
$(function () {
    var page = 1;
    //get the default list of classes
    listClasses(currentPage, "", "", "");

    //search button
    $("#searchClasses").click(function(){
        listClasses(0);
        return false; // this prevents the form from being submitted when the button is clicked.
    });

    // //reset button
    $("#resetClasses").click(function(){
        $("#name").val("");
        $("#typeId").val("");
        $("#id").val("");
        listClasses(0);
        return false; // this prevents the form from being submitted when the button is clicked.
    });

    //pagination functionality
    $("#back").click(function(){
        if(currentPage > 0){
            listClasses(currentPage - 1);
        }
        return false;
    });
    //pagination functionality
    $("#next").click(function(){
        if(currentPage < lastPage){
            listClasses(currentPage + 1);
        }
        return false;
    });
});

function listClasses(page) {
    //from input fields
    var classPeriod = $("#period").val();
    var className = $("#name").val();
    var classIdentifier = $("#identifier").val();
    currentPage = page;

    $.get("/teacherClassUpdate?page=" + page + "&period=" + classPeriod + "&name=" + className + "&identifier=" + classIdentifier, function (data) {
        //paginator display
        var descriptionClass = $("#descriptionClass");
        var start = data.number * data.size + 1;
        var end = start + data.size;
        var total = data.totalElements;
        lastPage = Math.ceil(total / data.size) - 1;
        descriptionClass.text(start + " - " + end + " of " + total);

        //container for class results
        var teacherClasses = $("#teacherClasses");
        teacherClasses.empty();

        // iterate over the classes from the ajax response
        data.content.forEach(function(teacherClass){
            var template = $("#template").clone();
            template.removeAttr("id");

            // set the attributes
            template.find(".period").text(teacherClass.period);
            template.find(".name").text(teacherClass.name);
            template.find(".identifier").text(teacherClass.identifier);
            template.find(".department").text(teacherClass.department);
            template.find(".teacherName").text(teacherClass.teacherLastName + ", " + teacherClass.teacherFirstName);
            template.find(".assignmentNumber").text(teacherClass.assignmentNumber);
            template.find(".classAverage").text(teacherClass.classAverage);
            teacherClass.append(template);
        });
    });
}

// $.get( "ajax/test.html", function( data ) {
//     $( ".result" ).html( data );
//     alert( "Load was performed." );
// });