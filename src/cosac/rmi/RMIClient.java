package cosac.rmi;

import cosac.model.FoodData;
import cosac.model.RestrictionData;
import cosac.model.SectionData;
import cosac.model.UserData;
import cosac.rmi.service.food.FoodServiceable;
import cosac.rmi.service.restriction.RestrictionServiceable;
import cosac.rmi.service.section.SectionServiceable;
import cosac.rmi.service.user.UserServiceable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RMIClient {

    private static final String SERVER = "localhost";
    private static final String CONNECTION_STRING = "rmi://" + SERVER;
    private static final String CONNECTION_USER_SERVICE = CONNECTION_STRING + "/UserService";
    private static final String CONNECTION_SECTION_SERVICE = CONNECTION_STRING + "/SectionService";
    private static final String CONNECTION_FOOD_SERVICE = CONNECTION_STRING + "/FoodService";
    private static final String CONNECTION_RESTRICTION_SERVICE = CONNECTION_STRING + "/RestrictionService";

    public static void main(String[] args) {
        System.out.println("lookup for " + CONNECTION_USER_SERVICE);
        try {

            // user test
            UserServiceable userService = (UserServiceable) Naming.lookup(CONNECTION_USER_SERVICE);
            ArrayList<UserData> users = userService.getAllUsers();
            for(UserData userData : users) {
                System.out.println("client -> " + userData);
            }

            // section test
            SectionServiceable sectionService = (SectionServiceable) Naming.lookup(CONNECTION_SECTION_SERVICE);
            ArrayList<SectionData> sections = sectionService.getAllSection();
            for(SectionData section : sections) {
                System.out.println("client -> " + section);
            }

            // food test
            FoodServiceable foodService = (FoodServiceable) Naming.lookup(CONNECTION_FOOD_SERVICE);
            ArrayList<FoodData> food = foodService.getAllFood();
            for(FoodData f : food) {
                System.out.println("client -> " + f);
            }

            // restriction test
            RestrictionServiceable restrictionServic = (RestrictionServiceable) Naming.lookup(CONNECTION_RESTRICTION_SERVICE);
            ArrayList<RestrictionData> restrictions = restrictionServic.getAllRestrictions();
            for(RestrictionData restriction : restrictions) {
                System.out.println("client -> " + restriction);
            }


        } catch (MalformedURLException | RemoteException | NotBoundException exc) {
            exc.printStackTrace();
        }
    }

}
