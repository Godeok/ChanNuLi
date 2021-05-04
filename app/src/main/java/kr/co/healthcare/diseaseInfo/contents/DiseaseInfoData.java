package kr.co.healthcare.diseaseInfo.contents;

public class DiseaseInfoData {
    private final String title;
    private final String contents;

    public DiseaseInfoData(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}