const EMPTY_FORM_ERROR_MESSAGE = "未入力の項目があります";

class AddForm {
    #mailElement;
    #nameElement;
    #categoryElement;
    #submitButtonElement;

    constructor(mailElement, nameElement, categoryElement, submitButtonElement) {
        this.#mailElement = mailElement;
        this.#nameElement = nameElement;
        this.#categoryElement = categoryElement;
        this.#submitButtonElement = submitButtonElement;
    }

    clear() {
        this.#mailElement.value = "";
        this.#nameElement.value = "";
    }

    readValues() {
        if (!this.#mailElement.value) return Callback.failed(EMPTY_FORM_ERROR_MESSAGE);
        if (!this.#nameElement.value) return Callback.failed(EMPTY_FORM_ERROR_MESSAGE);
        if (!this.#categoryElement.value) return Callback.failed(EMPTY_FORM_ERROR_MESSAGE);

        const account = new Account(this.#mailElement.value, this.#nameElement.value, this.#categoryElement.value);
        return Callback.success(account);
    }

    subscribeToSubmit(onSubmit) {
        this.#submitButtonElement.addEventListener('click', onSubmit);
    }
}

const addForm = new AddForm(
    document.getElementById('mail'),
    document.getElementById('name'),
    document.getElementById('category'),
    document.getElementById('addButton')
);
