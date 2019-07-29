package study2;

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
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getGul() {
        return gul;
    }

    public void setGul(String gul) {
        this.gul = gul;
    }

    public String getTheTime() {
        return theTime;
    }

    public void setTheTime(String theTime) {
        this.theTime = theTime;
    }

    @Override
    public String toString() {
        return "BangMyungVO{" +
                "no=" + no +
                ", gul='" + gul + '\'' +
                ", theTime='" + theTime + '\'' +
                '}';
    }
}
