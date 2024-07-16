package jp.ac.morijyobi.equipmentmanagementsystem.bean.output;

public record SuccessMessage(String content) implements IMessage {
    @Override
    public String category() {
        return MessageCategory.SUCCESS.toString();
    }
}
