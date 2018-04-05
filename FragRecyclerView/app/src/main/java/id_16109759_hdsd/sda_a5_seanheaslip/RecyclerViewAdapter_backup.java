package id_16109759_hdsd.sda_a5_seanheaslip;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by seanh on 06/03/2018.
 *  During testing of app, use arraylist with hardcoded images.
 *  Had issues loading images due to the file locaion
 */

public class RecyclerViewAdapter_backup extends RecyclerView.Adapter<RecyclerViewAdapter_backup.viewHolder>
{

    Context mContext;
    List<Expenses> mData;
    Dialog myDialog;


    public RecyclerViewAdapter_backup(Context mContext, List<Expenses> mData) {
        this.mContext = mContext;
        this.mData =mData;
    }

//    public RecyclerViewAdapter(FragmentExpenses.OnItemSelectedInterface listener)
//    {
//    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, final int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_expenses, parent, false);
        final viewHolder vHolder = new viewHolder(view);


        //Dialog ini
        myDialog= new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_clicked_expense);

        vHolder.item_contact.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TextView dialog_title_tv = (TextView) myDialog.findViewById(R.id.dialog_expense_type_id);
                TextView dialog_description_tv = (TextView) myDialog.findViewById(R.id.dialog_expense_description_id);
                ImageView dialog_item_img = (ImageView) myDialog.findViewById(R.id.dialog_img_expense_id);
                dialog_title_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_description_tv.setText(mData.get(vHolder.getAdapterPosition()).getExpDescription());
                dialog_item_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());

                Toast.makeText(mContext, "Testing Clicked list item" +
                        String.valueOf(vHolder.getAdapterPosition()),
                        Toast.LENGTH_SHORT).show();
                myDialog.show();

            }
        });

        return vHolder;
    }

    //Look into line dividers
    //https://inducesmile.com/android/how-to-set-recycleview-item-row-background-color-in-android/

    /**
     * Referecnce for changing row color:
     * Date: 08/03/2018
     * https://stackoverflow.com/questions/40603251/
     * /changing-colors-to-recyclerview-items
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(viewHolder holder, int position)
    {
        if(position %2 == 1){
            holder.item_contact.setBackgroundColor(Color.parseColor("#FFEB3B"));
//            holder.textView_name.setText(mData.get(position).getName());
//            holder.textView_description.setText(mData.get(position).getExpDescription());
//            holder.img.setImageResource(mData.get(position).getPhoto());
        }
        else {
            holder.item_contact.setBackgroundColor(Color.parseColor("#CDDC39"));
//            holder.textView_name.setText(mData.get(position).getName());
//            holder.textView_description.setText(mData.get(position).getExpDescription());
//            holder.img.setImageResource(mData.get(position).getPhoto());
        }
        holder.textView_name.setText(mData.get(position).getName());
        holder.textView_phone.setText(mData.get(position).getExpDescription());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_contact;
        private TextView textView_name;
        private TextView textView_phone;
        private ImageView img;

        public viewHolder(View itemView)
        {
            super(itemView);
            item_contact = (LinearLayout) itemView.findViewById(R.id.contact_item_id);
            textView_name = (TextView) itemView.findViewById(R.id.expense_type);
            textView_phone = (TextView) itemView.findViewById(R.id.expense_description);
            img = (ImageView) itemView.findViewById(R.id.img_contact);

        }
    }
}
