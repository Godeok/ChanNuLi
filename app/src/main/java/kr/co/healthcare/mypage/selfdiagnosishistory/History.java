package kr.co.healthcare.mypage.selfdiagnosishistory;

public class History {
    int label;
    int count;

    public History(){
        this.count = 0;
    }

    public History(int label, int count) {
        this.label = label;
        this.count = count;
    }

    public int getLabel() {
        return label;
    }

    public int getCount() {
        return count;
    }
}