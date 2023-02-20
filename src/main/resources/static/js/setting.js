$(function(){
    $("#uploadForm").submit(upload);
});

function upload() {
    $.ajax({
        url: "http://upload-z2.qiniup.com",
        method: "post",
        // 不要将表单中的数据转为字符串：上传文件时不应该将数据转为字符串
        processData: false,
        // 文件上传时，禁用类型说明，免得给浏览器造成文件类型边界的不确定性
        contentType: false,
        data: new FormData($("#uploadForm")[0]),
        success: function(data) {
            if(data && data.code == 0) {
                // 如果上传云服务器成功，则更新头像访问路径
                $.post(
                    CONTEXT_PATH + "/user/header/url",
                    {
                        "fileName":$("input[name='key']").val()
                    },
                    function(data) {
                        // 七牛云服务器响应给我们的数据为JSON格式的，但我们项目中都是字符串数据，所以需要先转为JSON
                        data = $.parseJSON(data);
                        if(data.code == 0) {
                            window.location.reload();
                        } else {
                            alert(data.msg);
                        }
                    }
                );
            } else {
                alert("上传失败!");
            }
        }
    });
    return false;
}