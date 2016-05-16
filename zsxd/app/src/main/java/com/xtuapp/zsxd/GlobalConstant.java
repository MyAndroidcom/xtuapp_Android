package com.xtuapp.zsxd;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class GlobalConstant {
    public static class Url{
        public static final String SERVER_URL = "http://192.168.191.1:8080";
        public static final String SPLASH_URL = SERVER_URL+"/splash.jpg";

        public static final String LOGIN_URL = "/xtuapp/api/v1/tokens/%s";
}
    public static class Conf{
        public static final int PHOTO_SUM = 10;
    }
    public  static class Constant{
        public static final String[] TOPIC_MENUS = {"发帖","热门","精华","直播"};
        public static final String KEY_PASS = "asjiofjpweg@jk2we.rk,jh";

    }
    public static class Pref{
        public static final String IS_FIRST = "isFirst";
    }

    public static class Code{
        public static final int OK = 200; // 服务器成功返回用户请求的数据
        public static final int CREATED = 201; // 用户新建或修改数据成功
        public static final int ACCEPTED = 202; // 表示一个请求已经进入后台排队
        public static final int NO_CONTENT = 204; // 用户删除数据成功
        public static final int INVALID_REQUEST = 400; // 用户发出的请求有错误，服务器没有进行新建或修改数据的操作
        public static final int UNAUTHORIZED = 401; // 表示用户没有权限（令牌、用户名、密码错误）
        public static final int FORBIDDEN = 403; // 表示用户得到授权（与401错误相对），但是访问是被禁止的
        public static final int NOT_FOUND = 404; // 用户发出的请求针对的是不存在的记录，服务器没有进行操作
        public static final int NOT_ACCEPTABLE = 406; // 用户请求的格式不可得
        public static final int GONE = 410; // 用户请求的资源被永久删除，且不会再得到的
        public static final int UNPROCESABLE_ENTITY = 422; // [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误
        public static final int INTERNAL_SERVER_ERROR = 500; // 服务器发生错误，用户将无法判断发出的请求是否成功
    }
    public static class Tip {
        public static final String INTERNAL_SERVER_ERROR = "服务器发生异常";
        public static final String USERNAME_IS_NULL = "用户名不能为空!";
        public static final String USERNAME_IS_INVALID = "用户名格式有误!";
        public static final String PASSWORD_IS_NULL = "密码不能为空!";
        public static final String PASSWORD_IS_INVALID = "密码格式有误!";
        public static final String ILLEGAL_REQUEST = "请求非法!";
        public static final String EMAIL_EXIST = "该邮箱已被注册";
        public static final String USERNAME_OR_PASSWORD_IS_WRONG = "用户名或密码有误";
        public static final String JWGL_ERROR = "教务管理系统异常";
        public static final String VERIFYCODE_IS_WRONG = "验证码有误";
        public static final String UNAUTHORIZED = "没有权限";
        public static final String PARAMETERS_IS_WRONG = "请求参数有误";
    }

}
