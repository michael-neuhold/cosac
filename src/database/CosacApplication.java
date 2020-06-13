package database;

import cosac.model.Role;
import cosac.model.UserData;
import database.dao.user.UserDataDao;
import database.dao.user.UserDataDaoJdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;
import static java.lang.System.err;

public class CosacApplication {

    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final Map<String, Function> commands = createCommands();

    private static final String SERVER = "localhost";
    private static final String USERNAME = "root";
    private static final String PASSWORD = null;
    private static final String CONNECTION_STRING = "jdbc:mysql://" + SERVER + "/CosacDB?autoReconnect=true&useSSL=false";

    private interface Function {
        void call(UserDataDao dao) throws Exception;
    }

    private static String promptFor(String prompt) {
        for(;;) {
            out.print(prompt + ">");
            try { return in.readLine(); }
            catch (Exception exc) {}
        }
    }

    private static Map<String, Function> createCommands() {
        Map<String, Function> tmpCommands = new HashMap<>();
        tmpCommands.put("insert", CosacApplication::insert);
        tmpCommands.put("list", CosacApplication::list);
        tmpCommands.put("update", CosacApplication::update);
        tmpCommands.put("delete", CosacApplication::delete);
        //tmpCommands.put("quite", (dao) -> { /* never called */ });
        return tmpCommands;
    }

    private static void list(UserDataDao dao) throws DataAccessException {
        for(UserData user : dao.getAll())
            out.println(user);
    }

    private static void insert(UserDataDao dao) throws DataAccessException {
        UserData user = new UserData(
                promptFor("  user id "),
                promptFor("  first name "),
                promptFor("  last name "),
                promptFor("  email "),
                promptFor("  password "),
                Role.STUDENT,
                false
        );
        dao.store(user);
        out.printf("inserted new person %s%n", user);
    }

    private static void update(UserDataDao dao) throws DataAccessException {
        UserData user = new UserData(
                promptFor("  user id "),
                promptFor("  first name "),
                promptFor("  last name "),
                promptFor("  email "),
                promptFor("  password "),
                Role.STUDENT,
                false
        );
        dao.update(user);
        out.printf("updated user %s%n", user);
    }

    private static void delete(UserDataDao dao) throws DataAccessException {
        dao.delete(promptFor("  user id "));
        out.printf("deleted user by id");
    }

    public static void main(String args[]) {
        try(UserDataDao userDataDao = new UserDataDaoJdbc(CONNECTION_STRING, USERNAME, PASSWORD)) {

            out.println("user by id: " + userDataDao.getById("S1810307094"));

            out.printf("%ncurrently %d entries in phone book %n", userDataDao.getCount());
            out.printf("%ncommands: %s%n%n", commands.keySet());
            for(String cmd = promptFor(""); !cmd.equals("quit"); cmd = promptFor("")) {
                Function func = commands.get(cmd.trim());
                if(func == null) out.printf("ERROR: invalid command; commands: %s%n", commands.keySet());
                else try { func.call(userDataDao); }
                catch (Exception exc) {err.printf("ERROR: %s%n", exc.getMessage()); }
            }
        } catch(Exception exc) { exc.printStackTrace(); }
    }

}
