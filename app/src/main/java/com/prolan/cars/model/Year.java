
package com.prolan.cars.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Year {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("year")
    @Expose
    private Integer year;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

}
