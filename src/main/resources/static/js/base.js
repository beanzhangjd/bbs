/*****************功能类函数********************/
//获取url参数的函数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return r[2];
    }
    return null;
}

//访问单个帖子的函数
function showPost(id){
    location="/api/showPostDetail?id="+id
}
function getNumByModule() {
    var data
    $.ajax({
        url:"/api/getNumByModule",
        data:"id="+getUrlParam("id"),
        type:"get",
        datatype:"json",
        async:false,
        success:function(obj){
            data=obj
        }
    })
    return data;
}

function limitUtil(totalRecords) {
    if(totalRecords%30){
        return totalRecords/30+1
    }else{
        return totalRecords/30
    }
}

/**************注册页面****************/
//验证码
function countdown(){
    var second = 60;
    var time = setInterval(function(){
        if(second>0){
            $("#code-bt").attr("disabled",true);
            $("#code-val").html("正在发送("+second+"s)");
            second--;
        }else{
            $("#code-val").html("发送验证码");
            $("#code-bt").attr("disabled",false);
            clearInterval(time);
        }
    },1000);
}
//外部单位公司选择
function showOtherCompany(){
    $("#select_1").html("<option value='0'>- - 公司 - -</option>")
    $.ajax({
        url:"",
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                for(i=0;i<obj.data.length;i++){
                    var op=new Option(obj.data[i].company,obj.data[i].company_no)
                    $("#select_1").append(op)
                }
            }
        }
    })
}
//公司下拉选函数
function showCompany(){
    $("#select_1").html("<option value='0'>- - 公司 - -</option>")
    $.ajax({
        url:"/showCompany",
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                for(i=0;i<obj.data.length;i++){
                    var op=new Option(obj.data[i].dept,obj.data[i].id)
                    $("#select_1").append(op)
                }
            }
        }
    })
}
//根据公司获取所有部门函数
function showDeptByCompany(){
    $("#select_2").html("<option value='0'>- - 部门 - -</option>")
    $.ajax({
        url:"/showDeptByCompany",
        data:"company_no="+$("#select_1").val(),
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                for(i=0;i<obj.data.length;i++){
                    var op=new Option(obj.data[i].dept,obj.data[i].id)
                    $("#select_2").append(op)
                }
            }
        }
    })
}
//注册
function register(){
    if(!checkPhoneG($("#phone").val())){
        alert("请检查你输入的手机号")
        return false;
    }
    if (!checkPwdLength($("#password").val().length)&&!checkPwdG($("#password").val(),$("#check").val())) {
        alert("请检查您是输入的密码")
        return false;
    }
    if (!$("#name").val()) {
        alert("姓名不可为空")
        return false;
    }
    if (!$("#name").val().length>36) {
        alert("姓名过长")
        return false;
    }
    if (!$("#check_code").val()) {
        alert("验证码不可为空")
        return false;
    }
    if ($("#t_box").prop("checked")){
        if (!$("#select_1").val()||isNaN($("#select_1").val())){
            alert("请选择部门信息")
            return false
        }
    }else {
        if ($("#select_1").val()==="0"||$("#select_2").val()==="0"||isNaN($("#select_2").val())||isNaN($("#select_1").val())){
            alert("请选择部门信息")
            return false
        }
    }
    $.ajax({
        url: "/register",
        data: $("#register-form").serialize(),
        type: "post",
        dataType: "JSON",
        success: function (obj) {
            if(obj.state==1){
            	alert("注册成功，将跳入登陆界面。")
                location="/"
            }else{
            	alert("注册失败")
            }
        }
    })
}
//验证输入框的函数
//验证手机号格式
function checkPhoneG(phone){
    //var reg=new  new RegExp("/^[1][3,4,5,7,8][0-9]{9}$/");
    var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if(phone== "" || phone.length == 0){
        return false
    }else{
        if (reg.test(phone)) {
            $("#phone_p").html("&nbsp;")
            return true
        } else {
            $("#phone_p").html("请输入正确的手机号").css("color", "red")
            return false
        }
    }
}
//验证密码长度
function checkPwdLength(pwdlen){
    if (pwdlen.length == 0) {
        return false
    }
    if (pwdlen < 6 || pwdlen > 16) {
        $("#pwd_p").html("请输入6-16位的密码").css("color", "red")
        return false
    }else{
        $("#pwd_p").html("&nbsp;")
        return true
    }
}
//验证两次密码
function checkPwdG(pwd,ckeck){
    if(pwd==ckeck){
        $("#cpwd_p").html("&nbsp;")
        return true
    }else{
        $("#cpwd_p").html("两次密码输入不一致").css("color", "red")
        return false
    }
}


