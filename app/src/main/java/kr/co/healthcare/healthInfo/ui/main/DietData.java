package kr.co.healthcare.healthInfo.ui.main;

public class DietData {
    private String title;
    private String description;
    private boolean isExpanded;

    DietData(String title, String description){
        this.title = title;
        this.description = description;
        this.isExpanded = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
