const NULL_ERROR_MESSAGE = 'nullが指定されました';
const EXIST_ERROR_MESSAGE = '既に追加されています';
const NOT_FOUND_ERROR_MESSAGE = '存在しないアカウントが指定されました';

class AccountList {
    #values;

    constructor() {
        this.#values = new Array(0);
    }

    add(account) {
        if (account === null) return Callback.failed(NULL_ERROR_MESSAGE);
        if (this.#isExist(account)) return Callback.failed(EXIST_ERROR_MESSAGE);

        this.#values.push(account);

        return Callback.success(account);
    }

    remove(mail) {
        const index = this.#findIndex(mail);
        if (index === -1) return Callback.failed(NOT_FOUND_ERROR_MESSAGE);

        const account = this.#values[index];
        this.#values.splice(index, 1);

        return Callback.success(account);
    }

    #isExist(account) {
        return this.#values.findIndex(a => a.mail() === account?.mail()) !== -1;
    }

    #findIndex(mail) {
        return this.#values.findIndex(a => a.mail() === mail);
    }

    isEmpty() {
        return this.#values.length === 0;
    }

    toJson() {
        return JSON.stringify(this.#values);
    }
}

const accountList = new AccountList();
