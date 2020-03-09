$(function () {
    let checkInput1 = false;
    let checkInput2 = false;
    let checkInput3 = false;

    $("#validationServer01").keyup(function () {
        let page = "/signUp/emailCheck";
        let value = $(this).val();
        console.log(value);
        let dataValue = {
            "userMail": value
        };
        console.log(JSON.stringify(dataValue));
        $.ajax({
            type: "POST",
            url: page,
            data: JSON.stringify(dataValue),
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                result = JSON.parse(JSON.stringify(result));
                console.log(result.success);
                if (result.success) {
                    $("#validationServer01").addClass("is-valid").removeClass("is-invalid");
                    $("#checkValidationServer01").addClass("valid-feedback").removeClass("invalid-feedback").html(result.msg);
                } else {
                    $("#validationServer01").addClass("is-invalid").removeClass("is-valid");
                    $("#checkValidationServer01").addClass("invalid-feedback").removeClass("valid-feedback").html(result.msg);
                }
            }
        });
    });

    $("#validationServer02").keyup(function () {
        let value = $(this).val();
        if (value.length < 6) {
            $(this).addClass("is-invalid").removeClass("is-valid");
            $("#checkValidationServer02").addClass("invalid-feedback").html("密码长度过短");
        } else {
            $(this).addClass("is-valid").removeClass("is-invalid");
            $("#checkValidationServer02").addClass("valid-feedback").html("请记牢您的密码").removeClass("invalid-feedback");
        }
    });

    $("#validationServer03").keyup(function () {
        let passWd = $("#validationServer02").val();
        let rePassWd = $(this).val();
        if (passWd == rePassWd) {
            $(this).addClass("is-valid").removeClass("is-invalid");
            $("#checkValidationServer03").addClass("valid-feedback").html("重复验证成功").removeClass("invalid-feedback");
        } else {
            $(this).addClass("is-invalid").removeClass("is-valid");
            $("#checkValidationServer03").addClass("invalid-feedback").html("输入密码不一致");
        }
    });


});