package com.test.dragdroptest;

/*import org.apache.commons.lang3.builder.ToStringBuilder;*/

import java.io.Serializable;

/**
 * Created by Pooja Gaikwad on 6/22/2016.
 */
public class CustomList implements Serializable {

    public static final long serialID = 1234L;

    public String id;
    public String name;
    public String address;
    public String mobile;
    public double lat;
    public double lng;
    public String estimatedTime;

    /**
     * No args constructor for use in serialization
     */
    public CustomList() {
    }

    /**
     * @param id
     * @param estimatedTime
     * @param address
     * @param name
     * @param lng
     * @param lat
     * @param mobile
     */
    public CustomList(String id, String name, String address,
                      String mobile, double lat, double lng, String estimatedTime) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.lat = lat;
        this.lng = lng;
        this.estimatedTime = estimatedTime;
    }

   /* @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }*/
}
