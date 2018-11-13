<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/jsp/header.jsp" />
    <title>TODOタスクの一覧</title>
  </head>
  <body>
    <jsp:include page="/jsp/nav.jsp" />
    <div class="container">
        <c:if test="${message != null}">
          <c:out value="${message}"/>
          <br/>
        </c:if>
      <table class="table table-bordered">
        <tr>
          <th>番号</th>
          <th>タイトル</th>
          <th>タスク内容</th>
          <th>期限</th>
          <th>最終更新</th>
          <th>ユーザID</th>
          <th>状況</th>
          <th>詳細画面へ</th>
          <th>添付ファイル</th>
        </tr>
        <c:forEach items="${todoList}" var="dto">
          <tr>
            <td><c:out value="${dto.id}"/></td>
            <td><c:out value="${dto.title}"/></td>
            <td><c:out value="${dto.task}"/></td>
            <td><fmt:formatDate value="${dto.limitdate}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${dto.lastupdate}" pattern="yyyy-MM-dd"/></td>
            <td><c:out value="${dto.userid}"/></td>
            <td><c:out value="${dto.label}"/></td>
            <td><a href="detail?id=<c:out value="${dto.id}"/>">詳細画面へ</a></td>
            <td></td>
          </tr>
        </c:forEach>
      </table>
    </div>
    <div class="container">
      <form id="inout" action="input" method="GET">
        <input type="submit" class="btn btn-success" value="タスクの新規登録"/>
      </form>
    </div>
  </body>
</html>
