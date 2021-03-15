package com.kanjistudy.database.resources;

import android.content.Context;

import com.kanjistudy.R;
import com.kanjistudy.database.Database;
import com.kanjistudy.database.dao.KanaDao;
import com.kanjistudy.database.dao.KanjiDao;
import com.kanjistudy.database.dao.UserDao;
import com.kanjistudy.database.repo.KanaRepository;
import com.kanjistudy.database.repo.KanjiRepository;
import com.kanjistudy.database.repo.UserRepository;
import com.kanjistudy.models.Kana;
import com.kanjistudy.models.Kanji;
import com.kanjistudy.models.User;

public class Data {

    //LOCAL DB VARIABLES
    private static Database database;

    private static KanjiDao kanjiDao;
    private static KanaDao kanaDao;
    private static UserDao userDao;

    public static KanjiRepository kanjiRepository;
    public static KanaRepository kanaRepository;
    public static UserRepository userRepository;


    //Static variables according to the user that is currently engaging with the app
    public static User currentUser;

    //This only has to be called once
    public static void dbInit(Context context) {
        database = Database.getInstance(context);
        kanjiDao = database.kanjiDao();
        kanjiRepository = new KanjiRepository(kanjiDao);
        userDao = database.userDao();
        userRepository = new UserRepository(userDao);
        kanaDao = database.kanaDao();
        kanaRepository = new KanaRepository(kanaDao);

        //To insert the data just once when the app starts
        if (kanjiRepository.getAllKanjis().isEmpty() && kanaRepository.getAllKanas().isEmpty()) {
            loadKanjis();
            loadKana();
        }


    }

    //TRUE if exists
    public static boolean checkUserMail(String mail) {

        boolean emailExist = false;


        if (userRepository.getUserByMail(mail) != null) {
            emailExist = true;
        }

        return emailExist;

    }

    //TRUE if exists
    public static boolean checkUserName(String username) {

        boolean usernameExists = false;


        if (userRepository.getUserByUsername(username) != null) {
            usernameExists = true;
        }

        return usernameExists;

    }

    public static boolean checkPassword(String username, String password) {

        boolean correctPassword = false;


        if (userRepository.getPasswordByUsername(username).equals(password)) {
            correctPassword = true;
        }

        return correctPassword;

    }


    public static void loadUser(User userToInsert) {
        userRepository.insert(userToInsert);
    }

    public static void setCurrentUser(String username) {
        currentUser = userRepository.getUserByUsername(username);
    }


