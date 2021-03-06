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
    private final String definition;
    private final String cause;
    private final String symptom;
    private final String treatment;
    private final String precaution;

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
}