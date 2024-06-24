package jp.ac.morijyobi.equipmentmanagementsystem.bean.output;

public interface IMessage {
    public enum MessageCategory {
        SUCCESS,
        ERROR,
    }

    /**
     * MessageCategory を文字列として返す
     * @return MessageCategory.toString()
     */
    public String category();
    public String content();
}