    public static void loadKanjis() {

        // Incoming kanjis
        //String[] kanjilvl1 = {"一", "右", "雨", "円", "王", "音", "下", "火", "花", "貝", "学", "気", "休", "玉", "金", "九", "空", "月", "犬", "見", "五", "口", "校", "左", "三", "山", "四", "子", "糸", "字", "耳", "七", "車", "手", "十", "出", "女", "小", "上", "森", "人", "水", "正", "生", "青", "石", "赤", "先", "千", "川", "早", "草", "足", "村", "大", "男", "竹", "中", "虫", "町", "天", "田", "土", "二", "日", "入", "年", "白", "八", "百", "文", "本", "名", "木", "目", "夕", "立", "力", "林", "六"};
        //String[] kanjilvl2 = {"引", "羽", "雲", "園", "遠", "黄", "何", "夏", "家", "科", "歌", "画", "会", "回", "海", "絵", "外", "角", "楽", "活", "間", "丸", "岩", "顔", "帰", "汽", "記", "弓", "牛", "魚", "京", "強", "教", "近", "兄", "形", "計", "元", "原", "言", "古", "戸", "午", "後", "語", "交", "光", "公", "工", "広", "考", "行", "高", "合", "国", "黒", "今", "才", "細", "作", "算", "姉", "市", "思", "止", "紙", "寺", "時", "自", "室", "社", "弱", "首", "秋", "週", "春", "書", "少", "場", "色", "食", "心", "新", "親", "図", "数", "星", "晴", "声", "西", "切", "雪", "線", "船", "前", "組", "走", "多", "太", "体", "台", "谷", "知", "地", "池", "茶", "昼", "朝", "長", "鳥", "直", "通", "弟", "店", "点", "電", "冬", "刀", "東", "当", "答", "頭", "同", "道", "読", "内", "南", "肉", "馬", "買", "売", "麦", "半", "番", "父", "風", "分", "聞", "米", "歩", "母", "方", "北", "妹", "毎", "万", "明", "鳴", "毛", "門", "夜", "野", "矢", "友", "曜", "用", "来", "理", "里", "話"};
        //String[] kanjilvl3 = {"悪", "安", "暗", "委", "意", "医", "育", "員", "飲", "院", "運", "泳", "駅", "央", "横", "屋", "温", "化", "荷", "界", "開", "階", "寒", "感", "漢", "館", "岸", "期", "起", "客", "宮", "急", "球", "究", "級", "去", "橋", "業", "局", "曲", "銀", "区", "苦", "具", "君", "係", "軽", "決", "血", "研", "県", "庫", "湖", "向", "幸", "港", "号", "根", "祭", "坂", "皿", "仕", "使", "始", "指", "死", "詩", "歯", "事", "持", "次", "式", "実", "写", "者", "主", "取", "守", "酒", "受", "州", "拾", "終", "習", "集", "住", "重", "宿", "所", "暑", "助", "勝", "商", "昭", "消", "章", "乗", "植", "深", "申", "真", "神", "身", "進", "世", "整", "昔", "全", "想", "相", "送", "息", "速", "族", "他", "打", "対", "待", "代", "第", "題", "炭", "短", "談", "着", "柱", "注", "丁", "帳", "調", "追", "定", "庭", "笛", "鉄", "転", "登", "都", "度", "島", "投", "湯", "等", "豆", "動", "童", "農", "波", "配", "倍", "箱", "畑", "発", "反", "板", "悲", "皮", "美", "鼻", "筆", "氷", "表", "病", "秒", "品", "負", "部", "服", "福", "物", "平", "返", "勉", "放", "味", "命", "面", "問", "役", "薬", "油", "有", "由", "遊", "予", "様", "洋", "羊", "葉", "陽", "落", "流", "旅", "両", "緑", "礼", "列", "練", "路", "和"};
        //String[] kanjilvl4 = {"愛", "案", "以", "位", "囲", "胃", "衣", "印", "栄", "英", "塩", "億", "加", "果", "課", "貨", "芽", "改", "械", "害", "街", "各", "覚", "完", "官", "管", "観", "関", "願", "喜", "器", "希", "旗", "機", "季", "紀", "議", "救", "求", "泣", "給", "挙", "漁", "競", "共", "協", "鏡", "極", "訓", "軍", "郡", "型", "径", "景", "芸", "欠", "結", "健", "建", "験", "固", "候", "功", "好", "康", "航", "告", "差", "最", "菜", "材", "昨", "刷", "察", "札", "殺", "参", "散", "産", "残", "司", "史", "士", "氏", "試", "児", "治", "辞", "失", "借", "種", "周", "祝", "順", "初", "唱", "松", "焼", "照", "省", "笑", "象", "賞", "信", "臣", "成", "清", "静", "席", "積", "折", "節", "説", "戦", "浅", "選", "然", "倉", "巣", "争", "側", "束", "続", "卒", "孫", "帯", "隊", "達", "単", "置", "仲", "貯", "兆", "腸", "低", "停", "底", "的", "典", "伝", "徒", "努", "灯", "働", "堂", "得", "特", "毒", "熱", "念", "敗", "梅", "博", "飯", "費", "飛", "必", "標", "票", "不", "付", "夫", "府", "副", "粉", "兵", "別", "変", "辺", "便", "包", "法", "望", "牧", "末", "満", "未", "脈", "民", "無", "約", "勇", "要", "養", "浴", "利", "陸", "料", "良", "量", "輪", "類", "令", "例", "冷", "歴", "連", "労", "老", "録"};
        //String[] kanjilvl5 = {"圧", "易", "移", "因", "営", "永", "衛", "液", "益", "演", "往", "応", "恩", "仮", "価", "可", "河", "過", "賀", "解", "快", "格", "確", "額", "刊", "幹", "慣", "眼", "基", "寄", "規", "技", "義", "逆", "久", "旧", "居", "許", "境", "興", "均", "禁", "句", "群", "経", "潔", "件", "券", "検", "険", "減", "現", "限", "個", "故", "護", "効", "厚", "構", "耕", "講", "鉱", "混", "査", "再", "妻", "採", "災", "際", "在", "罪", "財", "桜", "雑", "賛", "酸", "師", "志", "支", "枝", "資", "飼", "似", "示", "識", "質", "舎", "謝", "授", "修", "術", "述", "準", "序", "承", "招", "証", "常", "情", "条", "状", "織", "職", "制", "勢", "性", "政", "精", "製", "税", "績", "責", "接", "設", "絶", "舌", "銭", "祖", "素", "総", "像", "増", "造", "則", "測", "属", "損", "態", "貸", "退", "団", "断", "築", "張", "提", "程", "敵", "適", "統", "導", "銅", "徳", "独", "任", "燃", "能", "破", "判", "版", "犯", "比", "肥", "非", "備", "俵", "評", "貧", "婦", "富", "布", "武", "復", "複", "仏", "編", "弁", "保", "墓", "報", "豊", "暴", "貿", "防", "務", "夢", "迷", "綿", "輸", "余", "預", "容", "率", "略", "留", "領"};
        //String[] kanjilvl6 = {"異", "遺", "域", "宇", "映", "延", "沿", "我", "灰", "拡", "閣", "革", "割", "株", "巻", "干", "看", "簡", "危", "揮", "机", "貴", "疑", "吸", "供", "胸", "郷", "勤", "筋", "敬", "系", "警", "劇", "激", "穴", "憲", "権", "絹", "厳", "源", "呼", "己", "誤", "后", "孝", "皇", "紅", "鋼", "降", "刻", "穀", "骨", "困", "砂", "座", "済", "裁", "策", "冊", "蚕", "姿", "私", "至", "視", "詞", "誌", "磁", "射", "捨", "尺", "若", "樹", "収", "宗", "就", "衆", "従", "縦", "縮", "熟", "純", "処", "署", "諸", "除", "傷", "将", "障", "城", "蒸", "針", "仁", "垂", "推", "寸", "盛", "聖", "誠", "宣", "専", "泉", "洗", "染", "善", "創", "奏", "層", "操", "窓", "装", "臓", "蔵", "存", "尊", "宅", "担", "探", "誕", "暖", "段", "値", "宙", "忠", "著", "庁", "潮", "頂", "賃", "痛", "展", "党", "糖", "討", "届", "難", "乳", "認", "納", "脳", "派", "俳", "拝", "背", "肺", "班", "晩", "否", "批", "秘", "腹", "奮", "並", "閉", "陛", "片", "補", "暮", "宝", "訪", "亡", "忘", "棒", "枚", "幕", "密", "盟", "模", "訳", "優", "郵", "幼", "欲", "翌", "乱", "卵", "覧", "裏", "律", "臨", "朗", "論"};
        //String[] kanjilvl7 = {"亜", "哀", "挨", "握", "扱", "宛", "闇", "依", "偉", "威", "尉", "慰", "椅", "為", "畏", "維", "緯", "萎", "違", "井", "壱", "逸", "稲", "茨", "芋", "咽", "姻", "淫", "陰", "隠", "韻", "臼", "渦", "唄", "浦", "餌", "影", "詠", "鋭", "疫", "悦", "謁", "越", "閲", "宴", "怨", "援", "炎", "煙", "猿", "縁", "艶", "鉛", "汚", "凹", "奥", "押", "旺", "欧", "殴", "翁", "岡", "沖", "憶", "臆", "乙", "俺", "卸", "穏", "佳", "嫁", "寡", "暇", "架", "禍", "稼", "箇", "苛", "華", "菓", "蚊", "牙", "雅", "餓", "介", "塊", "壊", "怪", "悔", "懐", "戒", "拐", "皆", "劾", "崖", "慨", "概", "涯", "蓋", "該", "骸", "垣", "柿", "嚇", "核", "殻", "獲", "穫", "較", "郭", "隔", "岳", "顎", "掛", "潟", "喝", "括", "渇", "滑", "葛", "褐", "轄", "且", "釜", "鎌", "刈", "瓦", "乾", "冠", "勘", "勧", "喚", "堪", "寛", "患", "憾", "換", "敢", "棺", "款", "歓", "汗", "環", "甘", "監", "緩", "缶", "肝", "艦", "貫", "還", "鑑", "閑", "陥", "韓", "含", "玩", "頑", "企", "伎", "奇", "岐", "幾", "忌", "既", "棋", "棄", "畿", "祈", "軌", "輝", "飢", "騎", "鬼", "亀", "偽", "儀", "宜", "戯", "擬", "欺", "犠", "菊", "吉", "喫", "詰", "却", "脚", "虐", "丘", "及", "朽", "窮", "糾", "巨", "拒", "拠", "虚", "距", "享", "凶", "叫", "峡", "恐", "恭", "挟", "況", "狂", "狭", "矯", "脅", "響", "驚", "仰", "凝", "暁", "僅", "巾", "錦", "斤", "琴", "緊", "菌", "襟", "謹", "吟", "駆", "駒", "愚", "虞", "偶", "遇", "隅", "串", "屈", "掘", "窟", "靴", "熊", "繰", "桑", "勲", "薫", "傾", "刑", "啓", "契", "恵", "慶", "憩", "掲", "携", "渓", "稽", "継", "茎", "蛍", "詣", "鶏", "迎", "鯨", "撃", "隙", "桁", "傑", "倹", "兼", "剣", "圏", "堅", "嫌", "懸", "拳", "献", "肩", "謙", "賢", "軒", "遣", "鍵", "顕", "幻", "弦", "玄", "舷", "孤", "弧", "枯", "股", "虎", "誇", "雇", "顧", "鼓", "互", "呉", "娯", "御", "悟", "碁", "乞", "侯", "勾", "喉", "坑", "孔", "巧", "恒", "慌", "抗", "拘", "控", "攻", "更", "梗", "江", "洪", "溝", "甲", "硬", "稿", "絞", "綱", "肯", "荒", "衡", "貢", "購", "郊", "酵", "項", "香", "剛", "拷", "豪", "克", "酷", "獄", "腰", "込", "頃", "墾", "婚", "恨", "懇", "昆", "痕", "紺", "魂", "佐", "唆", "沙", "詐", "鎖", "挫", "債", "催", "塞", "宰", "彩", "栽", "歳", "采", "砕", "斎", "載", "剤", "阪", "咲", "崎", "埼", "削", "搾", "柵", "索", "錯", "拶", "撮", "擦", "傘", "惨", "桟", "斬", "暫", "伺", "刺", "嗣", "施", "旨", "祉", "紫", "肢", "脂", "諮", "賜", "雌", "侍", "慈", "滋", "璽", "鹿", "軸", "叱", "執", "嫉", "湿", "漆", "疾", "芝", "赦", "斜", "煮", "遮", "蛇", "邪", "爵", "酌", "釈", "寂", "朱", "殊", "狩", "珠", "腫", "趣", "儒", "呪", "寿", "需", "囚", "愁", "秀", "臭", "舟", "襲", "蹴", "酬", "醜", "充", "柔", "汁", "渋", "獣", "銃", "叔", "淑", "粛", "塾", "俊", "瞬", "准", "循", "旬", "殉", "潤", "盾", "巡", "遵", "庶", "緒", "叙", "徐", "償", "匠", "升", "召", "奨", "宵", "尚", "床", "彰", "抄", "掌", "昇", "晶", "沼", "渉", "焦", "症", "硝", "礁", "祥", "称", "粧", "紹", "肖", "衝", "訟", "詔", "詳", "鐘", "丈", "冗", "剰", "壌", "嬢", "浄", "畳", "譲", "醸", "錠", "嘱", "飾", "拭", "殖", "触", "辱", "尻", "伸", "侵", "唇", "娠", "寝", "審", "慎", "振", "浸", "紳", "芯", "薪", "診", "辛", "震", "刃", "尋", "甚", "尽", "腎", "迅", "陣", "須", "酢", "吹", "帥", "炊", "睡", "粋", "衰", "遂", "酔", "随", "髄", "崇", "枢", "据", "杉", "裾", "澄", "瀬", "畝", "是", "凄", "姓", "征", "牲", "誓", "請", "逝", "醒", "斉", "隻", "惜", "戚", "斥", "析", "籍", "脊", "跡", "拙", "摂", "窃", "仙", "占", "扇", "栓", "潜", "煎", "旋", "繊", "羨", "腺", "薦", "詮", "践", "遷", "鮮", "漸", "禅", "繕", "膳", "塑", "措", "曽", "狙", "疎", "礎", "租", "粗", "訴", "阻", "遡", "僧", "双", "喪", "壮", "爽", "捜", "掃", "挿", "曹", "槽", "燥", "痩", "荘", "葬", "藻", "遭", "霜", "騒", "憎", "贈", "促", "即", "捉", "俗", "賊", "袖", "遜", "汰", "唾", "堕", "妥", "惰", "駄", "堆", "耐", "怠", "戴", "替", "泰", "滞", "胎", "袋", "逮", "滝", "卓", "択", "拓", "沢", "濯", "託", "濁", "諾", "但", "奪", "脱", "棚", "誰", "丹", "嘆", "旦", "淡", "端", "綻", "胆", "鍛", "壇", "弾", "恥", "痴", "稚", "致", "遅", "畜", "蓄", "逐", "秩", "窒", "嫡", "抽", "衷", "酎", "鋳", "駐", "弔", "彫", "徴", "懲", "挑", "眺", "聴", "超", "跳", "勅", "捗", "朕", "沈", "珍", "鎮", "陳", "津", "墜", "椎", "塚", "漬", "潰", "坪", "爪", "釣", "鶴", "亭", "偵", "貞", "呈", "堤", "帝", "廷", "抵", "締", "艇", "訂", "諦", "逓", "邸", "泥", "摘", "滴", "溺", "哲", "徹", "撤", "迭", "添", "貼", "殿", "吐", "塗", "妬", "斗", "渡", "賭", "途", "奴", "怒", "倒", "凍", "唐", "塔", "悼", "搭", "桃", "棟", "盗", "痘", "筒", "到", "藤", "謄", "踏", "逃", "透", "陶", "騰", "闘", "憧", "洞", "瞳", "胴", "峠", "匿", "督", "篤", "栃", "凸", "突", "屯", "豚", "頓", "曇", "鈍", "奈", "那", "謎", "鍋", "縄", "軟", "尼", "弐", "匂", "虹", "如", "尿", "妊", "忍", "寧", "猫", "捻", "粘", "悩", "濃", "把", "覇", "婆", "罵", "廃", "排", "杯", "輩", "培", "媒", "賠", "陪", "伯", "剥", "拍", "泊", "舶", "薄", "迫", "漠", "爆", "縛", "箸", "肌", "鉢", "髪", "伐", "罰", "抜", "閥", "伴", "帆", "搬", "斑", "氾", "汎", "畔", "繁", "般", "藩", "販", "範", "煩", "頒", "盤", "蛮", "卑", "妃", "彼", "扉", "披", "泌", "疲", "碑", "罷", "被", "避", "尾", "微", "眉", "匹", "膝", "肘", "姫", "媛", "漂", "描", "苗", "浜", "賓", "頻", "敏", "瓶", "怖", "扶", "敷", "普", "浮", "符", "腐", "膚", "譜", "賦", "赴", "阜", "附", "侮", "舞", "封", "伏", "幅", "覆", "払", "沸", "噴", "墳", "憤", "紛", "雰", "丙", "併", "塀", "幣", "弊", "柄", "蔽", "壁", "癖", "蔑", "偏", "遍", "舗", "捕", "穂", "募", "慕", "簿", "倣", "俸", "奉", "峰", "崩", "抱", "泡", "砲", "縫", "胞", "芳", "蜂", "褒", "邦", "飽", "乏", "傍", "剖", "坊", "妨", "帽", "忙", "房", "某", "冒", "紡", "肪", "膨", "謀", "貌", "僕", "墨", "撲", "朴", "睦", "勃", "没", "堀", "奔", "翻", "凡", "盆", "摩", "磨", "魔", "麻", "埋", "昧", "膜", "枕", "又", "抹", "繭", "慢", "漫", "魅", "岬", "蜜", "妙", "眠", "矛", "霧", "婿", "娘", "冥", "銘", "滅", "免", "麺", "茂", "妄", "猛", "盲", "網", "耗", "黙", "餅", "戻", "紋", "冶", "弥", "厄", "躍", "柳", "愉", "癒", "諭", "唯", "幽", "悠", "憂", "湧", "猶", "裕", "誘", "雄", "融", "与", "誉", "妖", "庸", "揚", "揺", "擁", "溶", "窯", "謡", "踊", "抑", "沃", "翼", "羅", "裸", "頼", "雷", "絡", "酪", "嵐", "欄", "濫", "藍", "吏", "履", "梨", "璃", "痢", "離", "硫", "粒", "隆", "竜", "侶", "慮", "虜", "了", "僚", "寮", "涼", "猟", "療", "瞭", "糧", "陵", "倫", "厘", "隣", "瑠", "塁", "涙", "累", "励", "鈴", "隷", "零", "霊", "麗", "齢", "暦", "劣", "烈", "裂", "廉", "恋", "錬", "呂", "炉", "賂", "露", "廊", "弄", "楼", "浪", "漏", "郎", "麓", "賄", "脇", "惑", "枠", "湾", "腕", "丼", "傲", "刹", "哺", "喩", "嗅", "嘲", "毀", "彙", "恣", "惧", "慄", "憬", "拉", "摯", "曖", "楷", "鬱", "璧", "瘍", "箋", "籠", "緻", "羞", "訃", "諧", "貪", "踪", "辣", "錮", "塡", "頰"};


        Kanji[][] kanjis = {
                {
                        new Kanji("一", "One", "Ichi", R.raw.ichi),
                        new Kanji("右", "Right", "Migi", R.raw.migi),
                        new Kanji("雨", "Rain", "Ame", R.raw.ame),
                        new Kanji("円", "Circle", "En", R.raw.en),
                        new Kanji("王", "King", "Ō", R.raw.okanji),
                },
                {
                        new Kanji("歌", "Song", "Uta", R.raw.uta),
                        new Kanji("羽", "Wing", "Hane", R.raw.hane),
                        new Kanji("雲", "Cloud", "Kumo", R.raw.kumo),
                        new Kanji("画", "Picture", "Ga", R.raw.gakanji),
                        new Kanji("遠", "Far", "Tō", R.raw.tokanji),
                },
                {
                        new Kanji("悪", "Evil", "Waru", R.raw.warukanji),
                        new Kanji("員", "Member", "In", R.raw.inkanji),
                        new Kanji("運", "Luck", "Un", R.raw.unkanji),
                        new Kanji("委", "Commission", "I", R.raw.commissionkanji),
                        new Kanji("品", "Goods", "Shina", R.raw.goodskanji),
                },
                {
                        new Kanji("愛", "Love", "Ai", R.raw.lovekanji),
                        new Kanji("案", "Draft", "An", R.raw.draftkanji),
                        new Kanji("以", "After", "I", R.raw.afterkanji),
                        new Kanji("晴", "Sunny", "Hare", R.raw.sunnykanji),
                        new Kanji("役", "Role", "Yaku", R.raw.rolekanji),
                },
                {
                        new Kanji("液", "Liquid", "Eki", R.raw.liquidkanji),
                        new Kanji("圧", "Pressure", "Atsu", R.raw.pressurekanji),
                        new Kanji("易", "Easy", "Eki", R.raw.easykanji),
                        new Kanji("解", "Solution", "Kai", R.raw.solutionkanji),
                        new Kanji("因", "Cause", "In", R.raw.causekanji),
                },
                {
                        new Kanji("巻", "Roll", "Maki", R.raw.rollkanji),
                        new Kanji("系", "System", "Kei", R.raw.systemkanji),
                        new Kanji("域", "Area", "Iki", R.raw.areakanji),
                        new Kanji("割", "Discount", "Wari", R.raw.discountkanji),
                        new Kanji("映", "Project", "Ei", R.raw.projectkanji),
                },
                {
                        new Kanji("亜", "Arabic", "A", R.raw.arabickanji),
                        new Kanji("威", "Prestige", "I", R.raw.prestigekanji),
                        new Kanji("尉", "Lieutenant", "Jō", R.raw.lieutenantkanji),
                        new Kanji("緯", "Latitude", "Nuki", R.raw.latitudekanji),
                        new Kanji("釜", "Kettle", "Kama", R.raw.kettlekanji),
                },
        };

        for (int i = 0; i < kanjis.length; i++) {
            for (int j = 0; j < kanjis[i].length; j++) {
                kanjis[i][j].setLevel(Integer.toString(i + 1));
                kanjiRepository.insert(kanjis[i][j]);
            }
        }

    }


