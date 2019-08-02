package study3;

public class TalkVO {
    private Integer talkNo = null;
    private Integer roomNo = null;
    private String content = null;

    @Override
    public String toString() {
        return "TalkVO{" +
                "talkNo=" + talkNo +
                ", roomNo=" + roomNo +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getTalkNo() {
        return talkNo;
    }

    public void setTalkNo(Integer talkNo) {
        this.talkNo = talkNo;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
