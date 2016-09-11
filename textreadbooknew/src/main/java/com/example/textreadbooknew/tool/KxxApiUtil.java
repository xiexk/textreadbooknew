package com.example.textreadbooknew.tool;

/**
 * @author chenjh
 * @explain 开心学接口请求地址
 * @time 16/6/12 下午4:21.
 */
public class KxxApiUtil {

    private static KxxApiUtil uniqueInstance = null;

    private KxxApiUtil() {
    }

    public static KxxApiUtil getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new KxxApiUtil();
        }
        return uniqueInstance;
    }

    public static String authToken = "";


    /**
     * 服务器请求地址
     */
  //  public static String serverUrl = "http://www.kaixinxue.cn:8080/api/";//测试
    public static String serverUrl = "http://api.kaixinxue.cn/";//正式

    public static String TOKEN = "lzc@13696892000";// 正式
    public static String VERSION = "160812";// 正式
    // 分享地址
    public static String ShareUrl = "http://www.kaixinxue.cn/app/share.php";

    public static String UPDATA_PATH = "/KXX/updata";
    public static String BOOK_CASE_DB_NAME = "kxx_read";
    public static String BOOK_DOWNLOAD_PATH = "/KXX/book_download/";
    public static String BOOK_IMAGE_PATH = "/KXX/book_download";
    public static String FILE_SAVEPATH = SdCardUtils.getExternalSdCardPath() + "/KXX/Portrait/";

    public static String ARTCLE_DOWNLOAD_PATH = "http://l.kaixinxue.cn/uploads/appuploads/kxxbookarticlezip/";
    public static String CHECK_UPDATA = "http://update.kaixinxue.cn/app/kxx/MobileAppVersion1.xml";

    public static int TIME_OUT = 6000;
    public static int DB_VERSION = 2;

    //微信appid 和 screid
    public static final String WX_APP_ID = "wxb62d081a9c8340db";
    public static final String screid = "8e47e39a94a2ee8726e1af0381cf993f";
    //小米
    public static Long xm_clientId = 2882303761517159159L;
    public static String xm_redirectUri = "https://account.xiaomi.com";

    public static String LOGIN_BY_SMS = serverUrl + "sys/auth/account/login/sms";
    public static String LOGIN_BY_PWD = serverUrl + "sys/auth/account/login/pwd";
    public static String LOGIN_BY_QQ = serverUrl + "sys/auth/account/thirdLogin/qq";
    public static String LOGIN_BY_WX = serverUrl + "sys/auth/account/thirdLogin/weixin";
    public static String LOGIN_BY_XM = serverUrl + "sys/auth/account/thirdLogin/xiaomi";
    public static String LOGIN_BY_SN = serverUrl + "sys/auth/account/thirdLogin/sina";

    public static String UPDATE_USER_INFO = serverUrl + "main/member/info/update";//修改用户信息
    /**
     * 修改用户头像
     */
    public static String UPDATE_USER_PORTRAIT = serverUrl + "main/member/info/update/portrait";
    /**
     * 修改用户手机
     */
    public static String UPDATE_USER_MOBILE = serverUrl + "main/member/info/update/mobile";
    /**
     * 获取用户详细信息
     */
    public static String USER_INFO_DETAIL = serverUrl + "main/member/info/detail";
    /**
     * 获取用户基本信息
     */
    public static String USER_INFO_BASIC = serverUrl + "main/member/info/basic";
    /**
     * 获取用户经常变动的信息(果壳、经验值)
     */
    public static String USER_INFO_DYNAMIC = serverUrl + "main/member/info/dynamic";

    /**
     * 获取笔记列表
     */
    public static String BOOKNOTE_LIST = serverUrl + "main/member/booknote/list";

    /**
     * 添加笔记
     */
    public static String BOOKNOTE_SAVE = serverUrl + "main/member/booknote/save";
    /**
     * 获取笔记详情
     */
    private static String BOOKNOTE_DETAIL = serverUrl + "main/member/booknote/";
    public static String get_BOOKNOTE_DETAIL(String _id){
        return BOOKNOTE_DETAIL+_id;
    }
    /**
     * 删除笔记
     */
    private static String BOOKNOTE_DELETE = serverUrl + "main/member/booknote/remove/";
    public static String get_BOOKNOTE_DELETE(String _id){
        return BOOKNOTE_DELETE+_id;
    }
   ;
    /**
     * 获取用户书本收藏列表
     */
    public static String BUERBOOKLIST = serverUrl + "main/member/content/book/list";
    /**
     * 获取书本列表
     */
    public static String BOOK_LIST = serverUrl + "main/content/book/list";
    /**
     * 删除书本
     */
    public static String BOOK_CANCEL = serverUrl + "main/member/content/book/cancel";
    /**
     * 收藏书本
     */
    public static String BOOK_COLLECTION = serverUrl + "main/member/content/book/collect";
    /**
     * 阅读书本
     */
    public static String TASK_READBOOK = serverUrl + "main/content/book/read/";

    public static String taskREAD_READBOOK(String _id) {
        return TASK_READBOOK + _id;
    }

    /**
     * 获取书本详情
     */
    private static String BOOK_INFO = serverUrl + "main/content/book/";

    public static String getBOOK_INFOUrl(String _id) {
        return BOOK_INFO + _id;
    }

    /**
     * 获取书本目录
     */
    private static String BOOK_CATALOG = serverUrl + "main/content/book/catalog/";

    public static String getBOOK_CATALOG(String _id) {
        return BOOK_CATALOG + _id;
    }

    /**
     * 获取书本标签
     */
    public static String BOOK_TAGS = serverUrl + "sys/res/config/book/subjectTags";

    /**
     * 获取章节知识点详情
     */
    private static String ARTICLE_KNOWLEDGE = serverUrl + "main/content/book/article/";

    public static String getARTICLE_KNOWLEDGE(String _id) {
        return ARTICLE_KNOWLEDGE + _id + "/knowledge";
    }

    /**
     * 搜索知识点
     */
    public static String SEARCH_KNOWLEDGE = serverUrl + "main/content/find/knowledge";
    /**
     * 搜索题目
     */
    public static String SEARCH_TOPIC = serverUrl + "main/content/find/topic";
    /**
     * 搜索书本
     */
    public static String SEARCH_BOOK = serverUrl + "main/content/find/book";
    /**
     * 获取章节知识点详情(题目)
     */
    private static String ARTICLE_TOPIC = serverUrl + "main/content/book/article/";

    public static String getARTICLE_TOPIC(String _id) {
        return ARTICLE_TOPIC + _id + "/topic";
    }

    /**
     * 获取广告列表
     */
    public static String APADVERT_LIST = serverUrl + "main/operation/appadvert/list";
    /**
     * 获取学校列表
     */
    public static String SCHOOL_LIST = serverUrl + "sys/res/config/schools";

    /**
     * 退出登入
     */
    public static String LOGOUT = serverUrl + "sys/auth/account/logout";


    /**
     * 用户回复系统消息(新)
     */
    public static String FEEDBACK = serverUrl + "main/operation/feedback/save";

    /**
     * 用户回复系统消息(老)
     */
    public static String FEEDBACK_OL = "http://service.kaixinxue.cn:8080/kxx/UserReport_feedbackNotice.action";

    /**
     * 用户反馈（设置界面）老接口
     */
    public static String FEEDBACK_OLD = "http://service.kaixinxue.cn:8080/kxx/UserReport_UserFeedback_V3.action";
    /**
     * 获取系统消息老接口
     */
    public static String SYSTEMMESSAGE = "http://service.kaixinxue.cn:8080/kxx/Uc_SysNotice_V2.action";
    /**
     * 补填邀请码
     */
    public static String SAVEUSERINVITECODE = "http://service.kaixinxue.cn:8080/kxx/Uc_logInvite_V2.action";




    //.........................................课程模块..............................................


    public final static String TABS = serverUrl+"main/operation/chp/tabs";// 获取首页所有tab标签
    public final static String TABCONTENT = serverUrl+"main/operation/chp/";// 获取首页tab标签内容 最后要加上tabid /column
    public final static String ClassDetail = serverUrl+"main/res/course/";// 课程详情 最后要加上 code
    public final static String ClassCateLog = serverUrl+"main/res/course/";// 课程目录 最后要加上 code /catalog
    public final static String ClassCommendList = serverUrl+"main/res/course/";// 获取推荐课程 最后要加上 code /recommends
    public final static String ClassList = serverUrl+"main/res/course/list";// 获取视频列表
    public final static String SpecialList = serverUrl+"main/res/coursetheme/list";// 获取专题列表
    public final static String TeacherInfo = serverUrl+"main/res/teacher/";// 获取教师信息 后要加 code教师编号
    public final static String Hot = serverUrl+"main/operation/searchHot/list";// 获取热搜
    public final static String MyClass = serverUrl+"main/member/course/list";// 获取我的课程
    public final static String MyClection = serverUrl+"main/member/course/collect/list";// 获取收藏的课程
    public final static String Clection = serverUrl+"main/member/course/collect/switch";// 收藏课程
    public final static String VideoPlayInfo = serverUrl+"main/res/course/";// 获取视频播放信息 后要加 code/videoplay
    public final static String ClassSpecialInfo = serverUrl+"main/res/coursetheme/";// 专题详情 后加code



    public final static String ProgressClass  = serverUrl+"main/member/info/census/course";// 课程进度


    //..............................................学习模块.................................................

    public final static String TaskList = serverUrl+"main/member/task/list";// 获取任务列表
    public final static String GetTaskReward  = serverUrl+"main/member/task/receive";// 领取奖励

    public final static String SignStatu  = serverUrl+"main/member/sign/is";// 获取用户签到信息
    public final static String Sign  = serverUrl+"main/member/sign/do";// 签到

    public final static String ProgressTask  = serverUrl+"main/member/info/census/task";// 任务进度
    public final static String PersionEasyChangeInfo  = serverUrl+" main/member/info/dynamic";// 获取用户经常变动的信息



    //.............................................订单..............................................

    public final static String GetClassOrder = serverUrl+"main/econ/order/create/course";// 获取课程订单
    public final static String GetSpecialOrder = serverUrl+"main/econ/order/create/coursetheme";// 获取专题订单



}