/****************显示用户信息********************/
//显示现在登陆的用户信息
function getUserById(){
    $.ajax({
        url: "/api/getUserById",
        type: "post",
        dataType: "JSON",
        success: function (obj) {
            if(obj.state===1){
                var user=obj.data
                $("#user-phone").html(user.phone)
                $("#user-pwd").html(user.password)
                $("#user-name").html(user.name)
                $("#user-head").attr("src",user.head)
                $("#nav-name").html(user.name)
                showDeptByUser(user.dept_id)
                if (user.grade>5){
                    $("#Button-Top").css("display","block")
                    $("#Button-over").css("display","block")
                }
            }
        }
    })
    showCompany()
}
//根据用户的部门id获取部门名字
function showDeptByUser(dept_id){
    $.ajax({
        url:"/api/getDeptByUser",
        data:"dept_id="+dept_id,
        type:"get",
        async:false,
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                $("#user-company").html(obj.data.company)
                $("#user-dept").html(obj.data.dept)
            }
        }
    })
}
/*******************修改用户信息********************/
//修改密码
function savePwd(){
    var key=checkPwdLength($("#oldpwd").val().length)&&checkPwdLength($("#newpwd").val().length)&&checkPwdG($("#newpwd").val(),$("#check").val())
    if (!key) {
        alert("你输入的密码格式有误")
        return
    }
    if (confirm("你确定修改吗？")) {
        $.ajax({
            url: "/api/updatePassword",
            data: $("#pwd-form").serialize(),
            type: "post",
            dataType: "JSON",
            success: function (obj) {
                alert(obj.message)
                if(obj.state==1){
                    del()
                }
            }
        })
    }
}
//修改姓名
function saveName(){
    if(!$("#iname").val()){
        alert("姓名不可以为空")
        return false;
    }
    if($("#iname").val().length>36){
        alert("姓名过长")
        return false;
    }
    if (confirm("你确定修改吗？")) {
        $.ajax({
            url: "/api/updateName",
            data: $("#name-form").serialize(),
            type: "post",
            dataType: "JSON",
            success: function (obj) {
                alert(obj.message)
                if(obj.state==1){
                    $("#user-name").html($("#iname").val())
                    del()
                }
            }
        })
    }

}
//修改部门
function saveDept(){
    if ($("#t_box").prop("checked")){
        if (!$("#select_1").val()||isNaN($("#select_1").val())){
            alert("请选择部门信息")
            return false
        }
    }else {
        if (!$("#select_1").val()||!$("#select_2").val()||isNaN($("#select_2").val())||isNaN($("#select_1").val())){
            alert("请选择部门信息")
            return false
        }
    }
    if (confirm("你确定修改吗？")) {
        $.ajax({
            url: "/api/updateDept",
            data: $("#dept-form").serialize(),
            type: "post",
            dataType: "JSON",
            success: function (obj) {
                alert(obj.message)
                if(obj.state==1){
                    $("#company").html($("#select_1").find("option:selected").text())
                    $("#dept").html($("#select_2").find("option:selected").text())
                    del()
                }
            }
        })
    }
}
//显示修改窗口
function showUpdatePwd(){
    $("#update-pwd").css("display","inline")
}
function showUpdateName(){
    $("#update-name").css("display","inline")
}
function showUpdateDept(){
    $("#update-dept").css("display","inline")
}
function showUpdateHead(){
    $("#update-head").css("display","inline")
}
//关闭修改窗口
function del(){
    $("#update-head").css("display","none")
    $("#update-dept").css("display","none")
    $("#update-name").css("display","none")
    $("#update-pwd").css("display","none")
    $("#tag-div").css("display","none")
    $("#star").css("display","none")
}
//退出登录
function exit(){
    if (confirm("你确定退出么？")) {
        var data=new Date().getTime()-60*60*24*7;
        document.cookie='Authority=null;expires='+new Date(data).toUTCString()+';path=/'
        window.location.reload()
        // window.location="/"
    }
}
/********************nav导航栏的js代码********************/
function showNav(){
    getUserById()
    for (var i = 1; i <= 6; i++) {
        getNav(i)
    }
    $('#search-in').keyup(function (e) {
        if(e.keyCode==13){
            $("#search-a").click()
        }
        var keywords = $(this).val();
        if (keywords == '') {
            $('#search-to').hide();
            return
        }
        $.ajax({
            url: '/api/getTitle?text=' + keywords,
            dataType: 'json',
            beforeSend: function () {
                $('#search-to').append('<div>正在加载。。。</div>');
            },
            success: function (obj) {
                $('#search-to').empty().show();
                if (obj.data == '') {
                    $('#search-to').append('<div class="error">找不到相关帖子</div>');
                }
                $.each(obj.data, function () {
                    $('#search-to').append('<div class="click_work">' + this + '</div>');
                })
            },
            error: function () {
                $('#search-to').empty().show();
                $('#search-to').append('<div class="click_work">出现错误</div>');
            }
        })
    })
    //点击搜索数据复制给搜索框
    $(document).on('click', '.click_work', function () {
        var word = $(this).text();
        $('#search-in').val(word);
        $('#search-to').hide();
        // $('#texe').trigger('click');触发搜索事件
        $("#search-a").click()
    })
    $("#search-a").click(function () {
        location="/api/showTitle?text="+$('#search-in').val()
    })
}
//从showNav抽取的函数
function getNav(i) {
    var data
    var url;
    if (i===1) url= "/api/getByCTime";
    if (i===2) url= "/api/getByOTime";
    if (i===3) url= "/api/getByState";
    if (i===4) url= "/api/getByUser";
    if (i===5) url= "/api/getByContent";
    if (i===6) url= "/api/getByKnow";
    $.ajax({
        url:url,
        type:"get",
        datatype:"json",
        async:false,
        success:function (obj) {
            $("#nav-"+i).html(obj)
            data=obj;
        }
    })
    return data
}
/****************展示列表*********************/
/************发送获取列表请求*****************/
function getList(url,id,pno) {
    if(!pno){
        pno=1
    }
    $.ajax({
        url:url,
        data:"pno="+pno,
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                showList(obj.data,id)
            }
        }
    })
}
/**************展示列表************************/
function showMyPost(){
    getList("/api/getMyPost","#mypost",getUrlParam("pno"))
}
function showMyReply(){
    getList("/api/getMyReply","#myreply",getUrlParam("pno"));
}
function showPostList() {
    var pno=getUrlParam("pno")
    if(!pno){
        pno=1
    }
    $.ajax({
        url:"/api/getPostByModule",
        data:"id="+getUrlParam("id")+"&pno="+pno,
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                showList(obj.data,"#list")
            }
        }
    })
}
function showList(data,id) {
    for (var i = 0; i < data.length; i++) {
        var bt=$('<button class="btn ForumTopic btn-block" style="background-color: rgb(37, 26, 15); color: #fff;"'
            +'onclick="showPost('
            +data[i].pid
            +');">'
            +'<div class="ForumTopic-details">'
            +'<span class="ForumTopic-heading">'
            +'<span class="ForumTopic-title--wrapper">'
            +'<span class="ForumTopic-title" data-toggle="tooltip">'
            + data[i].title
            + '</span>'
            + '</span>'
            + '</span>'
            +'<span class="ForumTopic-author">'
            +data[i].uname
            +'</span>'
            + '<span class="ForumTopic-replies">'
            + '<img src="../image/discuss.png">'
            + '<span>'
            +data[i].num
            +'</span>'
            + '</span>'
            + '<span class="ForumTopic-timestamp">'
            +data[i].create_time
            + '</span>'
            + '<span class="ForumTopic-timestamp">'
            +data[i].end_time
            + '</span>'
            + '</div>'
            +'</button>')
        $(id).append(bt)
    }
}
//展示模块
function showModule() {
    $.ajax({
        url:"/api/getModule",
        data:"id="+getUrlParam("id"),
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                $("#label").html(obj.data.name)
                $("#header").html(obj.data.name)
                $("title").html(obj.data.name)
            }
        }
    })
}
/**************帖子内容**************/
//模块
function getModule(id) {
    $.ajax({
        url : "/api/getModule",
        data : "id=" + id,
        type : "get",
        datatype : "json",
        success : function(obj) {
            if (obj.state == 1) {
                $("#module").html(obj.data.name)
                $("#module").attr("href","/api/showPostList?id="+id)
                $("#nav-labal2 .Navbar-label").html(obj.data.name)
                $("#nav-labal2").attr("href","/api/showPostList?id="+id)
            }
        }
    })
}
//显示内容
function showDetail() {
    $.ajax({
        url : "/api/getPostById",
        data : "id=" + getUrlParam("id"),
        type : "get",
        datatype : "json",
        success : function(obj) {
            if (obj.state == 1) {
                $("#label1").html(obj.data.title)
                $("#header").html(obj.data.title)
                $("title").html(obj.data.title)
                getModule(obj.data.module_id)
                getContentsByPost(obj.data.id)
                if(obj.data.create_user==postGetUser()&&obj.data.state <= 5){
                    $("#Button-over").css("display","block")
                }
                if(obj.data.state >= 5){
                    $("#Button-reply").css("display","none")
                    $("#topic-reply-form").css("display","none")
                    $("#Button-over").css("display","none")
                }else {
                    $("#Button-reply").css("display","flex")
                    $("#topic-reply-form").css("display","flex")
                }
                if (obj.data.lv==2){
                    $("#Button-Top .Button-content").html("取消置顶")
                }else if (obj.data.lv==1){
                    $("#Button-Top .Button-content").html("置顶")
                }
            }
        }
    })

}
function postGetUser() {
    var id;
    $.ajax({
        url:"/api/getUserById",
        type:"get",
        datatype:"json",
        async:false,
        success: function (obj) {
            if(obj.state===1){
                var user=obj.data
                $("#user-name").html(user.name)
                $("#user-head").attr("src",user.head)
                id=user.id
                showDeptByUser(user.dept_id)
            }
        }
    })
    return id;
}
function getContentsByPost(id) {
    $.ajax({
        url : "/api/showContentsByPost",
        data : "id=" + id,
        type : "get",
        datatype : "json",
        success : function(obj) {
            if (obj.state == 1) {
                for (i = 0; i < obj.data.length; i++) {
                    var content=$('<div class="TopicPost"><div class="TopicPost-content"><aside class="TopicPost-author">'
                        +'<div class="Author-block">'
                        +'<div class="Author" id="" data-topic-post-body-content="true">'
                        +'<a class="Author-avatar ">'
                        +'<img src="'
                        +obj.data[i].head
                        +'" alt="" />'
                        +'</a>'
                        +'<div class="Author-details">'
                        +'<span class="Author-name">'
                        +'<a class="Author-name--profileLink">'
                        +obj.data[i].name
                        +'</a>'
                        +'</span>'
                        +'<span class="Author-guild">'
                        +'<a class="Author-guild--link">'
                        +obj.data[i].company
                        +'<br>'
                        +obj.data[i].dept
                        +'</a></span></div></div></div></aside>'
                        +'<div class="TopicPost-body" data-topic-post-body="true">'
                        +'<div class="TopicPost-details">'
                        +'<div class="Timestamp-details">'
                        +'<a class="TopicPost-timestamp">'
                        + obj.data[i].create_time
                        +'</a>'
                        +'</div>'
                        +'</div>'
                        +'<!--回复正文-->'
                        +'<div class="TopicPost-bodyContent" data-topic-post-body-content="true">'
                        +obj.data[i].text
                        +'</div></div></div></div>')
                    $("#post-1").append(content)
                }
            }
        }
    })
}


