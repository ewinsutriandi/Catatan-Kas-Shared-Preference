package simple.example.catatankas;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
/*
    Class ini berisi variabel dan method pembantu yang digunakan untuk keperluan formatting
    Variabel dan method yang ada di sini perlu dipisah karena tidak terkait langsung dengan logic
 */

public class GenericUtility {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
    public static String formatUang(double nilai) {
        DecimalFormat formatUang;
        formatUang = new DecimalFormat("#,###.00");
        DecimalFormatSymbols s = new DecimalFormatSymbols();
        s.setGroupingSeparator('.');
        s.setDecimalSeparator(',');
        formatUang.setDecimalFormatSymbols(s);
        return formatUang.format(nilai);
    }
}
