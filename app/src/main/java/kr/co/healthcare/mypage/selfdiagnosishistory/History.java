package kr.co.healthcare.mypage.selfdiagnosishistory;

public class History {
    int label;
    int count;
    int color;

    public History(){
        this.count = 0;
    }

    public History(int label, int count, int color) {
        this.label = label;
        this.count = count;
        this.color = color;
    }

    public int getLabel() {
        return label;
    }

    public int getCount() {
        return count;
    }

    public int getColor() {
        return color;
    }
}