//上传附件
function uploadFile($file,filename) {
    if($(".acc-v").length>=5){
        alert("最多添加5个附件")
        return
    }
    var formData = new FormData();
    formData.append("file",$file[0].files[0]);
    formData.append("name",filename);
    $.ajax({
        url : "/api/uploadFile",
        type : 'POST',
        data : formData,
        processData : false,
        contentType : false,
        beforeSend:function(){
            //alert("正在进行，请稍候");
        },
        success : function(obj) {
            if(obj.state===1){
                for (var i = 0; i < obj.data.length; i++) {
                    var accessory=obj.data[i];
                    editor.txt.append("<a href='\" + accessory.path + \"' download='\" + accessory.filename + \"' style='display: none;' id='"+accessory.id+"' class='" + accessory.id + "'>" + accessory.filename + "</a>");

                    //editor.txt.append("<iframe id='"+accessory.id+"' src='"+accessory.path+"' download='"+accessory.filename+"'>"+accessory.filename+"</iframe>");
                    $("#accessory").css("display","block")
                    $("#accessory").append("<div class='acc-v "+accessory.id+"'>"+accessory.filename+"<a href='#F' onclick='delFile("+accessory.id+");'><img src='/image/del.png'></a></div>")
                }
            }else{
                alert("上传出错")
            }
        },
        error : function(obj) {
            console.log("error");
        }
    });
}
//上传视频音频
function uploadVideo($file,filename) {
    if (/\.(webp|avi|mp4|3gp|flash|rmvb|wma|mp3)$/i.test(filename) === false) {
        //editor.txt.append("<a id='"+accessory.id+"' href='"+accessory.path+"' download='"+accessory.filename+"'>"+accessory.filename+"</a>");
        alert("文件格式可能不是音频或视频")
        return;
    }
    var formData = new FormData();
    formData.append("file",$file[0].files[0]);
    formData.append("name",filename);
    $.ajax({
        url : "/api/uploadVideo",
        type : 'POST',
        data : formData,
        processData : false,
        contentType : false,
        beforeSend:function(){
            //alert("正在进行，请稍候");
        },
        success : function(obj) {
            if(obj.state===1){
                var video='<video controls="" autoplay="false" name="media">'
                for (var i = 0; i < obj.data.length; i++) {
                    var accessory=obj.data[i];
                    video=video+'<source src="'+accessory.path+'" type="video/'+filename.substring(filename.lastIndexOf('.')+1)+'">'
                }
                video=video+"</video>"
                editor.txt.append(video)
            }else{
                alert("上传出错")
            }
        },
        error : function(obj) {
            console.log("error");
        }
    });
}
//取消一个附件
function delFile(id){

    $.ajax({
        url:"/api/delFile",
        data:"id="+id,
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                $("."+id).remove()
            }
        }
    })
}
//提交回复表单
function submit(){
    var reg=/&nbsp;/g;
    var str=editor.txt.text().replace(reg,"")

    if ($.trim(str)==""&&$(".acc-v").length==0&&editor.txt.html()=="<p><br></p>") {
        alert("请输入内容")
        return
    }

    var reg1=/display: none;/g;
    var text=editor.txt.html().replace(reg1,"display: block;")
    $.ajax({
        url:"/api/addContent",
        data:{id:getUrlParam("id"),text:text},
        type:"post",
        datatype:"json",
        success: function (obj) {
            //alert(obj.message)
            if(obj.state===1){
                $("#post-1").html("")
                getContentsByPost(getUrlParam("id"))
                editor.txt.html("")
                $("#accessory").html('<div style="clear:both;"></div>')
            }
        }
    })
}
//添加新帖子
function addPost(){

    if($("#title").val()==null||$.trim($("#title").val()).length==0){
        alert("请输入标题")
        return
    }
    $.ajax({
        url:"/api/addPost",
        data:{"id":getUrlParam("id"),"title":$("#title").val()},
        type:"post",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                var reg=/&nbsp;/g;
                var str=editor.txt.text().replace(reg,"")
                if($.trim(str)||$(".acc-v").length||editor.txt.html()!="<p><br></p>"){
                    addContent(obj.data.id)
                }
                if($("#tag-span").text()){
                    saveTag(obj.data.id)
                }
                location="/api/showPostDetail?id="+obj.data.id
            }
        }
    })
}
function addContent(id) {
    var reg1=/display: none;/g;
    var text=editor.txt.html().replace(reg1,"display: block;")
    $.ajax({
        url:"/api/addContent",
        data:{"id":id,"text":text},
        type:"post",
        datatype:"json",
        success:function(obj){
        }
    })
}
//取消一个附件
function delFile(id){
    $.ajax({
        url:"/api/delFile",
        data:"id="+id,
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                $("."+id).remove()
            }
        }
    })
}

