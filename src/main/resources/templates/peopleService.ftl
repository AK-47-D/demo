<!DOCTYPE html>
<html>
<head>
    <link href="static/css/bootstrap.css" />
    <link href="static/css/pnotify.custom.min.css" />
</head>
<body>
<button id="service" >开始造人</button>
<div id="context"></div>
</body>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/js/pnotify.custom.min.js"></script>
<script>
    var service = {
        count : 0,
        start:false,
        init:function(){
            $("#service").unbind().bind("click",function(){
                $(this).html("点击" + (++service.count) + "次");
                service.timeout();
            });
        },
        timeout:function(){
            if(!this.start){
                setTimeout(this.ajax,2000);
                this.start = true;
            }
        },
        ajax:function(){
            $("#service").attr("disabled","disabled").html($("#service").html() + " 正在通知造人");
            $.ajax({
                type:"post",
                dataType:"text",
                data:{count:service.count},
                url:"testNew",
                success:function(data){
                    new PNotify({
                        title: 'success!',
                        styling : 'bootstrap3',
                        text: '通知成功',
                        type: 'success',
                        delay:2000
                    });
                    $("#context").html($("#context").html()+"<br/>" + data);
                    $("#service").removeAttr("disabled","disabled").html("开始造人");
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    new PNotify({
                        title: 'fail!',
                        styling : 'bootstrap3',
                        text: '通知失败',
                        type: 'error',
                        delay:2000
                    });
                    $("#context").html($("#context").html()+"<br/>出错");
                    $("#service").removeAttr("disabled","disabled").html("开始造人");
                }

            })
            service.count = 0;
            service.start = false;
        }
    }
    service.init();
</script>
</html>