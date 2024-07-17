const mailInputElement = document.getElementById('mailInput');
const nameInputElement = document.getElementById('nameInput');
const categorySelectElement = document.getElementById('categorySelect');
const addButtonElement = document.getElementById('addButton');
const accountListElement = document.getElementById('account-list');
const formAccountListElement = document.getElementById('form-account-list');
const emptyMessageElement = document.getElementById('emptyMessage');
const errorMessageElement = document.getElementById('errorMessage');

const ID_HEAD = "MAIL_";
const ALREADY_VALUE_ERROR_MESSAGE = "既に追加されています";
const FORM_EMPTY_MESSAGE = "未入力の項目があります";
const NOT_EXIST_MAIL_ERROR_MESSAGE = "存在しないメールアドレスが指定されました";
const LIST_EMPTY_MESSAGE = "アカウントを追加してください";

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

class TemporaryAccount {
    #mail;
    #name;
    #category;

    constructor(mail, name, category) {
        this.#mail = mail;
        this.#name = name;
        this.#category = category;
    }

    mail() {
        return this.#mail;
    }

    name() {
        return this.#name;
    }

    category() {
        return this.#category;
    }

    // NOTE: IDE 上では no usages になるが、JSON 変換時に自動で使用される
    toJSON() {
        return {
            mail: this.#mail,
            name: this.#name,
            category: this.#category
        };
    }
}

class RegisterAccountList {
    #values;

    constructor() {
        this.#values = new Array(0);
    }

    toJson() {
        return JSON.stringify(this.#values);
    }

    add(mail, name, category) {
        if (!mail || !name || !category) return Callback.failed(FORM_EMPTY_MESSAGE);

        const temporaryAccount = new TemporaryAccount(mail, name, category);
        const isExist = this.#values.findIndex(a => a.mail() === temporaryAccount.mail()) !== -1;

        if (isExist) return Callback.failed(ALREADY_VALUE_ERROR_MESSAGE);

        this.#values.push(temporaryAccount);
        return Callback.success(temporaryAccount);
    }

    delete(mail) {
        const index = this.#values.findIndex(a => a.mail() === mail);

        if (index === -1) return Callback.failed(NOT_EXIST_MAIL_ERROR_MESSAGE);

        const account = this.#values[index];
        this.#values.splice(index, 1);
        return Callback.success(account);
    }

    isEmpty() {
        return this.#values.length === 0;
    }
}

function onAddButtonClick(event) {
    event.preventDefault();

    const callback = registerAccountList.add(
        mailInputElement.value,
        nameInputElement.value,
        categorySelectElement.value
    );

    // メッセージを更新
    errorMessageElement.innerText = callback.message();
    emptyMessageElement.innerText = "";
    mailInputElement.value = "";
    nameInputElement.value = "";

    if (callback.isFailed()) return;

    // フォームの value を更新
    formAccountListElement.value = registerAccountList.toJson();

    // 表示
    const account = callback.value();
    // NOTE: mail のみだと、他の id タグと被る可能性があるため、ID_HEAD を接頭語として付ける
    const id = ID_HEAD + account.mail();

    // TODO: デザインを整える
    const output = `
        <div id="${id}" class="parent mb-5 p-3 bg-white">
            <button type="submit" class="delete-button">x</button>
            <p>名前:  ${account.name()}</p>
            <p>メールアドレス: ${account.mail()}</p>
            <p>ロール: ${account.category()}</p> 
        </div>
    `;
    accountListElement.innerHTML += output;
}

function onDeleteButtonClick(event) {
    event.preventDefault();

    if (!event.target.classList.contains('delete-button')) return;

    const parent = event.target.closest('.parent');
    const id = parent.id;
    const mail = id.substring(ID_HEAD.length, id.length);
    const callback = registerAccountList.delete(mail);

    // メッセージを更新
    errorMessageElement.innerText = callback.message();

    if (callback.isFailed()) return;

    // フォームの value を更新
    formAccountListElement.value = registerAccountList.toJson()

    parent.remove();

    // 備品が一つも選択されていない時は、その旨を表示
    emptyMessageElement.innerText = registerAccountList.isEmpty() ? LIST_EMPTY_MESSAGE : "";
}

const registerAccountList = new RegisterAccountList();
emptyMessageElement.innerText = LIST_EMPTY_MESSAGE;
errorMessageElement.innerText = "";

addButtonElement.addEventListener('click', onAddButtonClick);
accountListElement.addEventListener('click', onDeleteButtonClick);
