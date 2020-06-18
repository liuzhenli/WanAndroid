package com.liuzhenli.app.bean;

import com.liuzhenli.app.base.BaseBean;

import java.util.List;

/**
 * describe:项目分类
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/14 4:12 PM
 */
public class ProjectTreeBean extends BaseBean {
    public List<ProjectTree> data;

    public static class ProjectTree {
        /**
         * children : []
         * courseId : 13
         * id : 294
         * name : 完整项目
         * order : 145000
         * parentChapterId : 293
         * userControlSetTop : false
         * visible : 0
         */

        public int courseId;
        public int id;
        public String name;
        public int order;
        public int parentChapterId;
        public boolean userControlSetTop;
        public int visible;
        public List<?> children;
    }
}
