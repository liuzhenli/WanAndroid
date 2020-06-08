package com.liuzhenli.app.bean;

import com.liuzhenli.app.base.BaseBean;

import java.util.List;

/**
 * describe:公众号列表
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/26 7:50 AM
 */
public class ArticleChapters extends BaseBean {

    public List<Module> data;

    public static class Module {
        /**
         * children : []
         * courseId : 13
         * id : 408
         * name : 鸿洋
         * order : 190000
         * parentChapterId : 407
         * userControlSetTop : false
         * visible : 1
         */

        public int courseId;
        public String id;
        public String name;
        public int order;
        public int parentChapterId;
        public boolean userControlSetTop;
        public int visible;
        public List<?> children;
    }
}
