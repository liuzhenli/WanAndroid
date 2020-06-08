package com.liuzhenli.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * describe:置顶文章
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/5 4:27 PM
 */
public class TopArticleList implements Serializable {

    /**
     * data : [{"apkLink":"","audit":1,"author":"扔物线","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12554,"link":"https://www.bilibili.com/video/BV1xf4y127Ur","niceDate":"刚刚","niceShareDate":"2020-03-23 16:36","origin":"","prefix":"","projectLink":"","publishTime":1596297600000,"selfVisible":0,"shareDate":1584952597000,"shareUser":"","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"「内存抖动」？别再吓唬面试者们了行吗","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":13115,"link":"https://mp.weixin.qq.com/s/49uYCxYBBh_WpMc-70WdjA","niceDate":"刚刚","niceShareDate":"2020-04-27 19:14","origin":"","prefix":"","projectLink":"","publishTime":1593619200000,"selfVisible":0,"shareDate":1587986043000,"shareUser":"享学","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"37岁老码农现身说法，走这3条路，薪资跨越30k！","type":1,"userId":33529,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":13397,"link":"https://wanandroid.com/blog/show/2754","niceDate":"刚刚","niceShareDate":"2020-05-13 17:42","origin":"","prefix":"","projectLink":"","publishTime":1591372800000,"selfVisible":0,"shareDate":1589362962000,"shareUser":"","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"2020年Android更新面试专题助你斩获offer，以及教你如何一步步简历","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>大家在开发工程中，有没有发现，有的自定义控件可以正常预览，有的就不行？<\/p>\r\n<p>那么针对这个情况，问题来了：<\/p>\r\n<ol>\r\n<li>自定义控件是如何做到预览的，AS 中我们的 View 的代码执行了？<\/li>\r\n<li>有些自定义控件不能预览的原因是？我们该怎么做，让其尽可能做到可预览状态？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":13701,"link":"https://www.wanandroid.com/wenda/show/13701","niceDate":"2020-06-01 00:54","niceShareDate":"2020-06-01 00:54","origin":"","prefix":"","projectLink":"","publishTime":1590944098000,"selfVisible":0,"shareDate":1590944069000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"本站发布","url":"/article/list/0?cid=440"},{"name":"问答","url":"/wenda"}],"title":"每日一问 | 自定义控件无法预览该怎么办？","type":1,"userId":2,"visible":1,"zan":3}]
     * errorCode : 0
     * errorMsg :
     */

    public int errorCode;
    public String errorMsg;
    public List<ArticleBean> data;
}
