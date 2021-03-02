<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<script type="text/javascript">
   	$(document).ready(function(){
      $("#writeForm").submit(function(event){         
           event.preventDefault();
           var bName = $("#bName").val();
           var bTitle = $("#bTitle").val();
           var bContent = $("#bContent").val();           
           
           console.log(bName);
           console.log(bTitle);
           console.log(bContent);
           console.log($(this).attr("action"));    
           
           var form = {
                 bName: bName,
                 bTitle: bTitle,
                 bContent: bContent
           };

           $.ajax({
             type : "PUT",
             url : $(this).attr("action"),
             cache : false,
             contentType:'application/json; charset=utf-8',
             data: JSON.stringify(form),
             success: function (result) {       
               if(result == "SUCCESS"){     
                  $(location).attr('href', '${pageContext.request.contextPath}/restful/board/')                            
               }                       
             },
             error: function (e) {
                 console.log(e);
             }
         })            
       });       
   	});
	</script>
</head>
<body>
<table width="500" cellpadding="0" cellspacing="0" border="1">
      <form id="writeForm" action="${pageContext.request.contextPath}/restful/write" method="post">
         <tr>
            <td> 이름 </td>
            <td> <input type="text" name="bName" size = "50"> </td>
         </tr>
         <tr>
            <td> 제목 </td>
            <td> <input type="text" name="bTitle" size = "50"> </td>
         </tr>
         <tr>
            <td> 내용 </td>
            <td> <textarea name="bContent" rows="10" ></textarea> </td>
         </tr>
         <tr >
            <td colspan="2"> <input type="submit" value="작성"> &nbsp;&nbsp; <a href="${pageContext.request.contextPath}/restful/board">목록보기</a></td>
         </tr>
      </form>
   </table>
   
</body>
</html>