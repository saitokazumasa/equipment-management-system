package jp.ac.morijyobi.equipmentmanagementsystem.bean.output;

public record ErrorMessage(String content) implements IMessage {
    @Override
    public String category() {
        return MessageCategory.ERROR.toString();
    }
}
