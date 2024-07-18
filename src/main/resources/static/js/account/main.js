const LIST_EMPTY_MESSAGE = "アカウントを追加してください";

function onAddButtonClick(event) {
    event.preventDefault();

    const readCallback = addForm.readValues();
    if (readCallback.isFailed()) {
        errorMessage.update(readCallback.message());
        return;
    }

    const addCallback = accountList.add(readCallback.value());
    if (addCallback.isFailed()) {
        errorMessage.update(addCallback.message());
        return;
    }

    errorMessage.clear();
    addForm.clear();
    jsonForm.update(accountList.toJson());

    accountListView.add(addCallback.value());
}

function onDeleteButtonClick(event) {
    event.preventDefault();

    if (!accountListView.isNotDeleteButton(event)) return;

    const parent = event.target.closest('.parent');
    const id = parent.id;
    const mail = id.substring(accountListView.idHead().length, id.length);
    const callback = accountList.remove(mail);

    if (callback.isFailed()) {
        errorMessage.update(callback.message());
        return;
    }

    errorMessage.clear();
    jsonForm.update(accountList.toJson());
    accountListView.remove(event);

    // リストが空の時はその旨を表示
    if (!accountList.isEmpty()) return;

    errorMessage.update(LIST_EMPTY_MESSAGE);
}

addForm.subscribeToSubmit(e => onAddButtonClick(e));
accountListView.subscribeToClick(e => onDeleteButtonClick(e));
errorMessage.update("");
