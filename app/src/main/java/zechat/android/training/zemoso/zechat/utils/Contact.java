package zechat.android.training.zemoso.zechat.utils;


public class Contact {
    private String contactName, status;

    public Contact() {
        //Empty Public Constructor
    }

    public Contact(String contactName, String status) {
        this.contactName = contactName;
        this.status = status;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String name) {
        this.contactName = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}