package models.dto;

import java.util.ArrayList;
import java.util.List;

public class EmployedFilter {
    String id;
    String emri;
    String mbiemri;
    String gjinia;
    String titulli;
    String drejtimi;
    String profesioni;
    String kompania;

    public EmployedFilter(String id,String emri, String mbiemri,String gjinia, String titulli, String drejtimi, String profesioni, String kompania) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.gjinia = gjinia;
        this.titulli = titulli;
        this.drejtimi = drejtimi;
        this.profesioni = profesioni;
        this.kompania = kompania;
    }

    public String getIdQuery() {
        return " AND (id LIKE ?";
    }
    public String getEmriQuery() {
        return " OR emri LIKE ?";
    }public String getMbiemriQuery() {
        return " OR mbiemri LIKE ?";
    }
    public String getGjiniaQuery() {
        return " OR gjinia LIKE ?";
    }public String getTitulliQuery() {
        return " OR titulli LIKE ?";
    }
    public String getDrejtimiQuery() {
        return " OR drejtimi LIKE ?";
    }public String getProfesioniQuery() {
        return " OR profesioni LIKE ?";
    }
    public String getKompaniaQuery() {
        return " OR kompania LIKE ?)";
    }

    public String getFilterQuery() {
        StringBuilder queryBuilder = new StringBuilder();

        if (id != null && !id.isEmpty()) {
            queryBuilder.append(getIdQuery());
        }
        if (emri != null && !emri.isEmpty()) {
            queryBuilder.append(getEmriQuery());
        }
        if (mbiemri != null && !mbiemri.isEmpty()) {
            queryBuilder.append(getMbiemriQuery());
        }
        if (gjinia != null && !gjinia.isEmpty()) {
            queryBuilder.append(getGjiniaQuery());
        }
        if (titulli != null && !titulli.isEmpty()) {
            queryBuilder.append(getTitulliQuery());
        }
        if (drejtimi != null && !drejtimi.isEmpty()) {
            queryBuilder.append(getDrejtimiQuery());
        }
        if (profesioni != null && !profesioni.isEmpty()) {
            queryBuilder.append(getProfesioniQuery());
        }
        if (kompania != null && !kompania.isEmpty()) {
            queryBuilder.append(getKompaniaQuery());
        }

        return queryBuilder.toString();
    }
    public List<Object> getFilterParams() {
        List<Object> params = new ArrayList<>();

        if (id != null && !id.isEmpty()) {
            params.add("%" + id + "%");
        }
        if (emri != null && !emri.isEmpty()) {
            params.add("%" + emri + "%");
        }
        if (mbiemri != null && !mbiemri.isEmpty()) {
            params.add("%" + mbiemri + "%");
        }
        if (gjinia != null && !gjinia.isEmpty()) {
            params.add("%" + gjinia + "%");
        }
        if (titulli != null && !titulli.isEmpty()) {
            params.add("%" + titulli + "%");
        }
        if (drejtimi != null && !drejtimi.isEmpty()) {
            params.add("%" + drejtimi + "%");
        }
        if (profesioni != null && !profesioni.isEmpty()) {
            params.add("%" + profesioni + "%");
        }
        if (kompania != null && !kompania.isEmpty()) {
            params.add("%" + kompania + "%");
        }

        return params;
    }
}
