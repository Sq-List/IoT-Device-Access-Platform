Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1,
                RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};

String.prototype.format = function(args) {
    var result = this;
    if (arguments.length < 1) {
        return result;
    }

    var data = arguments;       //如果模板参数是数组
    if (arguments.length == 1 && typeof (args) == "object") {
        //如果模板参数是对象
        data = args;
    }
    for (var key in data) {
        var value = data[key];
        if (undefined != value) {
            result = result.replace("{" + key + "}", value);
        }
    }
    return result;
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


//图片上传进行预览
function preview(fileId, imgId, classes) {
    var headPicImg = document.getElementById(imgId);
    var file = document.getElementById(fileId);

    if (headPicImg.nodeName === "IMG") {
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            if (classes == undefined) {
                headPicImg.classList.add("tonghe-form-img");
            } else {
                headPicImg.classList.add(classes);
            }
            reader.onload = function (evt) {
                headPicImg.src = evt.target.result;
            };
            reader.readAsDataURL(file.files[0]);
        } else {
            headPicImg.style = "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'";
        }
    } else if (headPicImg.nodeName === "DIV") {
        if (file.files && file.files[0]) {
            for (let i = 0; i < file.files.length; i++) {
                let newImg = document.createElement("img");

                let reader = new FileReader();
                if (classes == undefined) {
                    newImg.classList.add("tonghe-form-img");
                } else {
                    newImg.classList.add(classes);
                }
                reader.onload = function (evt) {
                    newImg.src = evt.target.result;
                };
                reader.readAsDataURL(file.files[i]);

                headPicImg.appendChild(newImg);
            }
        } else {
            for (let i = 0; i < file.files.length; i++) {
                let newImg = document.createElement("img");

                newImg.style = "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'";

                headPicImg.appendChild(newImg);
            }
        }
    }
}

function getToken() {
    return localStorage.getItem("token");
}

// 获取地址栏参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function logout() {
    layui.use(['layer'], function () {
        var $ = layui.$;
        var layer = layui.layer;

        var index = layer.load(2);

        $.ajax({
            url: ip + "/api/session",
            type: "delete",
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.log(res);
                if (res.code == 0) {
                    let data = res.data;
                    console.log(data);
                }

                layer.msg('已退出', {
                    icon: 1,
                    time: 2000
                }, function () {
                    window.location.href = "/login";
                });
            }
        })
    });
}

/**
 * 获取用户信息
 */
if (window.location.pathname.startsWith("/page")) {
    console.log("url: " + window.location.pathname);
    layui.use(['layer'], function () {
        var $ = layui.$;
        var layer = layui.layer;
    
        $.ajax({
            url: ip + "/api/user",
            type: "get",
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.log(res);
                if (res.code == 0) {
                    let data = res.data;
                    console.log(data);
    
                    $("#usernameA").html(data.username);
                } else {
                    layer.msg(res.msg, {
                        icon: 2,
                        time: 2000
                    });
                }
            }
        });
    });
}

var commonDateFormat = "yyyy-MM-dd hh:mm:ss";