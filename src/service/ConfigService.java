package service;

import dao.ConfigDAO;
import entity.Config;

public class ConfigService {

    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "500";

    static ConfigDAO configDAO = new ConfigDAO();
    static {
        init();
    }

    public static void init() {
        init(budget, default_budget);
        init(mysqlPath, "");
    }

    private static void init(String key, String value) {
        Config config = configDAO.getByKey(key);
        if (config == null) {
            Config c = new Config(key, value);
            //add to database
            configDAO.add(c);
        }
    }

    public String get(String key) {
        Config config = configDAO.getByKey(key);
        return (config == null) ? "" : config.getValue();
    }

    public void update(String key, String value) {
        Config config = configDAO.getByKey(key);
        if (config != null) {
            config.setValue(value);
            configDAO.update(config);
        }
    }

    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
}
