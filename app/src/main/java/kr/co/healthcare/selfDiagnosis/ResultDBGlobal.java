package kr.co.healthcare.selfDiagnosis;

//ResultDAO의 경우 직접 숫자 수정해주기

public class ResultDBGlobal {
    private static int range_safe = 3;
    private static int range_warning = 5;

    public static int getRange_safe() {
        return range_safe;
    }

    public static int getRange_warning() {
        return range_warning;
    }

    public void setRange_safe(int range_safe) {
        this.range_safe = range_safe;
    }

    public void setRange_warning(int range_warning) {
        this.range_warning = range_warning;
    }
}
