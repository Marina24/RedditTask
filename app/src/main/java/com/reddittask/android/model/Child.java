
package com.reddittask.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("data")
    @Expose
    private DataInside data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public DataInside getData() {
        return data;
    }

    public void setData(DataInside data) {
        this.data = data;
    }

}
