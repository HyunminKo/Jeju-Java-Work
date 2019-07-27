package temp;

public class BangMyungVO {
    private Integer no = null;
    private String gul = null;
    private String theTime = null;

    public BangMyungVO(Integer no, String gul, String theTime) {
        this.no = no;
        this.gul = gul;
        this.theTime = theTime;
    }

    public Integer getNo() {
        return this.no;
    }

    public String getGul() {
        return this.gul;
    }

    public String getTheTime() {
        return this.theTime;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public void setGul(String gul) {
        this.gul = gul;
    }

    public void setTheTiem(String theTime) {
        this.theTime = theTime;
    }
}