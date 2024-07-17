const ALREADY_VALUE_ERROR_MESSAGE = "既に追加されています";
const NOT_EXIST_ID_ERROR_MESSAGE = "存在しないIDが指定されました";
const LIST_EMPTY_MESSAGE = "備品IDを入力してください";

const UNCHECKED_ERROR_MESSAGE = "チェックされていません";
const NOT_EXIST_REASON_MESSAGE = "破損/汚損の理由を入力してください";

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

class DamageList {
    constructor() {
        this._values = [];
    }

    get() {
        return this._values;
    }

    add(id, reason) {
        const isExist = this._values.includes(id);

        if (!isExist) {
            this._values.push({id: id, reason: reason});
        }
    }

    delete(id) {
        const index = this._values.findIndex(a => a.id === parseInt(id));
        this._values.splice(index, 1);
    }

    isNotExist(id) {
        const index = this._values.findIndex(a => a.id === parseInt(id));
        return index === -1;
    }

    toJson() {
        return JSON.stringify(this._values);
    }
}

// equipmentList は Java Spring の Model から送られてくる DB の備品リストの値
const returnEquipmentList = new ReturnEquipmentList(equipmentList);

const idInputElement = document.getElementById('idInput');
const addButtonElement = document.getElementById('addButton');

const equipmentListElement = document.getElementById('equipment-list');
const formEquipmentListElement = document.getElementById('form-equipment-list');

const damageList = new DamageList();
const formDamageListElement = document.getElementById("form-damage-list");

const emptyMessageElement = document.getElementById('empty-message');
const errorMessageElement = document.getElementById('error-message');

// 編集フォームの要素
const popup = document.getElementById('popup');
const overlay = document.getElementById('overlay');
const selectedEquipmentId = document.getElementById('selected-equipment-id');
const formEquipmentName = document.getElementById('equipment-name-input');
const editCompleteButton = document.getElementById('edit-complete');
const isDamageElement = document.getElementById('is-damage');
const damageReasonInputElement = document.getElementById('damage-reason-input');
const hiddenInputElement = document.getElementById('hidden-input');
const checkErrorElement = document.getElementById('checkbox-error-message');
const inputErrorElement = document.getElementById('input-error-message');


emptyMessageElement.innerText = LIST_EMPTY_MESSAGE;
errorMessageElement.innerText = "";

checkErrorElement.innerText = "";
inputErrorElement.innerText = "";

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

    const equipment = callback.value();

    // TODO: レイアウトを整える
    const output = `
        <div id="${equipment.id}">
            <div>ID:${equipment.id}</div>
            <div>備品名:${equipment.name}</div>
            <div class="damage-state"></div>
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

    // 編集フォームに値をセットする
    selectedEquipmentId.innerText = "ID:" + event.target.parentElement.id
    hiddenInputElement.value = id;
    formEquipmentName.innerText = returnEquipmentList.getById(id).name;
    if(event.target.parentElement.getElementsByClassName('damage-reason')[0].innerText === "汚損/破損あり") {
        isDamageElement.checked = true;
    }
    damageReasonInputElement.value = event.target.parentElement.getElementsByClassName('damage-reason')[0].innerText;

    // 編集フォームを表示する
    openPopupForm();
}

function onEditCompleteButtonClick(event) {
    event.preventDefault();

    const id = hiddenInputElement.value;

    // 対象の備品IDを持つdiv要素の中にある子要素を取得する
    const damageCategoryElement = document.getElementById(id).getElementsByClassName('damage-state')[0];
    const damageReasonElement = document.getElementById(id).getElementsByClassName('damage-reason')[0];

    const isDamage = isDamageElement.checked;
    const damageReason = damageReasonInputElement.value;

    // damageReasonが空白や改行のみかチェック
    const damageReasonCheck = damageReason.match(/\S/g);

    if (!isDamage) {
        checkErrorElement.innerText = UNCHECKED_ERROR_MESSAGE;
    } else {
        checkErrorElement.innerText = "";
    }

    if (damageReasonCheck === null) {
        inputErrorElement.innerText = NOT_EXIST_REASON_MESSAGE;
    } else {
        inputErrorElement.innerText = "";
    }

    if (damageList.isNotExist(id) && isDamage && damageReasonCheck) {
        damageList.delete(id);

        damageList.add(id, damageReason);

        damageCategoryElement.innerText = "汚損/破損あり";
        damageReasonElement.innerText = damageReason;
        formDamageListElement.value = damageList.toJson();

        closePopupForm();
    }

    console.log(damageList.get());
}

function openPopupForm() {
    popup.style.display = 'block';
    overlay.style.display = 'block';
}

function closePopupForm() {
    popup.style.display = 'none';
    overlay.style.display = 'none';
}

addButtonElement.addEventListener('click', onAddButtonClick);
equipmentListElement.addEventListener('click', onDeleteButtonClick);
equipmentListElement.addEventListener('click', onEditButtonClick);
editCompleteButton.addEventListener('click', onEditCompleteButtonClick);
overlay.addEventListener('click', closePopupForm);