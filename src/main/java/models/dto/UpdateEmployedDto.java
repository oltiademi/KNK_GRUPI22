package models.dto;

public class UpdateEmployedDto {
    private String id;
    private String emri;
    private String mbiemri;
    private String gjinia;
    private String titulli;
    private String drejtimi;
    private String profesioni;
    private String kompania;

    public UpdateEmployedDto(String id, String emri, String mbiemri, String gjinia, String titulli, String drejtimi, String profesioni, String kompania) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.gjinia = gjinia;
        this.titulli = titulli;
        this.drejtimi = drejtimi;
        this.profesioni = profesioni;
        this.kompania = kompania;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getGjinia() {
        return gjinia;
    }

    public void setGjinia(String gjinia) {
        this.gjinia = gjinia;
    }

    public String getTitulli() {
        return titulli;
    }

    public void setTitulli(String titulli) {
        this.titulli = titulli;
    }

    public String getDrejtimi() {
        return drejtimi;
    }

    public void setDrejtimi(String drejtimi) {
        this.drejtimi = drejtimi;
    }

    public String getProfesioni() {
        return profesioni;
    }

    public void setProfesioni(String profesioni) {
        this.profesioni = profesioni;
    }

    public String getKompania() {
        return kompania;
    }

    public void setKompania(String kompania) {
        this.kompania = kompania;
    }
}
