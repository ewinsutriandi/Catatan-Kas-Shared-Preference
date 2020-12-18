package simple.example.catatankas.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Transaksi {
    public static final String DEBIT="DEBIT";
    public static final String KREDIT="KREDIT";
    private String id;
    private Date tanggal;
    private String deskripsi;
    private double nilai;
    private String jenis;

    public Transaksi() {
        this.id = UUID.randomUUID().toString();
        this.tanggal = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public static Transaksi fromJSONObject(JSONObject obj) {
        Transaksi tr = new Transaksi();
        try {
            tr.setId(obj.getString("id"));
            tr.setTanggal(new Date(obj.getLong("tanggal")));
            tr.setDeskripsi(obj.getString("deskripsi"));
            tr.setNilai(obj.getDouble("nilai"));
            tr.setJenis(obj.getString("jenis"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id",this.id);
            jsonObj.put("tanggal",this.tanggal.getTime());
            jsonObj.put("jenis",this.jenis);
            jsonObj.put("nilai",this.nilai);
            jsonObj.put("deskripsi",this.deskripsi);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
