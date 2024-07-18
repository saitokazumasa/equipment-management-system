class JsonForm {
    #jsonElement;

    constructor(jsonElement) {
        this.#jsonElement = jsonElement;
    }

    update(json) {
        this.#jsonElement.value = json;
    }
}

const jsonForm = new JsonForm(
    document.getElementById('json')
);
