class ErrorMessage {
    #element;

    constructor(errorMessageElement) {
        this.#element = errorMessageElement;
    }

    update(message) {
        this.#element.innerText = message;
    }

    clear() {
        this.#element.innerText = "";
    }
}

const errorMessage = new ErrorMessage(
    document.getElementById('errorMessage')
);
