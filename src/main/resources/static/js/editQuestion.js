$(function () {
    let testNumber = 0;

    $("form").on("click", "#submit", function () {
        let dateValue = {
            "questionID" : $("#exampleFormControlTextarea5").val(),
            "questionStatus" : $("#exampleFormControlSelect1").val(),
            "questionTitle" : $("#exampleFormControlTextarea0").val(),
            "question" : $("#exampleFormControlTextarea1").val(),
            "questionInputSimple" : $("#exampleFormControlTextarea2").val(),
            "questionOutputSimple" : $("#exampleFormControlTextarea3").val(),
            "questionPrompt" : $("#exampleFormControlTextarea4").val(),
            "questionTimeLimit" : $("#timeLimit").val(),
            "questionMemoryLimit" : $("#MemoryLimit").val()
        };
        $.ajax({
            type: "POST",
            url: "/admin/editQuestion",
            data: JSON.stringify(dateValue),
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                result = JSON.parse(JSON.stringify(result));
                if(result.success){
                    window.location.href="/admin/index";
                }else{
                    alert("修改失败");
                    window.location.href="/admin/index";
                }
            }
        })
    });

    $("#goBack").click(function () {
        window.location.href = "/admin/index";
    })
});