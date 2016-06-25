
package com.prolan.cars.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pojo {

    @SerializedName("makes")
    @Expose
    private List<Make> makes = new ArrayList<Make>();
    @SerializedName("makesCount")
    @Expose
    private Integer makesCount;

    /**
     * 
     * @return
     *     The makes
     */
    public List<Make> getMakes() {
        return makes;
    }

    /**
     * 
     * @param makes
     *     The makes
     */
    public void setMakes(List<Make> makes) {
        this.makes = makes;
    }

    /**
     * 
     * @return
     *     The makesCount
     */
    public Integer getMakesCount() {
        return makesCount;
    }

    /**
     * 
     * @param makesCount
     *     The makesCount
     */
    public void setMakesCount(Integer makesCount) {
        this.makesCount = makesCount;
    }

}
