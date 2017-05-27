package cn.springlogic.comment.jpa.entity;

import cn.springlogic.blog.jpa.entity.Article;
import cn.springlogic.user.jpa.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by admin on 2017/4/25.
 */
@Entity(name = "Comment")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String summary;

    @Column(name="create_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 如果以后评论可以多媒体格式,用该实体存储.
     * multimedia_content
     */
    @ManyToOne(fetch=FetchType.LAZY,  // 指定属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity=Article.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name="multimedia_content_id", // 外键列的列名
            referencedColumnName="id") // 指定引用表的主键列
    private Article multimediaArticle;


    /** 评论是多的一端 (多个评论对应一个用户)*/
    @ManyToOne(fetch= FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity=User.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name="user_id", // 外键列的列名
            referencedColumnName="id") // 指定引用user表的主键列
    @NotNull
    private User user;

    @ManyToOne(fetch=FetchType.LAZY,  // 指定属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity=Comment.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name="reply_comment_id", // 外键列的列名
            referencedColumnName="id") // 指定引用表的主键列
    private Comment replyComment;

    /**
     * 评论内容
     */
    private String content;
}
