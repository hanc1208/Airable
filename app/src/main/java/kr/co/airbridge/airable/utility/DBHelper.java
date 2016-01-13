package kr.co.airbridge.airable.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dajung han on 2016-01-11.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "airable.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE process (no INTEGER PRIMARY KEY, name VARCHAR(20), time INTEGER, place_name VARCHAR(50), description VARCHAR(200), state INTEGER)");

        db.execSQL("INSERT INTO process VALUES(1, '공항 도착', 5, '장소1', '탑승 시간에 늦지 않도록 여유 있게 3시간 전에는 공항에 도착해주세요', 0)");
        db.execSQL("INSERT INTO process VALUES(2, '로밍', 4, '여객터미널 1층, 3층', '보안검색 또는 출국심사 완료 후 일반지역으로 나올 수 없으니 사전에 로밍을 받으세요', -1)");
        db.execSQL("INSERT INTO process VALUES(3, '여행자 보험', 5, '여객터미널 3층 B, C, D 체크인 카운터 부근', '여행자 보험에 가입하시면 여행 중 사고에 대한 보상을 받을 수 있습니다', -1)");
        db.execSQL("INSERT INTO process VALUES(4, '환전', 5, '여객터미널 1층, 3층', '공항 내 환전소 위치를 확인하여 환전하세요', -1)");
        db.execSQL("INSERT INTO process VALUES(5, '예방 접종', 5, '여객터미널 중앙 밀레니엄홀 2층 5구역', '착지 국가에 따라 접종증명서를 확인하는 경우가 있으므로 반드시 확인하시기 바랍니다', -1)");
        db.execSQL("INSERT INTO process VALUES(6, '동식물 검역', 5, '여객터미널 3층 7번 출입구 부근', '동식물을 가지고 있으신 분들은 검역 신고를 하세요', -1)");
        db.execSQL("INSERT INTO process VALUES(7, '문화재 신고', 7, '여객터미널 3층 출국장 3번 게이트 부근', '문화재의 국외 반출을 금지하고 있습니다. 문화재로 오인될 수 있는 물건을 반출할 때에도 확인(감정) 받아야 합니다', -1)");
        db.execSQL("INSERT INTO process VALUES(8, '탑승 수속', 5, '여객터미널 3층 0 체크인카운터', '해당 카운터로 이동해 탑승권을 받으세요', 0)");
        db.execSQL("INSERT INTO process VALUES(9, '수하물 보내기', 5, '여객터미널 3층 0 체크인카운터', '수하물을 위탁 처리하거나 기내에 휴대하여 탑승하게 됩니다. 수하물 관련 주의사항을 확인 해 보세요', 0)");
        db.execSQL("INSERT INTO process VALUES(10, '세관 신고', 5, '여객터미널 3층 D, J 체크인카운터 부근', '큰 금액의 현금이나 귀중품을 소지하신 분들은 세관 신고를 하세요', -1)");
        db.execSQL("INSERT INTO process VALUES(11, '대형 수화물 위탁', 5, '여객터미널 3층 5번, 10번 출입구 부근', '대형수하물은 체크인 카운터에서 요금을 지불한 후 세관신고 카운터에서 세관신고를 하시고 대형수하물 카운터에서 탁송하시면 됩니다', -1)");
        db.execSQL("INSERT INTO process VALUES(12, '병무 허가', 5, '여객터미널 3층 G 탑승구 부근', '병역 의무자가 국외를 여행하실 때는 병무 신고를 하셔야 합니다', -1)");
        db.execSQL("INSERT INTO process VALUES(13, '부가세 환급', 5, '여객터미널 3층 2번 세관신고대 부근', '외국인 관광객 등이 국외 반출 목적으로 지정된 사후면세 판매장에서 구입하는 3만원 이상의 재화에 대해 부가가치세 및 개별소비세 등을 환급해 주는 제도입니다', -1)");
        db.execSQL("INSERT INTO process VALUES(14, '자동출입국 심사 등록', 5, '여객터미널 3층 체크인카운터 F구역 부근', '일반 유인심사보다 신속하게 출입국심사가 가능합니다. 최초 한 번만 등록하시면 됩니다', -1)");
        db.execSQL("INSERT INTO process VALUES(15, '보안 검색', 5, '여객터미널 3층 출국장', '공항 이용객 및 항공기의 안전을 위해 보안검색을 실시하오니 보안검색 업무에 적극 협조하여 주시기 바랍니다', 0)");
        db.execSQL("INSERT INTO process VALUES(16, '출국 심사', 5, '여객터미널 3층 출입국심사대', '출입국 심사대에서 외국인의 입국허가여부 등을 결정하는 국가의 고유한 주권의 행사입니다', 0)");
        db.execSQL("INSERT INTO process VALUES(17, 'Fast Track', 5, '여객터미널 3층 FAST TRACK 전용 출국장', 'Fast Track은 교통약자 및 출입국 우대자를 위한 서비스로, 전용 출국장을 통해 편리하고 신속하게 출국 수속을 진행할 수 있는 서비스 입니다', -1)");
        db.execSQL("INSERT INTO process VALUES(18, '탑승구 이동', 5, '여객터미널 지하1층 셔틀트레인 탑승구', '티켓을 확인하신 후 (탑승구 101~132번) 셔틀트레인을 타고 탑승동으로 이동하시기 바랍니다', -1)");
        db.execSQL("INSERT INTO process VALUES(19, '탑승', 5, '[ 여객터미널 / 탑승동 ] 0번 탑승구', '항공기 출발 30분 전에 탑승을 시작하여 10분 전에 탑승이 마감되오니 늦지 않도록 주의하시기 바랍니다', 0)");

        db.execSQL("CREATE TABLE shop (no INTEGER PRIMARY KEY, title VARCHAR(30), info VARCHAR(200), location VARCHAR(50), floor INTEGER, time VARCHAR(20), tel VARCHAR(20), image INTEGER, mark INTEGER)");

        db.execSQL("INSERT INTO shop VALUES(1, '타코벨', '타코벨, 음료 등', '(탑승동) 115번 Gate 부근', 33, '06 : 00 ~ 22 : 00', '032-743-6259', 1, 0)");
        db.execSQL("INSERT INTO shop VALUES(2, '버거킹', '햄버거류, 음료 등', '교통센터 지하1층 서측', 11, '07 : 00 ~ 22 : 00', '032-743-0322', 2, 0)");
        db.execSQL("INSERT INTO shop VALUES(3, '롯데리아', '햄버거류, 음료, 커피, 아이스크림 등', '체크인카운터 D부근', 23, '00 : 00 ~ 00 : 00', '032-743-5680', 2, 0)");
        db.execSQL("INSERT INTO shop VALUES(4, '케이 스트리트 푸드', '분식, 라면, 김밥, 떡볶이, 튀김, 순대, 어묵, 커피, 에이드', '(일반지역)여객터미널 4층 동편(D카운터 앞)', 24, '06 : 00 ~ 22 : 00', '032-743-6970', 3, 0)");
        db.execSQL("INSERT INTO shop VALUES(5, '오성쉐이크', '쉐이크, 수제 햄버거, 커피, 음료', '(일반지역)여객터미널 4층 동편(D카운터 앞)', 24, '06 : 00 ~ 22 : 00', '032-743-3454', 2, 0)");
        db.execSQL("INSERT INTO shop VALUES(6, '모스버거', '햄버거, 패스트푸드, 커피, 차, 음료', '(면세구역) 43번 Gate 부근', 23, '06 : 00 ~ 22 : 00', '032-743-5837', 2, 0)");
        db.execSQL("INSERT INTO shop VALUES(7, '스킨푸드', '화장품', '(여객터미널) 1층 9번 출입문 부근', 11, '06 : 30 ~ 21 : 00', '032-743-4196', 4, 0)");
        db.execSQL("INSERT INTO shop VALUES(8, '[롯데면세점]화장품·향수', '화장품', '(면세구역)탑승동 중앙 G117 부근', 33, '00 : 00 ~ 00 : 00', '032-743-7870', 4, 0)");
        db.execSQL("INSERT INTO shop VALUES(9, '에뛰드하우스', '화장품', '교통센터 서편', 10, '07 : 00 ~ 21 : 00', '032-743-4791', 4, 0)");
        db.execSQL("INSERT INTO shop VALUES(10, 'Travel Air', '가방, 샌들 등의 여행용품, 벨트, 지갑, 핸드백등의 피혁제품', '(여객터미널) 지하 1층 서편', 20, '09 : 00 ~ 20 : 00', '032-743-8483', 5, 0)");
        db.execSQL("INSERT INTO shop VALUES(11, '샘소나이트', '여행용 가방, 여행용품', '(여객터미널) 3층 체크인카운터 G 부근', 23, '06 : 30 ~ 21 : 00', '032-743-2366', 5, 0)");
        db.execSQL("INSERT INTO shop VALUES(12, '샌디캐스트', '반려동물 조각품', '교통센터 중앙', 10, '07 : 00 ~ 21 : 00', '032-743-7032', 5, 0)");
        db.execSQL("INSERT INTO shop VALUES(13, '에스젠1', '지상 최대의 소프트웨어 멤버-쉽', '향군타워', 40, '00 : 00 ~ 24 : 00', '010-3382-9303', 0, 0)");
        db.execSQL("INSERT INTO shop VALUES(14, '에스젠2', '지상 최대의 소프트웨어 멤버-쉽', '향군타워', 40, '00 : 00 ~ 24 : 00', '010-3382-9304', 0, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}
