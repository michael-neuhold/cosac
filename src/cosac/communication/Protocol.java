package cosac.communication;

import java.util.HashMap;
import java.util.Map;

public enum Protocol {
    GET_USER_DATA_SETS(100),
    GET_FOOD_DATA_SETS(101),
    GET_RESTRICTION_DATA_SETS(102),
    GET_SECTION_DATA_SETS(103),
    GET_ORDER_DATA_SETS(104),
    SET_USER_DATA_SETS(200),
    SET_FOOD_DATA_SETS(201),
    SET_RESTRICTION_DATA_SETS(202),
    SET_SECTION_DATA_SETS(203),
    SET_ORDER_DATA_SETS(204);

    private final int code;
    private static Map map = new HashMap<>();

    static {
        for (Protocol protocol : Protocol.values()) {
            map.put(protocol.code, protocol);
        }
    }

    Protocol(int code) { this.code = code; }

    public int getValue() {return code;}

    public static Protocol valueOf(int code) {
        return (Protocol) map.get(code);
    }
}
