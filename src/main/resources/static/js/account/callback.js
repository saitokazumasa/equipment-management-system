class Callback {
    #message;
    #value
    #isFailed;

    constructor(message, value, isFailed) {
        this.#message = message;
        this.#value = value;
        this.#isFailed = isFailed;
    }

    static success(value) {
        return new Callback("", value, false);
    }

    static failed(message) {
        return new Callback(message, undefined, true);
    }

    message() {
        return this.#message;
    }

    value() {
        return this.#value;
    }

    isFailed() {
        return this.#isFailed;
    }
}
