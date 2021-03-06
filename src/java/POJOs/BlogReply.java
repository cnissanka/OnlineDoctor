package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1



/**
 * BlogReply generated by hbm2java
 */
public class BlogReply  implements java.io.Serializable {


     private int idblogReply;
     private BlogCaptions blogCaptions;
     private User user;
     private String reply;

    public BlogReply() {
    }

	
    public BlogReply(int idblogReply, BlogCaptions blogCaptions, User user) {
        this.idblogReply = idblogReply;
        this.blogCaptions = blogCaptions;
        this.user = user;
    }
    public BlogReply(int idblogReply, BlogCaptions blogCaptions, User user, String reply) {
       this.idblogReply = idblogReply;
       this.blogCaptions = blogCaptions;
       this.user = user;
       this.reply = reply;
    }
   
    public int getIdblogReply() {
        return this.idblogReply;
    }
    
    public void setIdblogReply(int idblogReply) {
        this.idblogReply = idblogReply;
    }
    public BlogCaptions getBlogCaptions() {
        return this.blogCaptions;
    }
    
    public void setBlogCaptions(BlogCaptions blogCaptions) {
        this.blogCaptions = blogCaptions;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getReply() {
        return this.reply;
    }
    
    public void setReply(String reply) {
        this.reply = reply;
    }




}


