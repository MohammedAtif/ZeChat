package zechat.android.training.zemoso.zechat.java_beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zemoso on 25/7/17.
 */

public class Startup extends RealmObject {

    //region Variable Declaration
    @PrimaryKey
    private int id;
    private String jsonObject;
    //endregion

    //region Getters and Setters

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }

    public int getId() {
        return id;
    }

    //endregion
}
