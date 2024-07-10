const idInputElement = document.getElementById('idInput');
const addButtonElement = document.getElementById('addButton');
const equipmentListElement = document.getElementById('equipment-list');
const formEquipmentListElement = document.getElementById('form-equipment-list');
const emptyMessageElement = document.getElementById('emptyMessage');
const errorMessageElement = document.getElementById('errorMessage');

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

class CheckoutEquipmentList {
    constructor(equipments) {
        this._equipments = equipments;
        this._values = new Array(0);
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

class ReturnDate {
    constructor(lendingPeriod) {
        const date = this.calc(lendingPeriod);
        this._value = date.toLocaleDateString(
            "ja-JP",
            {year: "numeric", month: "2-digit", day: "2-digit"}
        );
    }

    get() {
        return this._value;
    }

    calc(lendingPeriod) {
        const today = new Date();
        const returnDate = new Date();

        returnDate.setDate(today.getDate() + lendingPeriod)

        return returnDate;
    }
}

function onAddButtonClick(event) {
    event.preventDefault();

    const callback = checkoutEquipmentList.add(idInputElement.value);

    // メッセージを更新
    errorMessageElement.innerText = callback.message();
    emptyMessageElement.innerText = "";
    idInputElement.value = "";

    if (callback.isFailed()) return;

    // フォームの value を更新
    formEquipmentListElement.value = checkoutEquipmentList.toJson();

    // 表示
    const equipment = callback.value();
    const returnDate = new ReturnDate(equipment.lendingPeriod);

    const output = `
        <div id="${equipment.id}" class="parent mb-5 p-3 bg-white">
            <div class="flex justify-between">
                <p class="text-sm text-gray">${equipment.id}</p>
                <button type="submit"
                        class="delete-button flex justify-center items-center w-4 h-4">
                    <div>
                        <div class="delete-button relative w-4 h-1 rounded-full bg-error rotate-45" style="top: 0.125rem;"></div>
                        <div class="delete-button relative w-4 h-1 rounded-full bg-error" style="top: -0.125rem; rotate: -45deg"></div>
                    </div>
                </button>
            </div>
            <p class="text-xl">${equipment.name}</p>
            <p class="pt-2 text-sm text-gray">返却期日</p>
            <p>
                <span class="text-xl">${returnDate.get()}</span>
                <span class="text-sm text-gray">まで（${equipment.lendingPeriod}日）</span>
            </p>
        </div>
    `;
    equipmentListElement.innerHTML += output;
}

function onDeleteButtonClick(event) {
    event.preventDefault();

    if (!event.target.classList.contains('delete-button')) return;

    const parent = event.target.closest('.parent');
    const callback = checkoutEquipmentList.delete(parent.id);

    // メッセージを更新
    errorMessageElement.innerText = callback.message();

    if (callback.isFailed()) return;

    // フォームの value を更新
    formEquipmentListElement.value = checkoutEquipmentList.toJson()

    parent.remove();

    // 備品が一つも選択されていない時は、その旨を表示
    emptyMessageElement.innerText = checkoutEquipmentList.isEmpty() ? LIST_EMPTY_MESSAGE : "";
}

// NOTE: equipmentList は Java Spring の Model から送られてくる DB の備品リストの値
const checkoutEquipmentList = new CheckoutEquipmentList(equipmentList);
emptyMessageElement.innerText = LIST_EMPTY_MESSAGE;
errorMessageElement.innerText = "";

addButtonElement.addEventListener('click', onAddButtonClick);
equipmentListElement.addEventListener('click', onDeleteButtonClick);
