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
    private static final String CONNECTION_TO_SERVICE = "rmi://" + SERVER;
    private static final String CONNECTION_USER_SERVICE = CONNECTION_TO_SERVICE + "/UserService";
    private static final String CONNECTION_SECTION_SERVICE = CONNECTION_TO_SERVICE + "/SectionService";
    private static final String CONNECTION_FOOD_SERVICE = CONNECTION_TO_SERVICE + "/FoodService";
    private static final String CONNECTION_RESTRICTION_SERVICE = CONNECTION_TO_SERVICE + "/RestrictionService";

}
