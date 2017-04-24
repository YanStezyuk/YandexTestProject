package com.example.yan.translate.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yan.translate.R;

// holder for errors, etc

public class SimpleViewHolder extends RecyclerView.ViewHolder {

    private TextView label;

    public SimpleViewHolder(View v){
        super(v);
        label = (TextView) v.findViewById(R.id.simpleLabel);
    }

    public TextView getLabel() {
        return label;
    }

    public void setLabel(TextView label) {
        this.label = label;
    }
}
