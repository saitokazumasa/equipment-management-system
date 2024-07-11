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

    getById(id) {
        return this._values.find(a => a.id === parseInt(id));
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

const damageList = [];
const formDamageListElement = document.getElementById("form-damage-list");

const emptyMessageElement = document.getElementById('emptyMessage');
const errorMessageElement = document.getElementById('errorMessage');


// 編集フォームの要素
const formEquipmentName = document.getElementById('equipment-name-input');
const editCompleteButton = document.getElementById('edit-complete');
const popup = document.getElementById('popup');
const overlay = document.getElementById('overlay');




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

    // フォームの value を更新
    formEquipmentListElement.value = returnEquipmentList.toJson();

    // 表示
    const equipment = callback.value();

    // TODO: レイアウトを整える
    const output = `
        <div id="${equipment.id}">
            <div>ID:${equipment.id}</div>
            <div class="equipment-name">備品名:${equipment.name}</div>
            <div class="damage-category"></div>
            <div class="damage-reason"></div>
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
    const id = parseInt(event.target.parentElement.id);

    // 編集フォームを表示する
    popup.style.display = 'block';
    overlay.style.display = 'block';

    // TODO:入力フォームの備品名をセット
    const toEditEquipment = returnEquipmentList.getById(id);
    formEquipmentName.innerText = toEditEquipment.name;



    const damageCategoryElement = event.target.parentElement.getElementsByClassName('damage-category')[0];
    const damageReasonElement = event.target.parentElement.getElementsByClassName('damage-reason')[0];

    const index = damageList.findIndex(a => a.id === id);

    if (index === -1) {
        damageList.push({
            id: id,
            category: 1,
            reason: "コーヒーをこぼした。"
        });

        damageCategoryElement.innerText = "汚損";
        damageReasonElement.innerText = "コーヒーをこぼした。";
    } else {
        damageList.splice(index, 1);

        damageCategoryElement.innerText = "";
        damageReasonElement.innerText = "";
    }

    formDamageListElement.value = JSON.stringify(damageList);
}

addButtonElement.addEventListener('click', onAddButtonClick);
equipmentListElement.addEventListener('click', onDeleteButtonClick);
equipmentListElement.addEventListener('click', onEditButtonClick);


function testFunction() {
    popup.style.display = 'none';
    overlay.style.display = 'none';
}

editCompleteButton.addEventListener('click', testFunction);
overlay.addEventListener('click', testFunction);




