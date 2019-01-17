package abanob.prog.awlade.com.cunncry;

/**
 * Created by abano on 11/12/2018.
 */

public class Currancy  {
    private String from;
    private String to;
    private String date;
    private String v;
    public Currancy(){}

    public Currancy(String from, String to, String date, String v) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.v = v;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
