package models;

public class Employed {
}
    String emri;
    String mbiemri;
    String gjinia;
    String titulli;
    String drejtimi;
    String profesioni;
    String kompania;

    public Employed(String emri, String mbiemri, String gjinia, String titulli, String drejtimi, String profesioni, String kompania) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.gjinia = gjinia;
        this.titulli = titulli;
        this.drejtimi = drejtimi;
        this.profesioni = profesioni;
        this.kompania = kompania;
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
