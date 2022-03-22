# SpringBoot个人博客项目
项目原型是b站李仁密老师的Spring Boot开发小而美的个人博客，持久层改用了mybatis。

博客地址：http://47.107.230.178:8081/      或者（2022/4/11之后可以访问）www.zouyt.xyz
 ## 项目的技术栈：
  - 前端UI：Semantic UI框架 + thymeleaf模板
  - 后端：Spring Boot + mybatis
  - 数据库：MySQL
 ## 项目环境
  - jdk1.8
  - Maven 3.8.1
  - SpringBoot 2.6.0
  - MySQL 8.0.17
 ## 项目需求

<img src="https://gitee.com/zou_yt/image/raw/master/img/个人博客系统思维导图.png" style="zoom: 80%;">
 ## 数据库构建
  ### 数据库模型

<img src="https://gitee.com/zou_yt/image/raw/master/img/个人博客项目数据库模型.png" style="zoom: 80%;">

 - t_blog博客对象
 ```java
    private Long id;    //博客id
    private String title;   //博客题目
    private String content; //博客内容
    private String firstPicture;     //博客首图
    private String flag;    //博客标记
    private Integer views;  //浏览次数
    private boolean appreciation;   //赞赏功能是否开启
    private boolean shareStatement; //转载声明是否开启
    private boolean commentabled;   //评论是否开启
    private boolean published;  //是否发布
    private boolean recommend; //是否推荐
    private Timestamp createTime;    //创建时间
    private Timestamp updateTime;    //更新时间
    private String description;     //博客描述
    private String tagIds;      //前端传来的标签组id
    private Type type;
    private List<Tag> tags = new ArrayList<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();
 ```
 - t_type分类对象
 ```java
    private Long id;    //分类id
    private String name;    //分类名
    private List<Blog> blogs = new ArrayList<>();
 ```
  - t_tag标签对象
 ```java
    private Long id;    //标签id
    private String name;    //标签名
    private List<Blog> blogs = new ArrayList<>();
 ```
 - t_comment评论对象
 ```java
    private Long id;    //评论id
    private String nickname;    //昵称
    private String email;   //邮箱
    private String content; //评论内容
    private String avatar;  //头像
    private Timestamp createTime;    //创建时间
    private Long blogId;    //评论所在的博客的id
    private Boolean adminComment;   //是否是管理员评论
    private Blog blog;
    private List<Comment> replyComments = new ArrayList<>();    //多个子类评论
    private Comment parentComment;  //父类评论
 ```
 - t_blog_tag中间表对象
 ```java
   private Long blogId;   //博客id
   private Long tagId;  //标签id
 ```
 - t_user用户表对象
 ```java
    private Long id;    //用户id
    private String nickname;    //昵称
    private String username;    //用户名
    private String password;    //密码
    private String email;   //邮箱
    private String avatar;  //头像
    private Integer type;   //用户类型
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
    private List<Blog> blogs = new ArrayList<>();
 ```
