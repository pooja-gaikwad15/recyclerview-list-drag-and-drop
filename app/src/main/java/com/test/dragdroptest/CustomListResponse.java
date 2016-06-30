package com.test.dragdroptest;

/*import org.apache.commons.lang3.builder.ToStringBuilder;*/

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pooja Gaikwad on 6/22/2016.
 */
public class CustomListResponse {
    public String userId;
    public String email;
    public List<CustomList> customList = new ArrayList<CustomList>();
    public List<CustomList> previousDayCustomList = new ArrayList<CustomList>();
    public int errorCode;
    public String errorMessage;

    /**
     * No args constructor for use in serialization
     */
    public CustomListResponse() {
    }

    /**
     * @param errorMessage
     * @param customList
     * @param email
     * @param userId
     * @param errorCode
     */
    public CustomListResponse(String userId, String email, List<CustomList> customList,
                              List<CustomList> previousDayCustomList,
                              int errorCode, String errorMessage) {
        this.userId = userId;
        this.email = email;
        this.customList = customList;
        this.previousDayCustomList = previousDayCustomList;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }*/

}
