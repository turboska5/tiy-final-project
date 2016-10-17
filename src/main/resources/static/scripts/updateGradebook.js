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
    var classIdentifier = $("#identifier").val();
    currentPage = page;

    $.get(
        "/teacherClassUpdate?page=" + page + "&period=" + classPeriod + "&name=" + className + "&identifier=" + classIdentifier,
        function (data) {
            // setup the paginator display
            var descriptionClass = $("#descriptionClass");
            var start = data.number * data.size + 1;
            var end = start + data.size;
            var total = data.totalElements;
            lastPage = Math.ceil(total / data.size) - 1;
            descriptionClass.text(start + " - " + end + " of " + total);

            // var classes = $("#classes");
            // classes.empty();
            // classes.add(data)


            // // iterate over the widgets from the ajax response
            // data.content.forEach(function( widget ){
            //     var template = $("#template").clone();
            //     template.removeAttr("id");
            //
            //     // set the image
            //     var img = template.find("img");
            //     img.attr("src", "/widget/image?id=" + widget.id);
            //
            //     // set the name
            //     var widgetName = template.find(".widgetName");
            //     if(loggedIn){
            //         // user is logged in, make the widget name a link
            //         widgetName.append("<a href='/editWidget?id=" + widget.id + "'>" + widget.name + "</a>")
            //     } else {
            //         // user is not logged in, just make the widget name be text
            //         widgetName.append(widget.name);
            //     }
            //
            //     // set the types
            //     var types = $(".types");
            //     types.empty();
            //
            //     widget.types.forEach(function( type ){
            //         types.append("<li>" + type.type + "</li>");
            //     });
            //
            //     // set the width, height, etc
            //     template.find(".width").text(widget.width);
            //     template.find(".height").text(widget.height);
            //     template.find(".length").text(widget.length);
            //     template.find(".weight").text(widget.weight);
            //
            //     // setup the notes
            //     var a = template.find(".notesLink a");
            //     a.attr("href", "/widgetNotes/?id=" + widget.id);
            //
            //     a.text(widget.notes.length + " notes..");
            //
            //
            //     widgets.append(template);
            // });
    });
}