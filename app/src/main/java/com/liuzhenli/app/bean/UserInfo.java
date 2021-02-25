package com.liuzhenli.app.bean;

import com.liuzhenli.app.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/25 11:05 PM
 */
public class UserInfo extends BaseBean {
    /**
     * data : null
     */

    public User data;

    /**
     * data : {"admin":false,"chapterTops":[],"collectIds":[],"email":"","icon":"","id":4384,"nickname":"yootoo","password":"","publicName":"yootoo","token":"","type":0,"username":"yootoo"}
     */

    public static class User implements Serializable {
        /**
         * admin : false
         * chapterTops : []
         * collectIds : []
         * email :
         * icon :
         * id : 4384
         * nickname : yootoo
         * password :
         * publicName : yootoo
         * token :
         * type : 0
         * username : yootoo
         */

        public boolean admin;
        public String email;
        public String icon;
        public int id;
        public String nickname;
        public String password;
        public String publicName;
        public String token;
        public int type;
        public String username;
        public List<?> chapterTops;
        public List<Integer> collectIds;
    }

}
