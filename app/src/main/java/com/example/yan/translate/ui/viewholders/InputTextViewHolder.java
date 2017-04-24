package com.example.yan.translate.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.yan.translate.R;
import com.example.yan.translate.util.StringUtils;

// holder for source text to translate

public class InputTextViewHolder extends RecyclerView.ViewHolder {

    private EditText inputText;
    private Button translateButton;
    private ImageButton favouriteButton;

    public InputTextViewHolder(View v, final inputTextCallback inputCallback,
                               final favouriteCallback favCallback){
        super(v);
        inputText = (EditText) v.findViewById(R.id.source_input_text);
        translateButton = (Button) v.findViewById(R.id.translate_button);
        favouriteButton = (ImageButton) v.findViewById(R.id.favourite_Button);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.notEmpty(inputText.getText())) {
                    if(inputCallback!=null){
                        inputCallback.handle(inputText.getText().toString());
                        favouriteButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(StringUtils.notEmpty(inputText.getText())){
                    favCallback.handle();

                }

            }
        });
    }

    public ImageButton getFavouriteButton() {
        return favouriteButton;
    }

    public void setFavouriteButton(ImageButton favouriteButton) {
        this.favouriteButton = favouriteButton;
    }

    public EditText getInputText() {
        return inputText;
    }

    public void setInputText(EditText inputText) {
        this.inputText = inputText;
    }

    public Button getTranslateButton() {
        return translateButton;
    }

    public void setTranslateButton(Button translateButton) {
        this.translateButton = translateButton;
    }

    public interface inputTextCallback{
        void handle(String inputText);
    }

    public interface favouriteCallback{
        void handle();
    }
}
