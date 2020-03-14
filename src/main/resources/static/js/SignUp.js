$(function () {
    let checkInput1 = false;
    let checkInput2 = false;
    let checkInput3 = false;
    let checkInput4 = false;
    let checkInput5 = false;
    let checkInput6 = false;

    $("#validationServer01").change(function () {
        let page = "/signUp/emailCheck";
        let value = $(this).val();
        let dataValue = {
            "userMail": value
        };
        $.ajax({
            type: "POST",
            url: page,
            data: JSON.stringify(dataValue),
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                result = JSON.parse(JSON.stringify(result));
                if (result.success) {
                    $("#validationServer01").addClass("is-valid").removeClass("is-invalid");
                    $("#checkValidationServer01").addClass("valid-feedback").removeClass("invalid-feedback").html(result.msg);
                    checkInput1 = true;
                } else {
                    $("#validationServer01").addClass("is-invalid").removeClass("is-valid");
                    $("#checkValidationServer01").addClass("invalid-feedback").removeClass("valid-feedback").html(result.msg);
                    checkInput1 = false;
                }
            }
        });
    });

    $("#validationServer02").change(function () {
        let value = $(this).val();
        if (value.length < 6) {
            $(this).addClass("is-invalid").removeClass("is-valid");
            $("#checkValidationServer02").addClass("invalid-feedback").html("密码长度过短").removeClass("valid-feedback");
            checkInput2 = false;
        } else {
            $(this).addClass("is-valid").removeClass("is-invalid");
            $("#checkValidationServer02").addClass("valid-feedback").html("请记牢您的密码").removeClass("invalid-feedback");
            checkInput2 = true;
        }
    });

    $("#validationServer03").change(function () {
        let passWd = $("#validationServer02").val();
        let rePassWd = $(this).val();
        if (passWd == rePassWd) {
            $(this).addClass("is-valid").removeClass("is-invalid");
            $("#checkValidationServer03").addClass("valid-feedback").html("重复验证成功").removeClass("invalid-feedback");
            checkInput3 = true;
        } else {
            $(this).addClass("is-invalid").removeClass("is-valid");
            $("#checkValidationServer03").addClass("invalid-feedback").html("输入密码不一致").removeClass("valid-feedback");
            checkInput3 = false;
        }
    });

    $("#validationServer04").change(function () {
        if(!$(this).val()){
            $(this).addClass("is-invalid").removeClass("is-valid");
            $("#checkValidationServer04").addClass("invalid-feedback").html("姓名不能为空").removeClass("valid-feedback");
            checkInput4 = false;
        }else {
            $(this).addClass("is-valid").removeClass("is-invalid");
            $("#checkValidationServer04").addClass("valid-feedback").html("请输入正确信息以供审核").removeClass("invalid-feedback");
            checkInput4 = true;
        }
    });

    $("#validationServer05").change(function () {
        if($(this).val() == "0"){
            $(this).addClass("is-invalid").removeClass("is-valid");
            $("#checkValidationServer05").addClass("invalid-feedback").html("班级不能为空").removeClass("valid-feedback");
            checkInput5 = false;
        }else {
            $(this).addClass("is-valid").removeClass("is-invalid");
            $("#checkValidationServer05").addClass("valid-feedback").html("请输入正确信息以供审核").removeClass("invalid-feedback");
            checkInput5 = true;
        }
    });

    $("#validationServer06").keyup(function () {
        let value = $(this).val();
        let dataValue = {
            "studentID": value
        };
        $.ajax({
            type: "POST",
            url: "/signUp/studentIDCheck",
            data: JSON.stringify(dataValue),
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                result = JSON.parse(JSON.stringify(result));
                if (result.success) {
                    $("#validationServer06").addClass("is-valid").removeClass("is-invalid");
                    $("#checkValidationServer06").addClass("valid-feedback").removeClass("invalid-feedback").html(result.msg);
                    checkInput6 = true;
                } else {
                    $("#validationServer06").addClass("is-invalid").removeClass("is-valid");
                    $("#checkValidationServer06").addClass("invalid-feedback").removeClass("valid-feedback").html(result.msg);
                    checkInput6 = false;
                }
            }
        });
    });

    $("#signUp").click(function () {
        if(checkInput1 && checkInput2 && checkInput3 && checkInput4 && checkInput5 && checkInput6){
            console.log("with");
            let dataValue = {
                "userMail" : $("#validationServer01").val(),
                "userPassWd" : $("#validationServer02").val(),
                "studentName" : $("#validationServer04").val(),
                "studentClassID" : $("#validationServer05").val(),
                "studentID" : $("#validationServer06").val(),
            };
            console.log(dataValue);
            $.ajax({
                type: "POST",
                url: "/signUp/submit",
                data: JSON.stringify(dataValue),
                contentType: "application/json",
                dataType: "json",
                success: function (result) {
                    result = JSON.parse(JSON.stringify(result));
                    if(result.success){
                        window.location.href="/index/check";
                    }else{
                        alert(result.msg);
                    }
                }
            })
        }
    });

    $("#validationServer07").change(function () {
        let page = "/signUp/emailCheck";
        let value = $(this).val();
        let dataValue = {
            "userMail": value
        };
        $.ajax({
            type: "POST",
            url: page,
            data: JSON.stringify(dataValue),
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                result = JSON.parse(JSON.stringify(result));
                if (!result.success) {
                    $("#validationServer07").addClass("is-valid").removeClass("is-invalid");
                    $("#checkValidationServer07").addClass("valid-feedback").removeClass("invalid-feedback").html(result.msg);
                } else {
                    $("#validationServer07").addClass("is-invalid").removeClass("is-valid");
                    $("#checkValidationServer07").addClass("invalid-feedback").removeClass("valid-feedback").html(result.msg);
                }
            }
        });
    });

});