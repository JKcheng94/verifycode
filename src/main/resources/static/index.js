/**
 * @Copyright (C) 2019 
 * @Description: TODO
 * @Author  dp_blue
 * @Date 2019-02-11 14:03
 */
$(document).ready(function () {
    $(".varifycode").click();
});
checkCode = function(){
    var input = $(".incheck")[0].value;
    if(input !== "" && input !== null){
        if(inputCode !== undefined && inputCode.toLowerCase() === input){
            alert("验证通过！")
        }else{
            alert("验证码错误！");
        }
    }else{
        alert("请输入验证码！")
    }

}
var inputCode;
function reloadImage() {
    $.ajax({
        url : "/reloadCode",
        type : "post",
        data : "",
        // dataType : "json",
        success : function(data){
            inputCode = data;
            var image = document.getElementById("varifycode");
            image.src = "./1.jpg?" + Math.random();
        },
        error : function (error) {
            alert("请求出错！" + error);
        }
    });
}