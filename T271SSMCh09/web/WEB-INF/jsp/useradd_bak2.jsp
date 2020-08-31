<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">
    span{
        color: red;
        font-size: 20px;
    }
</style>
<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <fm:form method="post" modelAttribute="user" enctype="multipart/form-data">
                用户编码：<fm:input path="userCode" /><fm:errors path="userCode" /><br/><%--path：对象的属性名--%>
                用户名称：<fm:input path="userName"/><fm:errors path="userName" /><br/>
                用户密码：<fm:password path="userPassword"/><fm:errors path="userPassword" /><br/>
                用户生日：<fm:input path="birthday"/><fm:errors path="birthday" /><br/>
                用户地址：<fm:input path="address"/><br/>
                联系电话：<fm:input path="phone"/><br/>
                用户角色：<fm:radiobutton path="userRole" value="1" />系统管理员
                <fm:radiobutton path="userRole" value="2" />经理
                <fm:radiobutton path="userRole" value="3" />普通员工<br/>
                证件照：<input type="file" name="attachs" id="a_idPicPath"/>
                工作照：<input type="file" name="attachs" id="a_idPicPath"/>
                <input type="submit" value="保存" />
            </fm:form>
        </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/useradd.js"></script>
