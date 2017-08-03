package zechat.android.training.zemoso.zechat.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ContactInfo extends RealmObject {
    //region Variables
    @PrimaryKey
    private String id;
    private String name;
    private String status;
    //endregion

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    //endregion

}
