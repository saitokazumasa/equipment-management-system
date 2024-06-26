// TODO: 変数名を要検討
const _useEquipmentIds    = new UseEquipmentIdList();
const addButtonElement    = document.getElementById('addButton');
const deleteButtonElement = document.getElementById('deleteButton');

const emptyMessageElement = document.getElementById('emptyMessage');
const errorMessageElement = document.getElementById('errorMessage');

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

class UseEquipmentIdList {
    constructor() {
        this._array = new Array(0);
    }

    get() {
        return this._array;
    }

    add(id) {
        if (this._array.includes(id)) return new Callback("既に追加されています", true);

        this._array.push(id);
        return new Callback("", false);
    }

    delete(id) {
        const index = this._array.indexOf(id);
        this._array.splice(index, 1);
    }

    isEmpty() {
        return this._array.length <= 0;
    }
}

function onAddButtonClick(event) {
    event.preventDefault();

    const inputId = document
        .getElementById('inputId')
        .value;

    const callback = _useEquipmentIds.add(inputId);
    errorMessageElement.innerText = callback.message();

    if (callback.isFailed()) return;

    // TODO: ページをリロード
}

function onDeleteButtonClick(event) {
    event.preventDefault();

    // 親要素に備品IDの情報が保持されている
    const id = event.target.parentElement.id;

    _useEquipmentIds.delete(id);
    emptyMessageElement.hidden = _useEquipmentIds.isEmpty();

    // TODO: ページをリロード
}

addButtonElement.addEventListener('click', onAddButtonClick);
deleteButtonElement.addEventListener('click', onDeleteButtonClick);

// TODO: 以下、ページ読み込み時に動かす
// TODO: URL などから _useEquipmentIds の値を取得
// TODO: _useEquipmentIds の値をフォームに反映

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