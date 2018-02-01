package com.MainTest;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Wudi
 * @Description:
 * @date 18:19  2018/1/8
 */
@Data
public class Employee {

    @SerializedName("empID")
    private int id;
    private String name;
    private boolean permanent;
    private Address address;
    private long[] phoneNumbers;
    private String role;
    private List<String> cities;
    private Map<String, String> properties;
}
