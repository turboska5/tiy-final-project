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
    $("#search").click(function(){
        listClasses(0);
        return false; // this prevents the form from being submitted when the button is clicked.
    });

    // //reset button
    // $("#reset").click(function(){
    //     $("#name").val("");
    //     $("#typeId").val("");
    //     $("#id").val("");
    //     listClasses(0);
    //     return false; // this prevents the form from being submitted when the button is clicked.
    // });

    //populate pagination
    $("#back").click(function(){
        if(currentPage > 0){
            listClasses(currentPage - 1);
        }
        return false;
    });
    $("#next").click(function(){
        if(currentPage < lastPage){
            listClasses(currentPage + 1);
        }
        return false;
    });

});

function listClasses(page) {
    var classPeriod = $("#period").val();
    var className = $("#name").val();
    var classIdentifer = $("#identifer").val();
    currentPage = page;

    $.get(
        "/teacherClassUpdate?page=" + page + "&period=" + classPeriod + "&name=" + className + "&identifier=" + classIdentifer,
        function (data) {
            // setup the paginator display
            var descriptionClass = $("#descriptionClass");
            var start = data.number * data.size + 1;
            var end = start + data.size;
            var total = data.totalElements;
            lastPage = Math.ceil(total / data.size) - 1;
            descriptionClass.text(start + " - " + end + " of " + total);

            //TODO: controller that provides html content and verifies login
            //TODO: replace content

    });
}