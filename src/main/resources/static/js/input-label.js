const labelElements = document.getElementsByTagName('label');

class Label {
    element;

    constructor(element) {
        this.element = element;
    }

    target() {
        const attributes = this.element.attributes;

        for (let i = 0; i < attributes.length; i++) {
            const tag = attributes.item(i);

            if (tag.name !== 'for') continue;

            return tag.value;
        }

        return undefined;
    }

    fadeIn(duration) {
        this.element.animate({
                opacity: 1
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }

    fadeOut(duration) {
        this.element.animate({
                opacity: 0
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }
}

function onLoad() {
    for (let i = 0; i < labelElements.length; i++) {
        const label = new Label(labelElements.item(i));
        const inputElement = document.getElementById(label.target());

        inputElement.addEventListener('change', event => {
            console.log(inputElement.value);
            if (inputElement.value === '' || inputElement.value === null) {
                label.fadeOut(100);
                return;
            }

            label.fadeIn(100);
        });
    }
}

window.onload = onLoad;
