<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/jsp/header.jsp" />
      <style type="text/css">
        body {
          padding-top: 60px;
        }
      </style>
    <title>TODOタスク管理アプリケーション</title>
  </head>
  <body>
    <div class="container">
      <a href="/todo/search" class="btn btn-success">タスク一覧の参照</a>
    </div>
  </body>
</html>
