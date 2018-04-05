package id_16109759_hdsd.sda_a5_seanheaslip;
/**
 * Copyright 2013 Square, Inc.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

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

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by seanh on 06/03/2018.
 *  During testing of app, use arraylist with hardcoded images.
 *  Had issues loading images due to the file locaion
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder>
{

   private Context mContext;
    private List<Expense> mData;
    private Dialog myDialog;


    public RecyclerViewAdapter(Context mContext, List<Expense> mData) {
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

                //use picasso API
                Picasso.with(mContext)
                        .load(mData.get(vHolder.getAdapterPosition()).getPhoto())
                        .fit()
                        .centerCrop()
                        .rotate(90)
                        .into(dialog_item_img);
               // dialog_item_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());
               // dialog_item_img.setImageResource();

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
        Expense expenseCurrent = mData.get(position);
        holder.textView_expType.setText(expenseCurrent.getExpenseType());
        holder.textView_description.setText(expenseCurrent.getExpDescription());
        holder.textView_date.setText(expenseCurrent.getDate());

       // holder.img.setImageResource(expenseCurrent.getPhoto());
        // Using Picasso to handle image
        Picasso.with(mContext)
                .load(expenseCurrent.getPhoto())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .rotate(90)
                .into(holder.img);

        //Change color of Even and Odd rows
        if(position %2 == 1){
            holder.item_contact.setBackgroundColor(Color.parseColor("#FFEB3B"));
        }
        else {
            holder.item_contact.setBackgroundColor(Color.parseColor("#CDDC39"));
        }

    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        public LinearLayout item_contact;
        public TextView textView_expType;
        public TextView textView_description;
        public TextView textView_date;
        public ImageView img;

        public viewHolder(View itemView)
        {
            super(itemView);
            item_contact = (LinearLayout) itemView.findViewById(R.id.contact_item_id);
            textView_expType = (TextView) itemView.findViewById(R.id.expense_type); //
            textView_description = (TextView) itemView.findViewById(R.id.expense_description); // description
            textView_date = (TextView) itemView.findViewById(R.id.date_id); //date
            img = (ImageView) itemView.findViewById(R.id.img_contact); //image

        }
    }
}
