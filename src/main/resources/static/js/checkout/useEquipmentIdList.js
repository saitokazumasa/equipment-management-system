import {Callback} from "./callback";

export class UseEquipmentIdList {
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

        if (index === -1) return new Callback("存在しないIDが指定されました", true);

        this._array.splice(index, 1);
        return new Callback("", false);
    }

    isEmpty() {
        return this._array.length <= 0;
    }
}
