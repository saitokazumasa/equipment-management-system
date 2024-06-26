// 備品のIDを格納する配列
let _equipmentIds = [];

// Elementを取得
const equipmentIdElement = document.getElementById('equipmentId');
const equipmentInfoElement = document.getElementById('equipment-info');
const equipmentIdsElement = document.getElementById('equipmentIds');
const messageElement = document.getElementById('message');

// ＋ボタンをクリックしたときの処理
document.getElementById('addList').addEventListener('click', () => {
    const equipmentIdValue = equipmentIdElement.value;

    // _equipmentIdsにequipmentIdの値が含まれていない場合の処理
    if (!_equipmentIds.includes(equipmentIdValue)) {
        // 対象の備品情報を取得
        getEquipmentInfo(equipmentIdValue);
    } else {
        messageElement.innerText = "すでに追加されています。";
    }
    equipmentIdElement.value = "";
});

// ✕ボタンをクリックしたときの処理
document.getElementById('equipment-info').addEventListener('click', (e) => {
    if (e.target.tagName === 'BUTTON') {
        // クリックされた要素の親要素のidを取得
        const equipmentId = e.target.parentElement.id;

        // _equipmentIdsから対象のequipmentIdを削除
        _equipmentIds = _equipmentIds.filter(id => id !== equipmentId);

        // 削除後の_equipmentIdsをhiddenに設定
        equipmentIdsElement.value = _equipmentIds;

        // 要素を削除
        e.target.parentElement.remove();

        messageElement.innerText = "";
    }

    if(_equipmentIds.length === 0) {
        messageElement.innerText = "備品を選択してください。";
    }
});

//　入力されたIDの備品情報を取得する非同期処理
async function getEquipmentInfo(equipmentIdValue) {
    try {
        const url = "http://localhost:8080/checkout/application/addList?equipmentId=" + equipmentIdValue;
        //例外が発生した場合は、catchへ移動
        const response = await fetch(url, {
            method: "GET",
        });
        if (!response.ok) {
            messageElement.innerText = "備品情報を取得できませんでした。";
        } else {
            const reserved = await response.json();

            // _equipmentIdsに追加
            _equipmentIds.push(equipmentIdValue);
            // _equipmentIdsをhiddenに設定
            equipmentIdsElement.value = _equipmentIds;

            // TODO:表示形式を整える
            equipmentInfoElement.innerHTML +=
                `<div id="${reserved.id}">
                        ID:${reserved.id}<br/>
                        備品名:${reserved.name}<br/>
                        貸出期間:${reserved.lendingPeriod}日<br/>
                        通知日:${reserved.notificationDate}日前
                        <button>✕</button>
                    </div>`;
        }
        messageElement.innerText = "";
    } catch (error) {
        messageElement.innerText = "備品情報を取得できませんでした。";
    }
}