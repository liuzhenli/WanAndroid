package com.liuzhenli.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/5 4:26 PM
 */
public class ArticleBean implements Serializable {
    /**
     * apkLink :
     * audit : 1
     * author :
     * canEdit : false
     * chapterId : 502
     * chapterName : 自助
     * collect : false
     * courseId : 13
     * desc :
     * descMd :
     * envelopePic :
     * fresh : true
     * id : 13584
     * link : https://juejin.im/post/5ec6832d51882542f71873b8
     * niceDate : 23小时前
     * niceShareDate : 1天前
     * origin :
     * prefix :
     * projectLink :
     * publishTime : 1590204816000
     * selfVisible : 0
     * shareDate : 1590143941000
     * shareUser : 哈利迪
     * superChapterId : 494
     * superChapterName : 广场Tab
     * tags : []
     * title : Android | Tangram动态页面之路（七）硬核的Virtualview
     * type : 0
     * userId : 6999
     * visible : 1
     * zan : 0
     */

    public String apkLink;
    public int audit;
    public String author;
    public boolean canEdit;
    public int chapterId;
    public String chapterName;
    public boolean collect;
    public int courseId;
    public String desc;
    public String descMd;
    public String envelopePic;
    public boolean fresh;
    public int id;
    public String link;
    public String niceDate;
    public String niceShareDate;
    public String origin;
    public String prefix;
    public String projectLink;
    public long publishTime;
    public int selfVisible;
    public long shareDate;
    public String shareUser;
    public int superChapterId;
    public String superChapterName;
    public String title;
    public int type;
    public int userId;
    public int visible;
    public int zan;
    /***2banner 1置顶   0最新*/
    public int itemType;
    public List<Tag> tags;

    public static class Tag {
        String name;
        String url;
    }
}