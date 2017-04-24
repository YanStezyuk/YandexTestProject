package com.example.yan.translate.api.model;

import java.util.List;


// Yandex Dict answer data model

public class Article {

    private List<Definition> def;

    public static class Definition {

        private String text;
        private String pos;
        private List<Translation> tr;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public List<Translation> getTr() {
            return tr;
        }

        public void setTr(List<Translation> tr) {
            this.tr = tr;
        }
    }

    public static class Translation {

        private String text;
        private String pos;
        private List<Synonym> syn;
        private List<Meaning> mean;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public List<Synonym> getSyn() {
            return syn;
        }

        public void setSyn(List<Synonym> syn) {
            this.syn = syn;
        }

        public List<Meaning> getMean() {
            return mean;
        }

        public void setMean(List<Meaning> mean) {
            this.mean = mean;
        }
    }

    public static class Synonym {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class Meaning {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public List<Definition> getDef() {
        return def;
    }

    public void setDef(List<Definition> def) {
        this.def = def;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int code;

}
