package com.kanjistudy.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kanjistudy.R;
import com.kanjistudy.controllers.ToastsConfig;
import com.kanjistudy.database.KanjiDao;
import com.kanjistudy.database.KanjiDatabase;
import com.kanjistudy.database.KanjiRepository;
import com.kanjistudy.models.KanjiDb;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private List<KanjiDb> localKanjiList;
    private Retrofit retrofit;
    private HttpLoggingInterceptor interceptor;
    private OkHttpClient.Builder httpClientBuilder;

    //LOCAL DB VARIABLES
    static KanjiDatabase kanjiDatabase;
    static KanjiDao kanjiDao;
    static KanjiRepository kanjiRepository;

    ToastsConfig toastsConfig = new ToastsConfig();
    TextView kanjiTextView, kanaTextView, hiraganaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kanjiTextView = findViewById(R.id.kanjiActivityTextViewMain);
        kanaTextView = findViewById(R.id.kanaActivityTextViewMain);
        hiraganaTextView = findViewById(R.id.hiraganaActivityTextViewMain);

        //toastsConfig.showToastByDuration(getApplicationContext(), 2, Integer.toString(levelIndex));

        //To insert the data just once when the app starts
        if (kanjiRepository == null) {

            kanjiDatabase = KanjiDatabase.getInstance(this.getApplicationContext());
            kanjiDao = kanjiDatabase.kanjiDao();
            kanjiRepository = new KanjiRepository(kanjiDao);
            loadKanjis();
            localKanjiList = kanjiRepository.getAllKanjis();
        }


        kanjiTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fromMainToKanjiOptionsActivity = new Intent(getApplicationContext(), KanjiOptionsActivity.class);
                startActivity(fromMainToKanjiOptionsActivity);
            }
        });

        hiraganaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastsConfig.showToastByDuration(getApplicationContext(), 3, "Coming soon...");
            }
        });


        kanaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastsConfig.showToastByDuration(getApplicationContext(), 3, "Coming soon...");
            }
        });


    }

    public void loadKanjis() {

        //Kanjis divided by levels
        String[] kanjilvl1 = {"一", "右", "雨", "円", "王", "音", "下", "火", "花", "貝", "学", "気", "休", "玉", "金", "九", "空", "月", "犬", "見", "五", "口", "校", "左", "三", "山", "四", "子", "糸", "字", "耳", "七", "車", "手", "十", "出", "女", "小", "上", "森", "人", "水", "正", "生", "青", "石", "赤", "先", "千", "川", "早", "草", "足", "村", "大", "男", "竹", "中", "虫", "町", "天", "田", "土", "二", "日", "入", "年", "白", "八", "百", "文", "本", "名", "木", "目", "夕", "立", "力", "林", "六"};
        String[] kanjilvl2 = {"引", "羽", "雲", "園", "遠", "黄", "何", "夏", "家", "科", "歌", "画", "会", "回", "海", "絵", "外", "角", "楽", "活", "間", "丸", "岩", "顔", "帰", "汽", "記", "弓", "牛", "魚", "京", "強", "教", "近", "兄", "形", "計", "元", "原", "言", "古", "戸", "午", "後", "語", "交", "光", "公", "工", "広", "考", "行", "高", "合", "国", "黒", "今", "才", "細", "作", "算", "姉", "市", "思", "止", "紙", "寺", "時", "自", "室", "社", "弱", "首", "秋", "週", "春", "書", "少", "場", "色", "食", "心", "新", "親", "図", "数", "星", "晴", "声", "西", "切", "雪", "線", "船", "前", "組", "走", "多", "太", "体", "台", "谷", "知", "地", "池", "茶", "昼", "朝", "長", "鳥", "直", "通", "弟", "店", "点", "電", "冬", "刀", "東", "当", "答", "頭", "同", "道", "読", "内", "南", "肉", "馬", "買", "売", "麦", "半", "番", "父", "風", "分", "聞", "米", "歩", "母", "方", "北", "妹", "毎", "万", "明", "鳴", "毛", "門", "夜", "野", "矢", "友", "曜", "用", "来", "理", "里", "話"};
        String[] kanjilvl3 = {"悪", "安", "暗", "委", "意", "医", "育", "員", "飲", "院", "運", "泳", "駅", "央", "横", "屋", "温", "化", "荷", "界", "開", "階", "寒", "感", "漢", "館", "岸", "期", "起", "客", "宮", "急", "球", "究", "級", "去", "橋", "業", "局", "曲", "銀", "区", "苦", "具", "君", "係", "軽", "決", "血", "研", "県", "庫", "湖", "向", "幸", "港", "号", "根", "祭", "坂", "皿", "仕", "使", "始", "指", "死", "詩", "歯", "事", "持", "次", "式", "実", "写", "者", "主", "取", "守", "酒", "受", "州", "拾", "終", "習", "集", "住", "重", "宿", "所", "暑", "助", "勝", "商", "昭", "消", "章", "乗", "植", "深", "申", "真", "神", "身", "進", "世", "整", "昔", "全", "想", "相", "送", "息", "速", "族", "他", "打", "対", "待", "代", "第", "題", "炭", "短", "談", "着", "柱", "注", "丁", "帳", "調", "追", "定", "庭", "笛", "鉄", "転", "登", "都", "度", "島", "投", "湯", "等", "豆", "動", "童", "農", "波", "配", "倍", "箱", "畑", "発", "反", "板", "悲", "皮", "美", "鼻", "筆", "氷", "表", "病", "秒", "品", "負", "部", "服", "福", "物", "平", "返", "勉", "放", "味", "命", "面", "問", "役", "薬", "油", "有", "由", "遊", "予", "様", "洋", "羊", "葉", "陽", "落", "流", "旅", "両", "緑", "礼", "列", "練", "路", "和"};
        String[] kanjilvl4 = {"愛", "案", "以", "位", "囲", "胃", "衣", "印", "栄", "英", "塩", "億", "加", "果", "課", "貨", "芽", "改", "械", "害", "街", "各", "覚", "完", "官", "管", "観", "関", "願", "喜", "器", "希", "旗", "機", "季", "紀", "議", "救", "求", "泣", "給", "挙", "漁", "競", "共", "協", "鏡", "極", "訓", "軍", "郡", "型", "径", "景", "芸", "欠", "結", "健", "建", "験", "固", "候", "功", "好", "康", "航", "告", "差", "最", "菜", "材", "昨", "刷", "察", "札", "殺", "参", "散", "産", "残", "司", "史", "士", "氏", "試", "児", "治", "辞", "失", "借", "種", "周", "祝", "順", "初", "唱", "松", "焼", "照", "省", "笑", "象", "賞", "信", "臣", "成", "清", "静", "席", "積", "折", "節", "説", "戦", "浅", "選", "然", "倉", "巣", "争", "側", "束", "続", "卒", "孫", "帯", "隊", "達", "単", "置", "仲", "貯", "兆", "腸", "低", "停", "底", "的", "典", "伝", "徒", "努", "灯", "働", "堂", "得", "特", "毒", "熱", "念", "敗", "梅", "博", "飯", "費", "飛", "必", "標", "票", "不", "付", "夫", "府", "副", "粉", "兵", "別", "変", "辺", "便", "包", "法", "望", "牧", "末", "満", "未", "脈", "民", "無", "約", "勇", "要", "養", "浴", "利", "陸", "料", "良", "量", "輪", "類", "令", "例", "冷", "歴", "連", "労", "老", "録"};
        String[] kanjilvl5 = {"圧", "易", "移", "因", "営", "永", "衛", "液", "益", "演", "往", "応", "恩", "仮", "価", "可", "河", "過", "賀", "解", "快", "格", "確", "額", "刊", "幹", "慣", "眼", "基", "寄", "規", "技", "義", "逆", "久", "旧", "居", "許", "境", "興", "均", "禁", "句", "群", "経", "潔", "件", "券", "検", "険", "減", "現", "限", "個", "故", "護", "効", "厚", "構", "耕", "講", "鉱", "混", "査", "再", "妻", "採", "災", "際", "在", "罪", "財", "桜", "雑", "賛", "酸", "師", "志", "支", "枝", "資", "飼", "似", "示", "識", "質", "舎", "謝", "授", "修", "術", "述", "準", "序", "承", "招", "証", "常", "情", "条", "状", "織", "職", "制", "勢", "性", "政", "精", "製", "税", "績", "責", "接", "設", "絶", "舌", "銭", "祖", "素", "総", "像", "増", "造", "則", "測", "属", "損", "態", "貸", "退", "団", "断", "築", "張", "提", "程", "敵", "適", "統", "導", "銅", "徳", "独", "任", "燃", "能", "破", "判", "版", "犯", "比", "肥", "非", "備", "俵", "評", "貧", "婦", "富", "布", "武", "復", "複", "仏", "編", "弁", "保", "墓", "報", "豊", "暴", "貿", "防", "務", "夢", "迷", "綿", "輸", "余", "預", "容", "率", "略", "留", "領"};
        String[] kanjilvl6 = {"異", "遺", "域", "宇", "映", "延", "沿", "我", "灰", "拡", "閣", "革", "割", "株", "巻", "干", "看", "簡", "危", "揮", "机", "貴", "疑", "吸", "供", "胸", "郷", "勤", "筋", "敬", "系", "警", "劇", "激", "穴", "憲", "権", "絹", "厳", "源", "呼", "己", "誤", "后", "孝", "皇", "紅", "鋼", "降", "刻", "穀", "骨", "困", "砂", "座", "済", "裁", "策", "冊", "蚕", "姿", "私", "至", "視", "詞", "誌", "磁", "射", "捨", "尺", "若", "樹", "収", "宗", "就", "衆", "従", "縦", "縮", "熟", "純", "処", "署", "諸", "除", "傷", "将", "障", "城", "蒸", "針", "仁", "垂", "推", "寸", "盛", "聖", "誠", "宣", "専", "泉", "洗", "染", "善", "創", "奏", "層", "操", "窓", "装", "臓", "蔵", "存", "尊", "宅", "担", "探", "誕", "暖", "段", "値", "宙", "忠", "著", "庁", "潮", "頂", "賃", "痛", "展", "党", "糖", "討", "届", "難", "乳", "認", "納", "脳", "派", "俳", "拝", "背", "肺", "班", "晩", "否", "批", "秘", "腹", "奮", "並", "閉", "陛", "片", "補", "暮", "宝", "訪", "亡", "忘", "棒", "枚", "幕", "密", "盟", "模", "訳", "優", "郵", "幼", "欲", "翌", "乱", "卵", "覧", "裏", "律", "臨", "朗", "論"};


        KanjiDb kanjiDb;

        for (String kanji : kanjilvl1) {
            kanjiDb = new KanjiDb(kanji);
            kanjiDb.setNivel("1");
            kanjiRepository.insert(kanjiDb);

        }

        for (String kanji : kanjilvl2) {
            kanjiDb = new KanjiDb(kanji);
            kanjiDb.setNivel("2");
            kanjiRepository.insert(kanjiDb);
        }

        for (String kanji : kanjilvl3) {
            kanjiDb = new KanjiDb(kanji);
            kanjiDb.setNivel("3");
            kanjiRepository.insert(kanjiDb);
        }

        for (String kanji : kanjilvl4) {
            kanjiDb = new KanjiDb(kanji);
            kanjiDb.setNivel("4");
            kanjiRepository.insert(kanjiDb);
        }

        for (String kanji : kanjilvl5) {
            kanjiDb = new KanjiDb(kanji);
            kanjiDb.setNivel("5");
            kanjiRepository.insert(kanjiDb);
        }

        for (String kanji : kanjilvl6) {
            kanjiDb = new KanjiDb(kanji);
            kanjiDb.setNivel("6");
            kanjiRepository.insert(kanjiDb);
        }

    }
}