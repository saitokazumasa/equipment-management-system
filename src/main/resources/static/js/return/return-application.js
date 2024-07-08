const ALREADY_VALUE_ERROR_MESSAGE = "既に追加されています";
const NOT_EXIST_ID_ERROR_MESSAGE = "存在しないIDが指定されました";
const LIST_EMPTY_MESSAGE = "備品IDを入力してください";

class Callback {
    constructor(message, value, isFailed) {
        this._message = message;
        this._value = value;
        this._isFailed = isFailed;
    }

    message() {
        return this._message;
    }

    value() {
        return this._value;
    }

    isFailed() {
        return this._isFailed;
    }
}

class ReturnEquipmentList {
    constructor(equipments) {
        this._equipments = equipments;
        this._values = new Array(0);
    }

    get() {
        return this._values;
    }

    toJson() {
        return JSON.stringify(this._values);
    }

    add(id) {
        const equipment = this._equipments.find(a => a.id === parseInt(id));
        const isExist = this._values.includes(equipment);

        if (equipment === undefined) return new Callback(NOT_EXIST_ID_ERROR_MESSAGE, undefined, true);
        if (isExist) return new Callback(ALREADY_VALUE_ERROR_MESSAGE, undefined, true);

        this._values.push(equipment);
        return new Callback("", equipment, false);
    }

    delete(id) {
        const index = this._values.findIndex(a => a.id === parseInt(id));

        if (index === -1) return new Callback(NOT_EXIST_ID_ERROR_MESSAGE, undefined, true);

        const equipment = this._equipments[index];
        this._values.splice(index, 1);
        return new Callback("", equipment, false);
    }

    isEmpty() {
        return this._values.length === 0;
    }
}

// equipmentList は Java Spring の Model から送られてくる DB の備品リストの値
const returnEquipmentList = new ReturnEquipmentList(equipmentList);

const idInputElement = document.getElementById('idInput');
const addButtonElement = document.getElementById('addButton');

const equipmentListElement = document.getElementById('equipment-list');
const formEquipmentListElement = document.getElementById('form-equipment-list');

const emptyMessageElement = document.getElementById('emptyMessage');
const errorMessageElement = document.getElementById('errorMessage');

emptyMessageElement.innerText = LIST_EMPTY_MESSAGE;
errorMessageElement.innerText = "";

function onAddButtonClick(event) {
    event.preventDefault();

    const callback = returnEquipmentList.add(idInputElement.value);

    // メッセージを更新
    errorMessageElement.innerText = callback.message();
    emptyMessageElement.innerText = "";
    idInputElement.value = "";

    if (callback.isFailed()) return;

    equipmentList[0]["damage"] = "";
    equipmentList[0]["damageReason"] = "";
    console.log(equipmentList);


    // フォームの value を更新
    formEquipmentListElement.value = returnEquipmentList.toJson();

    // 表示
    const equipment = callback.value();

    // TODO: レイアウトを整える
    const output = `
        <div id="${equipment.id}">
            ID:${equipment.id}
            備品名:${equipment.name}
            <div class="damage">${equipment.damage}</div>
            <div class="damage-reason">${equipment.damageReason}</div>
            <button class="edit-button">編集</button>
            <button class="delete-button">✕</button>
        </div>
    `;
    equipmentListElement.innerHTML += output;
}

function onDeleteButtonClick(event) {
    event.preventDefault();

    if (event.target.className !== 'delete-button') return;

    // 親要素に備品IDがある
    const id = event.target.parentElement.id;
    const callback = returnEquipmentList.delete(id);

    // メッセージを更新
    errorMessageElement.innerText = callback.message();

    if (callback.isFailed()) return;

    // フォームの value を更新
    formEquipmentListElement.value = returnEquipmentList.toJson()

    event.target.parentElement.remove();

    // 備品が一つも選択されていない時は、その旨を表示
    emptyMessageElement.innerText = returnEquipmentList.isEmpty() ? LIST_EMPTY_MESSAGE : "";
}

function onEditButtonClick(event) {
    event.preventDefault();

    if (event.target.className !== 'edit-button') return;

    // 親要素に備品IDがある
    const id = event.target.parentElement.id;

    const damageElement = event.target.parentElement.getElementsByClassName('damage')[0];
    const damageReasonElement = event.target.parentElement.getElementsByClassName('damage-reason')[0];

    if (equipmentList[0]["damage"] === "" && equipmentList[0]["damageReason"] === "") {
        equipmentList[0]["damage"] = "破損/汚損あり";
        equipmentList[0]["damageReason"] = "コーヒーをこぼした。";

        damageElement.innerText = equipmentList[0]["damage"];
        damageReasonElement.innerText = equipmentList[0]["damageReason"];
    } else {
        equipmentList[0]["damage"] = "";
        equipmentList[0]["damageReason"] = "";

        damageElement.innerText = equipmentList[0]["damage"];
        damageReasonElement.innerText = equipmentList[0]["damageReason"];
    }



    // const callback = returnEquipmentList.delete(id);
    //
    // // メッセージを更新
    // errorMessageElement.innerText = callback.message();
    //
    // if (callback.isFailed()) return;
    //
    // // フォームの value を更新
    // formEquipmentListElement.value = returnEquipmentList.toJson()
    //
    // event.target.parentElement.remove();
    //
    // // 備品が一つも選択されていない時は、その旨を表示
    // emptyMessageElement.innerText = returnEquipmentList.isEmpty() ? LIST_EMPTY_MESSAGE : "";
}

addButtonElement.addEventListener('click', onAddButtonClick);
equipmentListElement.addEventListener('click', onDeleteButtonClick);
equipmentListElement.addEventListener('click', onEditButtonClick);
