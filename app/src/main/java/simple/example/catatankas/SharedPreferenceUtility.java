package simple.example.catatankas;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simple.example.catatankas.model.Transaksi;

public class SharedPreferenceUtility {

    private static final String PREF_FILE = "simple.example.catatankas.DATA";
    // PREF_FILE -> Nama file utk penyimpanan,
    // biasanya menyertakan app.id agar tidak bentrok dgn app lain
    private static final String TRANS_KEY = "TRANS"; // KEY utk daftar transaksi
    private static final String USER_NAME_KEY = "USERNAME"; // KEY untuk nama user

    private static SharedPreferences getSharedPref(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE);
        return sharedPref;
    }
    /*
        Ambil data username dari Shared Preference
     */
    public static String getUserName(Context ctx) {
        return getSharedPref(ctx).getString(USER_NAME_KEY,"NO NAME");
    }
    /*
        Simpan data username ke Shared Preference
     */
    public static void saveUserName(Context ctx, String userName) {
        Log.d("SH PREF","Change user name to"+userName);
        getSharedPref(ctx).edit().putString(USER_NAME_KEY,userName).apply();
    }
    /*
        Ambil data transaksi dari Shared Preference
        Perhatikan bahwa data disimpan dalam format JSON String
     */
    public static List<Transaksi> getAllTransaksi(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(TRANS_KEY, null);
        List<Transaksi> trs = new ArrayList<Transaksi>();
        if (jsonString != null) {
            Log.d("SH PREF","json string is:"+jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    trs.add(Transaksi.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(trs,(transaksi, t1) -> {
            return transaksi.getTanggal().compareTo(t1.getTanggal());
        }); // urutkan transaksi berdasarkan tanggal
        return trs;
    }
    /*
        Simpan data transaksi ke Shared Preference
        Perhatikan bahwa data disimpan dalam format JSON String
     */
    private static void saveAllTransaksi(Context ctx, List<Transaksi> trs) {
        List<JSONObject> jsonTrs = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();
        for (Transaksi tr : trs) {
            jsonArr.put(tr.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(TRANS_KEY,jsonArr.toString()).apply();
    }

    /*
        Tambah data transaksi baru ke Shared Preference
     */
    public static void addTransaksi(Context ctx, Transaksi tr) {
        List<Transaksi> trs = getAllTransaksi(ctx);
        trs.add(tr);
        saveAllTransaksi(ctx,trs);
    }

    public static void editTransaksi(Context ctx, Transaksi tr) {
        List<Transaksi> trs = getAllTransaksi(ctx);
        for (Transaksi tre:trs) {
            if (tr.getId().equals(tre.getId())) {
                trs.remove(tre);
                trs.add(tr);
            }
        }
        saveAllTransaksi(ctx,trs);
    }

    public static void deleteTransaksi(Context ctx, String id) {
        List<Transaksi> trs = getAllTransaksi(ctx);
        for (Transaksi tr:trs) {
            if (tr.getId().equals(id)) {
                trs.remove(tr);
            }
        }
        saveAllTransaksi(ctx,trs);
    }

}
