package cosac.rmi.config;

public class RMIConfig {

    // RMI
    public static final String SERVER = "localhost";
    public static final int RMI_PORT = 1099;

    // DB
    public static final String USERNAME = "root";
    public static final String PASSWORD = null;
    public static final String CONNECTION_STRING = "jdbc:mysql://" + SERVER + "/CosacDB?autoReconnect=true&useSSL=false";

    // SETUP SERVICE ENDPOINTS
    public static final String ENDPOINT = "rmi://" + SERVER + ":" + RMI_PORT;
    public static final String ENDPOINT_USER_SERVICE = ENDPOINT + "/UserService";
    public static final String ENDPOINT_FOOD_SERVICE = ENDPOINT + "/FoodService";
    public static final String ENDPOINT_ORDER_SERVICE = ENDPOINT + "/OrderService";
    public static final String ENDPOINT_RESTRICTION_SERVICE = ENDPOINT + "/RestrictionService";
    public static final String ENDPOINT_SECTION_SERVICE = ENDPOINT + "/SectionService";

    // CLIENT SERVICE CONNECTION
    public static final String CONNECTION_TO_SERVICE = "rmi://" + SERVER;
    public static final String CONNECTION_USER_SERVICE = CONNECTION_TO_SERVICE + "/UserService";
    public static final String CONNECTION_SECTION_SERVICE = CONNECTION_TO_SERVICE + "/SectionService";
    public static final String CONNECTION_FOOD_SERVICE = CONNECTION_TO_SERVICE + "/FoodService";
    public static final String CONNECTION_RESTRICTION_SERVICE = CONNECTION_TO_SERVICE + "/RestrictionService";
    public static final String CONNECTION_ORDER_SERVICE = CONNECTION_TO_SERVICE + "/OrderService";

}
