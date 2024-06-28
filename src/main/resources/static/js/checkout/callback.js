export class Callback {
    constructor(message, isFailed) {
        this._message = message;
        this._isFailed = isFailed;
    }

    message() {
        return this._message;
    }
    isFailed() {
        return this._isFailed;
    }
}
