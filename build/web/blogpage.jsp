<%-- 
    Document   : blogpage
    Created on : Jan 12, 2016, 9:32:21 AM
    Author     : codeguy
--%>

<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            int blogid = 0;
            if (request.getParameter("blog") == null) {
                response.sendRedirect("our_blog.jsp");
            } else {
                blogid = Integer.parseInt(request.getParameter("blog"));
            }
            Session s = Connection.Connector.getSessionFactory().openSession();
            POJOs.Blog myBlog = (POJOs.Blog) s.createCriteria(POJOs.Blog.class).add(Restrictions.eq("idblog", blogid)).uniqueResult();

            if (request.getSession().getAttribute("user") != null) {
                if (request.getSession().getAttribute("blogSession") != null) {
                    Vector<Integer> viewedBlogs = (Vector<Integer>) request.getSession().getAttribute("blogSession");
                    boolean isViewed = false;
                    for (Integer b : viewedBlogs) {
                        if (blogid == b) {
                            isViewed = true;
                        }
                    }

                    if (!isViewed) {
                        viewedBlogs.add(blogid);
                        int cBlogviews = Integer.parseInt(myBlog.getBlogViews());
                        cBlogviews += 1;
                        myBlog.setBlogViews(cBlogviews + "");
                        s.update(myBlog);
                        s.beginTransaction().commit();
                    }
                } else {
                    Vector<Integer> viewBlog = new Vector<Integer>();
                    viewBlog.add(blogid);
                    request.getSession().setAttribute("blogSession", viewBlog);
                    int cBlogviews = Integer.parseInt(myBlog.getBlogViews());
                    cBlogviews += 1;
                    myBlog.setBlogViews(cBlogviews + "");
                    s.update(myBlog);
                    s.beginTransaction().commit();
                }
            }
        %>
        <title><%=myBlog.getBlogTitle()%></title>
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css"/>

        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <script>
            var comment = 0;
            function changeSelectedComment(commentid) {
                comment = commentid;
            }
            function getSelectedComment() {
                document.getElementById('txtblogcomment').value = comment;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <ul class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#example-navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="" class="navbar-brand">OnlineDoctor!</a>
                </div>

                <div class="collapse navbar-collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li ><a href="homepage.jsp">Home</a></li>
                        <li><a href="virtual_doc.jsp">Ask Virtual Doctor</a></li>
                        <li><a href="pharmacy_service.jsp">Pharmacy Services</a></li>
                        <li><a href="medical_services.jsp">Medical Services</a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">More Stuff...   <span class="glyphicon glyphicon-triangle-bottom"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="our_blog.jsp">Our Blog</a></li>
                                <li><a href="login_service.jsp">Your Account!</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </ul>

            <h1 style="margin-top: 60px; margin-bottom: 30px;"><%=myBlog.getBlogTitle()%> <small>Post created on <strong><%=myBlog.getBlogCreation()%></strong></small></h1>
            <%
                Iterator<POJOs.BlogImages> blogimages = myBlog.getBlogImageses().iterator();
                List<POJOs.BlogImages> imageList = s.createCriteria(POJOs.BlogImages.class).list();

                imageList.clear();
                while (blogimages.hasNext()) {
                    imageList.add(blogimages.next());
                }
            %>
            <div id="myCarousel" class="carousel slide">
                <ol class="carousel-indicators">
                    <%
                        int counter = 0;
                        for (POJOs.BlogImages bi : imageList) {%>

                    <%
                        if (counter == 0) {%>
                    <li data-target="#myCarousel" data-slide-to="<%=counter%>" class="active"></li>
                        <%} else {%>
                    <li data-target="#myCarousel" data-slide-to="<%=counter%>"></li>                                
                        <%}
                        %>                                
                        <%counter += 1;
                            }
                        %>
                </ol>
                <div class="carousel-inner">
                    <%
                        counter = 0;
                        for (POJOs.BlogImages bi : imageList) {%>
                    <%
                        if (counter == 0) {%>
                    <div class="item active">
                        <img src="<%=bi.getBlogImage()%>" alt="Blog Slide" />
                    </div>
                    <%} else {%>
                    <div class="item">
                        <img src="<%=bi.getBlogImage()%>" alt="Blog Slide" />
                    </div>
                    <%}
                    %>
                    <%counter += 1;
                        }
                    %>
                </div>
                <a class="carousel-control left" href="#myCarousel"
                   data-slide="prev">&lsaquo;</a>
                <a class="carousel-control right" href="#myCarousel"
                   data-slide="next">&rsaquo;</a>
            </div> <!-- end of carousel -->           


            <div class="well" style="margin-top: 60px;">
                <h4>Total Views on Blog :  <strong><%=myBlog.getBlogViews()%></strong> Views</h4>
                <p><%=myBlog.getBlogContent()%></p>
            </div> <!-- end of blog description -->

            <h3>Comments</h3>
            <%
                if (request.getSession().getAttribute("user") != null) {%>
            <form method="POST" action="handleBlogCommentAdd">
                <div class="input-group" style="margin-bottom: 50px;">
                    <input type="text" class="form-control" placeholder="Your Comment Here" name="comment"/>
                    <input type="hidden" value="<%=blogid%>" name="blogid" />
                    <span class="input-group-btn">
                        <button class="btn btn-primary">
                            <span class="glyphicon glyphicon-comment"></span>
                        </button>
                    </span>
                </div><!-- /input-group -->
            </form>
            <%}%>
            <%
                Iterator<POJOs.BlogCaptions> blogComments = myBlog.getBlogCaptionses().iterator();
                while (blogComments.hasNext()) {
                    POJOs.BlogCaptions cComment = blogComments.next();
                    POJOs.User commentUser = cComment.getUser();
                    String initals = "";
                    String fname = "", mname = "", lname = "";
                    if (commentUser.getUsertype().equals("ADMINISTRATOR")) {
                        fname = commentUser.getAdministrator().getAdminFname();
                        mname = commentUser.getAdministrator().getAdminMname();
                        lname = commentUser.getAdministrator().getAdminLname();
                        initals += fname.split("")[0];
                        initals += mname.split("")[0];
                        initals += lname.split("")[0];
                    } else if (commentUser.getUsertype().equals("DOCTOR")) {
                        fname = commentUser.getDoctor().getDoctorFname();
                        mname = commentUser.getDoctor().getDoctorMname();
                        lname = commentUser.getDoctor().getDoctorLname();
                        initals += fname.split("")[0];
                        initals += mname.split("")[0];
                        initals += lname.split("")[0];
                    } else if (commentUser.getUsertype().equals("CUSTOMER")) {
                        fname = commentUser.getPatient().getPatientFname();
                        mname = commentUser.getPatient().getPatientMname();
                        lname = commentUser.getPatient().getPatientLname();
                        // initals += fname.split("")[0];
                        initals += mname.split("")[0];
                        initals += lname.split("")[0];

                    }

                    initals = initals.toUpperCase();
            %>

            <div class="media">
                <a class="pull-left" href="#">
                    <h4><%=initals%></h4>
                </a>
                <div class="media-body">
                    <h4 class="media-heading"><%=mname + " " + lname%></h4>
                    <p><%=cComment.getComment()%></p>
                    <hr>
                    <%
                        Iterator<POJOs.BlogReply> iReplies = cComment.getBlogReplies().iterator();
                        while (iReplies.hasNext()) {
                            POJOs.BlogReply cReply = iReplies.next();
                            POJOs.User replyUser = cReply.getUser();
                            String replyInitals = "";

                            if (replyUser.getUsertype().equals("ADMINISTRATOR")) {
                                fname = replyUser.getAdministrator().getAdminFname();
                                mname = replyUser.getAdministrator().getAdminMname();
                                lname = replyUser.getAdministrator().getAdminLname();
                                replyInitals += fname.split("")[0];
                                replyInitals += mname.split("")[0];
                                replyInitals += lname.split("")[0];
                            } else if (replyUser.getUsertype().equals("DOCTOR")) {
                                fname = replyUser.getDoctor().getDoctorFname();
                                mname = replyUser.getDoctor().getDoctorMname();
                                lname = replyUser.getDoctor().getDoctorLname();
                                replyInitals += fname.split("")[0];
                                replyInitals += mname.split("")[0];
                                replyInitals += lname.split("")[0];
                            } else if (replyUser.getUsertype().equals("CUSTOMER")) {
                                fname = replyUser.getPatient().getPatientFname();
                                mname = replyUser.getPatient().getPatientMname();
                                lname = replyUser.getPatient().getPatientLname();
                                // initals += fname.split("")[0];
                                replyInitals += mname.split("")[0];
                                replyInitals += lname.split("")[0];
                            }
                    %>

                    <div class="media">
                        <a class="pull-left" href="#">
                            <h4><%=replyInitals%></h4>
                        </a>
                        <div class="media-body">
                            <h6 class="media-heading"><%=mname + " " + lname%></h6>
                            <p><%=cReply.getReply()%></p>
                        </div>
                    </div>

                    <%}
                    %>
                </div>
                <%if (request.getSession().getAttribute("user") != null) {%>
                <a class="pull-right" data-target="#myModal" data-toggle="modal" onclick="changeSelectedComment(<%=cComment.getIdblogCaptions()%>)">
                    <span class="glyphicon glyphicon-retweet"></span>
                </a>
                <%
                    POJOs.User loggedUser = (POJOs.User) request.getSession().getAttribute("user");
                    if (loggedUser.getUsername().equals(cComment.getUser().getUsername())) {%>

                <a class="pull-right" href="handleDeleteComment?comment=<%=cComment.getIdblogCaptions()%>">
                    <span class="glyphicon glyphicon-remove"></span>
                </a>

                <%}
                %>
                <%}%>
            </div>

            <%
                }
            %>
        </div> <!-- end of container -->

        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true" onshow="getSelectedComment()">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            Reply to comment
                        </h4>
                    </div>
                    <form method="POST" action="handleCommentRelyAdd">
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Reply Comment : </label>
                                <input type="hidden" name="cblog" value="<%=myBlog.getIdblog()%>" />
                                <input type="hidden" id="txtblogcomment" name="blog"/>
                                <input type="text" class="form-control" name="reply" />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">Close
                            </button>
                            <button type="submit" class="btn btn-primary">
                                Reply
                            </button>
                        </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </body>
</html>
