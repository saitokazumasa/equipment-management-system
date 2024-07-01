const ALREADY_VALUE_ERROR_MESSAGE = "既に追加されています";
const NOT_EXIST_ID_ERROR_MESSAGE = "存在しないIDが指定されました";
const LIST_EMPTY_MESSAGE = "備品IDを入力してください";

class Callback {
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

class UseEquipmentList {
    constructor() {
        this._array = new Array(0);
    }

    get() {
        return this._array;
    }

    ids() {
        return this._array.map(a => a.id);
    }

    add(id) {
        const equipment = equipmentList.find(a => a.id === parseInt(id));

        if (equipment === undefined) return new Callback(NOT_EXIST_ID_ERROR_MESSAGE, true);

        if (checkIdExists(this._array, parseInt(id))) return new Callback(ALREADY_VALUE_ERROR_MESSAGE, true);

        this._array.push(equipment);
        return new Callback("", false);
    }

    delete(id) {
        const index = this._array.findIndex(a => a.id === parseInt(id));

        if (index === -1) return new Callback(NOT_EXIST_ID_ERROR_MESSAGE, true);

        this._array.splice(index, 1);
        return new Callback("", false);
    }

    isEmpty() {
        return this._array.length <= 0;
    }
}

const useEquipmentList = new UseEquipmentList();

const idInputElement      = document.getElementById('idInput');
const addButtonElement    = document.getElementById('addButton');
const displayInfoElement = document.getElementById('display-info');
const idListElement       = document.getElementById("idList");

const emptyMessageElement = document.getElementById('emptyMessage');
const errorMessageElement = document.getElementById('errorMessage');


function checkIdExists(array, idToCheck) {
    for (let i = 0; i < array.length; i++) {
        if (array[i].id === idToCheck) {
            return true;
        }
    }
    return false;
}

function onAddButtonClick(event) {
    event.preventDefault();

    const callback = useEquipmentList.add(idInputElement.value);
    errorMessageElement.innerText = callback.message();
    emptyMessageElement.innerText = "";

    if (callback.isFailed()) return idInputElement.value = "";

    idListElement.value = useEquipmentList.ids()

    // TODO: 要確認
    const equipment = equipmentList.find(a => a.id === parseInt(idInputElement.value));
    // YYYY/MM/DD
    const returnDate = calculateReturnDate(equipment.lendingPeriod).toLocaleDateString("ja-JP", {year: "numeric",month: "2-digit", day: "2-digit"})
    // TODO: レイアウトを整える
    const displayEquipmentData = `<div id="${equipment.id}">
        ID:${equipment.id}
        備品名:${equipment.name}
        返却期日:${returnDate}まで（${equipment.lendingPeriod}日）
        <button id="deleteButton">✕</button>
        </div>`;
    displayInfoElement.innerHTML += displayEquipmentData;

    idInputElement.value = "";
}

function calculateReturnDate(n) {
    let today = new Date();
    let futureDate = new Date(today);

    futureDate.setDate(today.getDate() + n);

    return futureDate;
}

function onDeleteButtonClick(event) {
    event.preventDefault();
    if (event.target.tagName === 'BUTTON') {
        const id = event.target.parentElement.id;

        const callback = useEquipmentList.delete(id);
        errorMessageElement.innerText = callback.message();

        if (callback.isFailed()) return;

        idListElement.value = useEquipmentList.ids()

        event.target.parentElement.remove();

        // 備品が一つも選択されていない時は、その旨を表示
        emptyMessageElement.innerText = useEquipmentList.isEmpty() ? LIST_EMPTY_MESSAGE : "";
    }
}

emptyMessageElement.innerText = new Callback(LIST_EMPTY_MESSAGE, true).message();
errorMessageElement.innerText = new Callback("", false).message()

addButtonElement.addEventListener('click', onAddButtonClick);
displayInfoElement.addEventListener('click', onDeleteButtonClick);



// ----------------------------------------------------------------------------------

// const equipmentIdElement = document.getElementById('equipmentId');
// const equipmentInfoElement = document.getElementById('equipment-info');
// const equipmentIdsElement = document.getElementById('equipmentIds');
// const messageElement = document.getElementById('message');

// // ＋ボタンをクリックしたときの処理
// document.getElementById('addList').addEventListener('click', () => {
//     const equipmentIdValue = equipmentIdElement.value;
//
//     // _equipmentIdsにequipmentIdの値が含まれていない場合の処理
//     if (!_useEquipmentIdList.includes(equipmentIdValue)) {
//         // 対象の備品情報を取得
//         getEquipmentInfo(equipmentIdValue);
//     } else {
//         messageElement.innerText = "すでに追加されています。";
//     }
//     equipmentIdElement.value = "";
// });
//
// // ✕ボタンをクリックしたときの処理
// document.getElementById('equipment-info').addEventListener('click', (e) => {
//     if (e.target.tagName === 'BUTTON') {
//         // クリックされた要素の親要素のidを取得
//         const equipmentId = e.target.parentElement.id;
//
//         // _equipmentIdsから対象のequipmentIdを削除
//         _useEquipmentIdList = _useEquipmentIdList.filter(id => id !== equipmentId);
//
//         // 削除後の_equipmentIdsでhiddenの値を更新
//         equipmentIdsElement.value = _useEquipmentIdList;
//
//         // 要素を削除
//         e.target.parentElement.remove();
//
//         messageElement.innerText = "";
//     }
//
//     if(_useEquipmentIdList.length === 0) {
//         messageElement.innerText = "備品を選択してください。";
//     }
// });
//
// //　入力されたIDの備品情報を取得する非同期処理
// async function getEquipmentInfo(equipmentIdValue) {
//     try {
//         const url = "http://localhost:8080/checkout/application/addList?equipmentId=" + equipmentIdValue;
//         //例外が発生した場合は、catchへ移動
//         const response = await fetch(url, {
//             method: "GET",
//         });
//         if (!response.ok) {
//             messageElement.innerText = "備品情報を取得できませんでした。";
//         } else {
//             const reserved = await response.json();
//
//             // _equipmentIdsに追加
//             _useEquipmentIdList.push(equipmentIdValue);
//             // _equipmentIdsをhiddenに設定
//             equipmentIdsElement.value = _useEquipmentIdList;
//
//             // TODO:表示形式を整える
//             equipmentInfoElement.innerHTML +=
//                 `<div id="${reserved.id}">
//                         ID:${reserved.id}<br/>
//                         備品名:${reserved.name}<br/>
//                         貸出期間:${reserved.lendingPeriod}日<br/>
//                         通知日:${reserved.notificationDate}日前
//                         <button id="deleteButton">✕</button>
//                     </div>`;
//         }
//         messageElement.innerText = "";
//     } catch (error) {
//         messageElement.innerText = "備品情報を取得できませんでした。";
//     }
// }