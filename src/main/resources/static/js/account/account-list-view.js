class AccountListView {
    #element;
    #idHead = "MAIL_";

    constructor(element) {
        this.#element = element;
    }

    add(account) {
        // NOTE: mail のみだと、他の id タグと被る可能性があるため、ID_HEAD を接頭語として付ける
        const id = this.#idHead + account.mail();

        // TODO: デザインを整える
        this.#element.appendChild(
            `
                <div id="${id}" class="parent mb-5 p-3 bg-white">
                    <button type="submit" class="delete-button">x</button>
                    <p>名前:  ${account.name()}</p>
                    <p>メールアドレス: ${account.mail()}</p>
                    <p>ロール: ${account.category()}</p> 
                </div>
            `
        );
    }

    idHead() {
        return this.#idHead;
    }

    isNotDeleteButton(event) {
        return !event.target.classList.contains('delete-button');
    }

    remove(event) {
        const parent = event.target.closest('.parent');
        parent.remove();
    }

    subscribeToClick(onClick) {
        this.#element.addEventListener('click', onClick);
    }
}

const accountListView = new AccountListView(document.getElementById('account-list'));