function limitInit(totalPage,totalRecords,type) {
    var pageNo = getUrlParam('pno');
    if (!pageNo) {
        pageNo = 1;
    }
    //生成分页
    //有些参数是可选的，比如lang，若不传有默认值
    kkpager.generPageHtml({
        pno : pageNo,
        //总页码
        total : totalPage,
        //总数据条数
        totalRecords : totalRecords,
        mode: 'click',
        click : function(n){
            if (getUrlParam("text")){
                location="/api/"+type+"?text="+getUrlParam("text")+"&pno="+n
                return
            }
            if(getUrlParam("id")){
                location="/api/"+type+"?id="+getUrlParam("id")+"&pno="+n
            }else{
                location="/api/"+type+"?pno="+n
            }
        }
    });
}


//展示知识库（未实现）
function showKnow(){
    $.ajax({
        url:"/api/showKnow",
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                getList("/api/showKnow","#list",1)
            }
        }
    })
}
function showAddPag() {
    $("#tag-div").css("display","block")
}
function addTag() {
    if($("#tag-span").text().split(',').length>5){
        alert("标签达到最大值")
        $("#tag-div").css("display","none")
        return false;
    }
    var arr=$("#tag-span").text().split(',')
    for (var i = 0; i < arr.length; i++) {
        if($("#tag-in").val()==arr[i]){
            alert("标签不可重复")
            return false;
        }
    }
    $("#tag-span").append($("#tag-in").val()+",")
    $("#tag-div").css("display","none")
}
function saveTag(id) {
    var arr=$("#tag-span").text().split(',')
    for (var i = 0; i < arr.length-1; i++) {
        $.ajax({
            url:"/api/saveTag?tag="+arr[i]+"&id="+id,
            type:"get",
            datatype:"json",
            success:function(obj){
                if(obj.state==1){

                }
            }
        })
    }
}
function setScore(obj) {
    for (var i = 0; i < obj.innerText; i++) {
        $("#star-ul li")[i].className='star-li-on'
    }
    for (var i = obj.innerText; i < $("#star-ul li").length; i++) {
        $("#star-ul li")[i].className='star-li-off'
    }
}
function saveScore() {
    $.ajax({
        url:"/api/saveScore?id="+getUrlParam("id")+"&score="+$(".star-li-on").length,
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                del()
                $("#Button-over").css("display","none")
                $("#Button-reply").css("display","none")
                $("#topic-reply-form").css("display","none")
            }
        }
    })
}
function showIndex() {
    $.ajax({
        url:"/api/getAllModule",
        type:"get",
        datatype:"json",
        success:function(obj){
            if(obj.state==1){
                for (var i = 0; i < obj.data.length ; i++) {
                    var module=obj.data[i]
                    var val=$('<a href="/api/showPostList?id='
                        +(i+1)
                        +'" class="ForumCard ForumCard--content"> <i '
                        +'class="ForumCard-icon" style="background-image: url(\''
                        + module.image
                        +'\')"></i>'
                        +'<div class="ForumCard-details">'
                        +'<h1 class="ForumCard-heading">'
                        + module.name
                        +'</h1>'
                        +'<span class="ForumCard-description">'
                        + module.message
                        +'</span>\n'
                        +'</div>'
                        +'</a>')
                    $(".ForumCards").append(val)
                }
            }
        }
    })
}
function showTitleList() {
    getList("/api/getTitleList?text="+getUrlParam("text"),"#list",1);
}
function getNumByTitle() {
    var data
    $.ajax({
        url:"/api/getNumByTitle",
        data:"text="+getUrlParam("text"),
        type:"get",
        datatype:"json",
        async:false,
        success:function(obj){
            data=obj
        }
    })
    return data;
}
function setTop() {
    if (confirm("你确定修改帖子吗？")) {
        $.ajax({
            url:"/api/setTop?id="+getUrlParam("id"),
            type:"get",
            datatype:"json",
            async:false,
            success:function(obj){
                showDetail()
            }
        })
    }
}







