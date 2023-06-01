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
    public String getEmri() {
        return emri;
    }
    public String getMbiemri() {
        return mbiemri;
    }
    public String getGjinia() {
        return gjinia;
    }
    public String getTitulli() {
        return titulli;
    }
    public String getDrejtimi() {
        return drejtimi;
    }
    public String getProfesioni() {
        return profesioni;
    }
    public String getKompania() {
        return kompania;
    }
}
