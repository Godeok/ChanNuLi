package kr.co.healthcare.selfDiagnosis.QuestionDB;

//Contract
public class Questions {

    public String num;
    public String question;

    public void setNum(String num){
        this.num = num;
    }

    public void setQuestions(String question){
        this.question = question;
    }

    public String getNum() {
        return num;
    }

    public String getQuestion() {
        return question;
    }
}
