package jp.ac.morijyobi.equipmentmanagementsystem.bean.output;

public interface IMessage {
    public enum MessageCategory {
        SUCCESS,
        ERROR,
    }

    /**
     * メッセージのカテゴリを文字列として返す
     * @return "SUCCESS" / "ERROR"
     */
    public String category();
    public String content();
}
