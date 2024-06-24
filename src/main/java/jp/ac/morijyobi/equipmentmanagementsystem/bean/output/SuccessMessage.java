package jp.ac.morijyobi.equipmentmanagementsystem.bean.output;

public class SuccessMessage implements IMessage{
    private final String content;

    public SuccessMessage(final String content) {
        this.content = content;
    }

    @Override
    public String category() {
        return MessageCategory.SUCCESS.toString();
    }

    @Override
    public String content() {
        return content;
    }
}
