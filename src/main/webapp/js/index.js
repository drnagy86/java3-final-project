

$("document").ready(function (){

    console.log("Hello, World!");

//    $(":radio").change(console.log("hey"));

    $("#radF").click(function (){
        console.log("Change to F");
        $("#lblTempType").html("&#8457;");
        $("#pResult").html("&#8451;");
        // $("label[for='temp']").val("&#8457;")
    }); //end radF click

    $("#radC").click(function (){
        console.log("Change to C");
        $("#lblTempType").html("&#8451;");
        $("#pResult").html("&#8457;");
        // $("label[for='temp']").val("&#8451;")
    }); //end radC click
});