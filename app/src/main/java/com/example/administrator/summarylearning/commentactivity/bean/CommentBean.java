package com.example.administrator.summarylearning.commentactivity.bean;

import java.util.List;

/**
 * @Author      LD
 * @Time        2018/12/26 17:37
 * @Describe    评论的每一项内容
 * @Modify
 */
public class CommentBean {

    //第一级评论
    private String commentID;   //该整个评论的id标识        //暂时不写
    private String parentID;    //给谁评论的               //暂时不写
    private UserBean firstFloorUserBean;
    private String content;      //评论内容
    private String time;        //评论日期
    private List<childBean> secondFloorComment;


    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public UserBean getFirstFloorUserBean() {
        return firstFloorUserBean;
    }

    public void setFirstFloorUserBean(UserBean firstFloorUserBean) {
        this.firstFloorUserBean = firstFloorUserBean;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<childBean> getSecondFloorComment() {
        return secondFloorComment;
    }

    public void setSecondFloorComment(List<childBean> secondFloorComment) {
        this.secondFloorComment = secondFloorComment;
    }





    //第一级评论用户的信息
    public static class UserBean{

        private String userId;       //用户ID
        private String heading;       //头像
        private String name;         //用户名

        public UserBean() {
        }

        public UserBean( String name,String heading) {
            this.heading = heading;
            this.name = name;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getHeading() {
            return heading;
        }

        public void setHeading(String heading) {
            this.heading = heading;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    //第二级评论用户的信息
    public static class childBean{

        private UserBean fromUserBean;      //我给下面那个回复              fromUserBean回复toUserBean
        private UserBean toUserBean;        //接受回复
        private String   childContent;      //二级的评论的内容

        public UserBean getFromUserBean() {
            return fromUserBean;
        }

        public void setFromUserBean(UserBean fromUserBean) {
            this.fromUserBean = fromUserBean;
        }

        public UserBean getToUserBean() {
            return toUserBean;
        }

        public void setToUserBean(UserBean toUserBean) {
            this.toUserBean = toUserBean;
        }

        public String getChildContent() {
            return childContent;
        }

        public void setChildContent(String childContent) {
            this.childContent = childContent;
        }
    }

}
