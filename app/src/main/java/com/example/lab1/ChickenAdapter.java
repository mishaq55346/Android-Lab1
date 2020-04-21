package com.example.lab1;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChickenAdapter extends RecyclerView.Adapter {
    private final List<NumItem> numItems;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
            RuleBasedNumberFormat.SPELLOUT);
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void FillNum(int i){
        numItems.get(i).full_num = nf.format(numItems.get(i).num);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ChickenAdapter(ArrayList nums) {
        numItems = nums;
        for (int i = 0; i < nums.size(); i++) {
            FillNum(i);
        }
    }

    @Override
    public ChickenItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChickenItem(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_numlist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position % 2 == 0)
            holder.itemView.setBackgroundColor(Color.WHITE);
        else
            holder.itemView.setBackgroundColor(Color.GRAY);
        ((ChickenItem)(holder)).text_num.setText(String.format("%d", numItems.get(position).num));
        ((ChickenItem)(holder)).text_full_num.setText(numItems.get(position).full_num);
    }

    @Override
    public int getItemCount() {
        return numItems != null ? numItems.size() : 0;
    }
    public void updateList(NumItem user) {
        insertItem(user);
    }

    // Método responsável por inserir um novo usuário na lista
    //e notificar que há novos itens.
    private void insertItem(NumItem user) {
        numItems.add(user);
        notifyItemInserted(getItemCount());
    }

    void loadMore(){

    }
}
