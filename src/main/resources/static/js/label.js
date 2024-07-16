const labelElements = document.getElementsByTagName('label');

class Label {
    _element;

    constructor(element) {
        this._element = element;

        this.fadeOut(0);
    }

    targetElement() {
        const attributes = this._element.attributes;

        for (let i = 0; i < attributes.length; i++) {
            const tag = attributes.item(i);

            if (tag.name !== 'for') continue;

            return document.getElementById(tag.value);
        }

        return undefined;
    }

    fadeIn(duration) {
        this._element.animate({
                opacity: 1
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }

    fadeOut(duration) {
        this._element.animate({
                opacity: 0
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }
}

function onKeyUp(inputValue, label) {
    if (inputValue) {
        label.fadeIn(100);
        return;
    }

    label.fadeOut(100);
}

function onLoad() {
    for (let i = 0; i < labelElements.length; i++) {
        const label = new Label(labelElements.item(i));
        const inputElement = label.targetElement();

        inputElement.addEventListener('keyup', _ => onKeyUp(inputElement.value, label));
    }
}

window.addEventListener('load', onLoad);
