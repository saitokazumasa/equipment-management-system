class Account {
    #mail;
    #name;
    #category;

    constructor(mail, name, category) {
        if (mail === null) throw new Error('mail is null');
        if (name === null) throw new Error('name is null');
        if (category === null) throw new Error('category is null');

        this.#mail = mail;
        this.#name = name;
        this.#category = category;
    }

    static empty() {
        return new Account('none', 'none', 'none');
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

    toJSON() {
        return {
            id: -1,
            mail: this.#mail,
            name: this.#name,
            // NOTE: 仮パスワード
            password: 'morijyobi',
            category: this.#category,
            isEnable: true
        };
    }
}
