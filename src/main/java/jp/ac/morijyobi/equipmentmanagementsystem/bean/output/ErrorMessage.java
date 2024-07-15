package jp.ac.morijyobi.equipmentmanagementsystem.bean.output;

public class ErrorMessage implements IMessage {
    private final String _content;

    public ErrorMessage(final String content) {
        this._content = content;
    }

    @Override
    public String category() {
        return MessageCategory.ERROR.toString();
    }

    @Override
    public String content() {
        return _content;
    }
}
