$(function () {
    let testNumber = 0;
    $("#addTest").click(function () {
        testNumber++;
        $node = '<div class="row form-group">'
        +    '<div class="col">'
        +    '<textarea class="form-control" id="input'+testNumber+'" rows="3" placeholder="测试用例输入"></textarea>'
        +    '</div>'
        +    '<div class="col">'
        +    '<textarea class="form-control" id="output'+testNumber+'" rows="3" placeholder="测试用例输入"></textarea>'
        +    '</div>'
        +    '<div class="col-2">'
        +    '<div type="button" class="btn btn-danger deleteTest">删除</div>'
        +    '</div>'
        +    '</div>'
        $(this).before($node);
    });

    $("form").on("click", ".deleteTest", function () {
        $(this).parent().parent().remove();
    })

    $("form").on("click", "#submit", function () {
        let inputList = {};
        let outputList = {};
        for(let i=0; i<=testNumber; i++){
            let input = "input"+i;
            let output = "output"+i;
            let name = "test"+i
            if($("#"+input)[0] && $("#"+output)[0]){
                inputList[name] = $("#"+input).val();
                outputList[name] = $("#"+output).val();
            }
        }
        let dateValue = {
            "questionStatus" : $("#exampleFormControlSelect1").val(),
            "questionTitle" : $("#exampleFormControlTextarea0").val(),
            "question" : $("#exampleFormControlTextarea1").val(),
            "questionInputSimple" : $("#exampleFormControlTextarea2").val(),
            "questionOutputSimple" : $("#exampleFormControlTextarea3").val(),
            "questionPrompt" : $("#exampleFormControlTextarea4").val(),
            "questionTimeLimit" : $("#timeLimit").val(),
            "questionMemoryLimit" : $("#MemoryLimit").val(),
            "questionInput" : inputList,
            "questionOutput" : outputList
        }
        console.log(dateValue);
        $.ajax({
            type: "POST",
            url: "/admin/createQuestion",
            data: JSON.stringify(dateValue),
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                result = JSON.parse(JSON.stringify(result));
                if(result.success){
                    window.location.href="/admin/index";
                }else{
                    alert("添加失败");
                    window.location.href="/admin/index";
                }
            }
        })
    });
});