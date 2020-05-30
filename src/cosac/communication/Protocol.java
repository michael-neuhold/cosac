package cosac.communication;

import java.io.Serializable;

public enum Protocol implements Serializable {
    GET_USER_DATA_SETS,
    GET_FOOD_DATA_SETS,
    GET_RESTRICTION_DATA_SETS,
    GET_SECTION_DATA_SETS,
    SET_USER_DATA_SETS,
    SET_FOOD_DATA_SETS,
    SET_RESTRICTION_DATA_SETS,
    SET_SECTION_DATA_SETS
}
