package model;

public class Record {

    private String nickname;
    private int value;


    public Record(String nickname, int value) {
        this.nickname = nickname;
        this.value = value;
    }


    public String getNickname() {
        return nickname;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

