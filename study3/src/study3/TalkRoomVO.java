package study3;

public class TalkRoomVO {
    private Integer roomNo = null;
    private String apple = null;
    private String banana = null;
    private String orange = null;

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }

    public String getBanana() {
        return banana;
    }

    public void setBanana(String banana) {
        this.banana = banana;
    }

    public String getOrange() {
        return orange;
    }

    @Override
    public String toString() {
        return "TalkRoomVO{" +
                "roomNo=" + roomNo +
                ", apple='" + apple + '\'' +
                ", banana='" + banana + '\'' +
                ", orange='" + orange + '\'' +
                '}';
    }

    public void setOrange(String orange) {
        this.orange = orange;
    }
}
