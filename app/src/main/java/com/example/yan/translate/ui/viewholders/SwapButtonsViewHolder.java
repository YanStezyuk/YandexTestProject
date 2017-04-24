package com.example.yan.translate.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.yan.translate.R;

// holder for target and source languages, swap button

public class SwapButtonsViewHolder extends RecyclerView.ViewHolder {

    private Spinner sourceSpinner;
    private Spinner targetSpinner;
    private ImageView swapButton;

    private int sourceLangIdx;
    private int targetLangIdx;

    public SwapButtonsViewHolder(View v, final sourceLanguageCallback sourceCallback,
                                 final targetLanguageCallback targetCallback) {
        super(v);

        sourceSpinner = (Spinner) v.findViewById(R.id.source_lang_spinner);
        targetSpinner = (Spinner) v.findViewById(R.id.target_lang_spinner);
        swapButton = (ImageView) v.findViewById(R.id.swap_lang_button);

        sourceSpinner.setAdapter(createSpinnerAdapter());
        sourceSpinner.setVisibility(View.VISIBLE);
        targetSpinner.setAdapter(createSpinnerAdapter());
        targetSpinner.setVisibility(View.VISIBLE);
        sourceLangIdx = 0;
        targetLangIdx = 1;
        sourceSpinner.setSelection(sourceLangIdx);
        targetSpinner.setSelection(targetLangIdx);

        sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceLangIdx = position;
                if (sourceCallback != null) {
                    sourceCallback.handle(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }

        });

        targetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                targetLangIdx = position;
                if (targetCallback != null) {
                    targetCallback.handle(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }

        });

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = sourceSpinner.getSelectedItemPosition();
                sourceSpinner.setSelection(targetSpinner.getSelectedItemPosition());
                targetSpinner.setSelection(selected);
            }
        });

    }

    private ArrayAdapter createSpinnerAdapter() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                itemView.getContext(), R.array.langlist,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public Spinner getSourceSpinner() {
        return sourceSpinner;
    }

    public void setSourceSpinner(Spinner sourceSpinner) {
        this.sourceSpinner = sourceSpinner;
    }

    public Spinner getTargetSpinner() {
        return targetSpinner;
    }

    public void setTargetSpinner(Spinner targetSpinner) {
        this.targetSpinner = targetSpinner;
    }

    public ImageView getSwapButton() {
        return swapButton;
    }

    public void setSwapButton(ImageView swapButton) {
        this.swapButton = swapButton;
    }

    public interface sourceLanguageCallback {
        void handle(int selectedLanguageIdx);
    }

    public interface targetLanguageCallback {
        void handle(int selectedLanguageIdx);
    }

}
