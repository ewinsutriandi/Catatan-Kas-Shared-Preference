package simple.example.catatankas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import simple.example.catatankas.model.Transaksi;

public class DaftarTransaksiAdapter extends ArrayAdapter<Transaksi> {
    Context context;

    public DaftarTransaksiAdapter(@NonNull Context context, @NonNull List<Transaksi> objects) {
        super(context, R.layout.row_transaksi, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txTgl;
        TextView txNilai;
        TextView txDeskripsi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Transaksi tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_transaksi,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txTgl = convertView.findViewById(R.id.row_tx_tgl_transaksi);
            viewHolder.txDeskripsi = convertView.findViewById(R.id.row_tx_desc_transaksi);
            viewHolder.txNilai = convertView.findViewById(R.id.row_tx_nilai_transaksi);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txTgl.setText(GenericUtility.DATE_FORMAT.format(tr.getTanggal()));
        viewHolder.txDeskripsi.setText(tr.getDeskripsi());
        if (tr.getJenis().equals(Transaksi.DEBIT)) {
            viewHolder.txNilai.setText("-"+GenericUtility.formatUang(tr.getNilai()));
        } else {
            viewHolder.txNilai.setText(GenericUtility.formatUang(tr.getNilai()));
        }
        return convertView;
    }
}
