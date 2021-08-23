package kr.co.healthcare.selfDiagnosis.MainRecycler;

public class SelfMainData {
    private int ID;
    private String disease_name;
    private int num_of_questions;

    public SelfMainData(int id, String disease_name, int num_of_questions){
        this.ID = id;
        this.disease_name = disease_name;
        this.num_of_questions = num_of_questions;
    }

    public int getID() {
        return ID;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public int getNum_of_questions() {
        return num_of_questions;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public void setNum_of_questions(int num_of_questions) {
        this.num_of_questions = num_of_questions;
    }
}
