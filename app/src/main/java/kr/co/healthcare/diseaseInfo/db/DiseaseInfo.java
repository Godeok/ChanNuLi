package kr.co.healthcare.diseaseInfo.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DiseaseInfo {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String definition;
    private String cause;
    private String symptom;
    private String treatment;
    private String precaution;

    public DiseaseInfo(@NonNull int id, String name, String definition, String cause,
                   String symptom, String treatment, String precaution) {
        this.id = id; this.name = name;  this.definition = definition;  this.cause = cause;
        this.symptom = symptom; this.treatment = treatment; this.precaution = precaution;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public String getCause() {
        return cause;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getPrecaution() {
        return precaution;
    }

    @NonNull
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setPrecaution(String precaution) {
        this.precaution = precaution;
    }
}