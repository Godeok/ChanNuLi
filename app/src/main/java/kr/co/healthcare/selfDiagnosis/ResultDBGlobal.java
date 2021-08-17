package kr.co.healthcare.selfDiagnosis;

//ResultDAO의 경우 직접 숫자 수정해주기

public class ResultDBGlobal {
    private static int range_safe_7 = 2;
    private static int range_warning_7 = 5;

    private static int range_safe_10 = 3;
    private static int range_warning_10 = 6;

    private static int range_safe_15 = 4;
    private static int range_warning_15 = 7;

    public static int getRange_safe(int diseaseNum) {
        if(diseaseNum==4 || diseaseNum==6) return range_safe_15;
        else if(diseaseNum==1) return range_safe_7;
        else return range_safe_10;
    }

    public static int getRange_warning(int diseaseNum) {
        if(diseaseNum==4 || diseaseNum==6) return range_warning_15;
        else if(diseaseNum==1) return range_warning_7;
        else return range_warning_10;
    }
}
