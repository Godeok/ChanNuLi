package kr.co.healthcare.self_diagnosis.MainRecycler;

public class SelfMainData {
    private int ID;
    private String disease_name;
    private int num_of_qeustions;

    public SelfMainData(int id, String disease_name, int num_of_qeustions){
        this.ID = id;
        this.disease_name = disease_name;
        this.num_of_qeustions = num_of_qeustions;
    }

    public int getID() {
        return ID;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public int getNum_of_qeustions() {
        return num_of_qeustions;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public void setNum_of_qeustions(int num_of_qeustions) {
        this.num_of_qeustions = num_of_qeustions;
    }
}