    public static void loadKana() {

        Kana[] kanaArray = {

                new Kana("あ", "ア", "a", "a", null, "gojūon", R.raw.a),
                new Kana("い", "イ", "i", "i", null, "gojūon", R.raw.i),
                new Kana("う", "ウ", "u", "u", null, "gojūon", R.raw.u),
                new Kana("え", "エ", "e", "e", null, "gojūon", R.raw.e),
                new Kana("お", "オ", "o", "o", null, "gojūon", R.raw.o),
                new Kana("か", "カ", "ka", "a", "k", "gojūon", R.raw.ka),
                new Kana("き", "キ", "ki", "i", "k", "gojūon", R.raw.ki),
                new Kana("く", "ク", "ku", "u", "k", "gojūon", R.raw.ku),
                new Kana("け", "ケ", "ke", "e", "k", "gojūon", R.raw.ke),
                new Kana("こ", "コ", "ko", "o", "k", "gojūon", R.raw.ko),
                new Kana("さ", "サ", "sa", "a", "s", "gojūon", R.raw.sa),
                new Kana("し", "シ", "shi", "i", "s", "gojūon", R.raw.shi),
                new Kana("す", "ス", "su", "u", "s", "gojūon", R.raw.su),
                new Kana("せ", "セ", "se", "e", "s", "gojūon", R.raw.se),
                new Kana("そ", "ソ", "so", "o", "s", "gojūon", R.raw.so),
                new Kana("た", "タ", "ta", "a", "t", "gojūon", R.raw.ta),
                new Kana("ち", "チ", "chi", "i", "t", "gojūon", R.raw.chi),
                new Kana("つ", "ツ", "tsu", "u", "t", "gojūon", R.raw.tsu),
                new Kana("て", "テ", "te", "e", "t", "gojūon", R.raw.te),
                new Kana("と", "ト", "to", "o", "t", "gojūon", R.raw.to),
                new Kana("な", "ナ", "na", "a", "n", "gojūon", R.raw.na),
                new Kana("に", "ニ", "ni", "i", "n", "gojūon", R.raw.ni),
                new Kana("ぬ", "ヌ", "nu", "u", "n", "gojūon", R.raw.nu),
                new Kana("ね", "ネ", "ne", "e", "n", "gojūon", R.raw.ne),
                new Kana("の", "ノ", "no", "o", "n", "gojūon", R.raw.no),
                new Kana("は", "ハ", "ha", "a", "h", "gojūon", R.raw.ha),
                new Kana("ひ", "ヒ", "hi", "i", "h", "gojūon", R.raw.hi),
                new Kana("ふ", "フ", "fu", "u", "h", "gojūon", R.raw.fu),
                new Kana("へ", "ヘ", "he", "e", "h", "gojūon", R.raw.he),
                new Kana("ほ", "ホ", "ho", "o", "h", "gojūon", R.raw.ho),
                new Kana("ま", "マ", "ma", "a", "m", "gojūon", R.raw.ma),
                new Kana("み", "ミ", "mi", "i", "m", "gojūon", R.raw.mi),
                new Kana("む", "ム", "mu", "u", "m", "gojūon", R.raw.mu),
                new Kana("め", "メ", "me", "e", "m", "gojūon", R.raw.me),
                new Kana("も", "モ", "mo", "o", "m", "gojūon", R.raw.mo),
                new Kana("や", "ヤ", "ya", "a", "y", "gojūon", R.raw.ya),
                new Kana("ゆ", "ユ", "yu", "u", "y", "gojūon", R.raw.yu),
                new Kana("よ", "ヨ", "yo", "o", "y", "gojūon", R.raw.yo),
                new Kana("ら", "ラ", "ra", "a", "r", "gojūon", R.raw.ra),
                new Kana("り", "リ", "ri", "i", "r", "gojūon", R.raw.ri),
                new Kana("る", "ル", "ru", "u", "r", "gojūon", R.raw.ru),
                new Kana("れ", "レ", "re", "e", "r", "gojūon", R.raw.re),
                new Kana("ろ", "ロ", "ro", "o", "r", "gojūon", R.raw.ro),
                new Kana("わ", "ワ", "wa", "a", "w", "gojūon", R.raw.wa),
                new Kana("を", "ヲ", "wo", "u", "w", "gojūon", R.raw.wo),

                //n
                new Kana("ん", "ッ", "n", null, "n", "gojūon", R.raw.n),


                //yóon
                new Kana("きゃ", "キャ", "kya", "a", "k", "yōon", R.raw.kya),
                new Kana("きゅ", "キュ", "kyu", "u", "k", "yōon", R.raw.kyu),
                new Kana("きょ", "キョ", "kyo", "o", "k", "yōon", R.raw.kyo),
                new Kana("しゃ", "シャ", "sha", "a", "s", "yōon", R.raw.sha),
                new Kana("しゅ", "シュ", "shu", "u", "s", "yōon", R.raw.shu),
                new Kana("しょ", "ショ", "sho", "o", "s", "yōon", R.raw.sho),
                new Kana("ちゃ", "チャ", "cha", "a", "c", "yōon", R.raw.cha),
                new Kana("ちゅ", "チュ", "chi", "u", "c", "yōon", R.raw.chi),
                new Kana("ちょ", "チョ", "cho", "o", "c", "yōon", R.raw.cho),
                new Kana("にゃ", "ニャ", "nya", "a", "n", "yōon", R.raw.nya),
                new Kana("にゅ", "ニュ", "nyu", "u", "n", "yōon", R.raw.nyu),
                new Kana("にょ", "ニョ", "nyo", "o", "n", "yōon", R.raw.nyo),
                new Kana("ひゃ", "ヒャ", "hya", "a", "h", "yōon", R.raw.hya),
                new Kana("ひゅ", "ヒュ", "hyu", "u", "h", "yōon", R.raw.hyu),
                new Kana("ひょ", "ヒョ", "hyo", "o", "h", "yōon", R.raw.hyo),
                new Kana("みゃ", "ミャ", "mya", "a", "m", "yōon", R.raw.mya),
                new Kana("みゅ", "ミュ", "myu", "u", "m", "yōon", R.raw.myu),
                new Kana("みょ", "ミョ", "myo", "o", "m", "yōon", R.raw.myo),
                new Kana("りゃ", "リャ", "rya", "ya", "r", "yōon", R.raw.rya),
                new Kana("りゅ", "リュ", "ryu", "yu", "r", "yōon", R.raw.ryu),
                new Kana("りょ", "リョ", "ryo", "yo", "r", "yōon", R.raw.ryo),

                //Diacritics (gojūon with (han)dakuten)
                new Kana("が", "ガ", "ga", "a", "g", "gojūon with (han)dakuten", R.raw.ga),
                new Kana("ぎ", "ギ", "gi", "i", "g", "gojūon with (han)dakuten", R.raw.gi),
                new Kana("ぐ", "グ", "gu", "u", "g", "gojūon with (han)dakuten", R.raw.gu),
                new Kana("げ", "ゲ", "ge", "e", "g", "gojūon with (han)dakuten", R.raw.ge),
                new Kana("ご", "ゴ", "go", "o", "g", "gojūon with (han)dakuten", R.raw.go),
                new Kana("ざ", "ザ", "za", "a", "z", "gojūon with (han)dakuten", R.raw.za),
                new Kana("じ", "ジ", "ji", "i", "z", "gojūon with (han)dakuten", R.raw.ji),
                new Kana("ず", "ズ", "zu", "u", "z", "gojūon with (han)dakuten", R.raw.zu),
                new Kana("ぜ", "ゼ", "ze", "e", "z", "gojūon with (han)dakuten", R.raw.ze),
                new Kana("ぞ", "ゾ", "zo", "o", "z", "gojūon with (han)dakuten", R.raw.zo),
                new Kana("だ", "ダ", "da", "a", "d", "gojūon with (han)dakuten", R.raw.da),
                new Kana("ぢ", "ヂ", "ji", "i", "d", "gojūon with (han)dakuten", R.raw.ji),
                new Kana("づ", "ヅ", "zu", "u", "d", "gojūon with (han)dakuten", R.raw.zu),
                new Kana("で", "デ", "de", "e", "d", "gojūon with (han)dakuten", R.raw.de),
                new Kana("ど", "ド", "do", "o", "d", "gojūon with (han)dakuten", R.raw.do_),
                new Kana("ば", "バ", "ba", "a", "b", "gojūon with (han)dakuten", R.raw.ba),
                new Kana("び", "ビ", "bi", "i", "b", "gojūon with (han)dakuten", R.raw.bi),
                new Kana("ぶ", "ブ", "bu", "u", "b", "gojūon with (han)dakuten", R.raw.bu),
                new Kana("べ", "ベ", "be", "e", "b", "gojūon with (han)dakuten", R.raw.be),
                new Kana("ぼ", "ボ", "bo", "o", "b", "gojūon with (han)dakuten", R.raw.bo),
                new Kana("ぱ", "パ", "pa", "a", "p", "gojūon with (han)dakuten", R.raw.pa),
                new Kana("ぴ", "ピ", "pi", "i", "p", "gojūon with (han)dakuten", R.raw.pi),
                new Kana("ぷ", "プ", "pu", "u", "p", "gojūon with (han)dakuten", R.raw.pu),
                new Kana("ぺ", "ペ", "pe", "e", "p", "gojūon with (han)dakuten", R.raw.pe),
                new Kana("ぽ", "ポ", "po", "o", "p", "gojūon with (han)dakuten", R.raw.po),

                //Digraphs with diacritics (yōon with (han)dakuten)
                new Kana("ぎゃ", "ギャ", "gya", "a", "g", "yōon with (han)dakuten", R.raw.gya),
                new Kana("ぎゅ", "ギュ", "gyu", "u", "g", "yōon with (han)dakuten", R.raw.gyu),
                new Kana("ぎょ", "ギョ", "gyo", "o", "g", "yōon with (han)dakuten", R.raw.gyo),
                new Kana("じゃ", "ジャ", "ja", "a", "z", "yōon with (han)dakuten", R.raw.ja),
                new Kana("じゅ", "ジュ", "ju", "u", "z", "yōon with (han)dakuten", R.raw.ju),
                new Kana("じょ", "ジョ", "jo", "o", "z", "yōon with (han)dakuten", R.raw.jo),
                new Kana("ぢゃ ", "ヂャ", "ja", "a", "d", "yōon with (han)dakuten", R.raw.ja),
                new Kana("ぢゅ", "ヂュ", "ju", "u", "d", "yōon with (han)dakuten", R.raw.ju),
                new Kana("ぢょ", "ヂョ", "jo", "o", "d", "yōon with (han)dakuten", R.raw.jo),
                new Kana("びゃ", "ビャ", "bya", "a", "b", "yōon with (han)dakuten", R.raw.bya),
                new Kana("びゅ", "ビュ", "byu", "u", "b", "yōon with (han)dakuten", R.raw.byu),
                new Kana("びょ", "ビョ", "byo", "o", "b", "yōon with (han)dakuten", R.raw.byo),
                new Kana("ぴゃ", "ピャ", "pya", "a", "p", "yōon with (han)dakuten", R.raw.pya),
                new Kana("ぴゅ", "ピュ", "pyu", "u", "p", "yōon with (han)dakuten", R.raw.pyu),
                new Kana("ぴょ", "ピョ", "pyo", "o", "p", "yōon with (han)dakuten", R.raw.pyo),

        };

        for (Kana kana : kanaArray) {
            kanaRepository.insert(kana);
        }

    }

//    public static String getRandomKanji();
}
