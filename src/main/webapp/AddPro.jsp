<%@include file="db.jsp" %>
	
<%
String name=request.getParameter("name");
String p=request.getParameter("price");
int price=Integer.parseInt(p);
String cat=request.getParameter("cat");
String cmp=request.getParameter("cmp");
String img=request.getParameter("img");

String qr="insert into product2 values(?,?,?,?,?)";
PreparedStatement ps=con.prepareStatement(qr);
ps.setString(1, name);
ps.setInt(2, price);
ps.setString(3, cat);
ps.setString(4, cmp);
ps.setString(5, img);


int i=ps.executeUpdate();
con.close();
RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
rd.include(request,response);

%>