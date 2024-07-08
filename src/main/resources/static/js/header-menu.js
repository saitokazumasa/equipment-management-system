const menuElement = document.getElementById('menu');
const hamburgerMenuElement = document.getElementById('hamburger-menu');
const TopHamburgerLineElement = document.getElementById('top-hamburger-line');
const MiddleHamburgerLineElement = document.getElementById('middle-hamburger-line');
const bottomHamburgerLineElement = document.getElementById('bottom-hamburger-line');

class HamburgerLine {
    element;
    startRect = {top: 0, left: 0};
    endRect = {top: 0, left: 0};

    constructor(element, parentElement, topMargin) {
        this.element = element;

        const parentRect = parentElement.getBoundingClientRect();
        const elementRect = element.getBoundingClientRect();

        const centerRect = this.#calcCenterRect(parentRect);
        this.startRect =　this.#calcStartRect(centerRect, elementRect, topMargin);
        this.endRect = this.#calcEndRect(centerRect, elementRect);

        this.element.style.top = this.startRect.top + 'px';
        this.element.style.left = this.startRect.left + 'px';
        this.element.style.transform = 'rotate(0deg)';
        this.element.style.opacity = '1';
    }

    #calcCenterRect(rect) {
        const top = rect.top + rect.height / 2;
        const left = rect.left + rect.width / 2;
        return {top, left};
    }

    #calcStartRect(centerRect, elementRect, topMargin) {
        const top = centerRect.top - elementRect.height / 2 + topMargin;
        const left = centerRect.left - elementRect.width / 2;
        return  {top, left};
    }

    #calcEndRect(centerRect, elementRect) {
        const top = centerRect.top - elementRect.height / 2;
        const left = centerRect.left - elementRect.width / 2;
        return {top, left};
    }

    animeOpen() {
        console.error("Not implemented");
    }

    animeClose() {
        console.error("Not implemented");
    }
}

class TopHamburgerLine extends HamburgerLine {
    constructor(parentElement, element, topMargin) {
        super(parentElement, element, topMargin);
    }

    animeOpen() {
        this.element.animate({
                top: this.endRect.top + 'px'
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.animate({
                transform: 'rotate(45deg)'
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }

    animeClose() {
        this.element.animate({
                top: this.startRect.top + 'px'
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.animate({
                transform: 'rotate(0)'
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }
}

class MiddleHamburgerLine extends HamburgerLine {
    constructor(parentElement, element, topMargin) {
        super(parentElement, element, topMargin);
    }

    animeOpen() {
        this.element.animate({
                opacity: 0
            }, {
                duration: 100,
                easing: 'ease-out',
                fill: 'forwards'
            }
        );
    }

    animeClose() {
        this.element.animate({
                opacity: 1
            }, {
                duration: 100,
                easing: 'ease-in',
                fill: 'forwards'
            }
        );
    }
}

class BottomHamburgerLine extends HamburgerLine {
    constructor(parentElement, element, topMargin) {
        super(parentElement, element, topMargin);
    }

    animeOpen() {
        this.element.animate({
                top: this.endRect.top + 'px'
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.animate({
                transform: 'rotate(-45deg)'
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }

    animeClose() {
        this.element.animate({
                top: this.startRect.top + 'px'
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.animate({
                transform: 'rotate(0)'
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }
}

class Menu {
    element;

    constructor(element) {
        this.element = element;
        this.element.style.opacity = '0';
        this.element.hidden = true;
    }

    animeOpen() {
        this.element.hidden = false;
        this.element.animate({
                opacity: 1
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );

    }

    animeClose() {
        this.element.animate({
                opacity: 0
            }, {
                duration: 100,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.hidden = true;
    }

    isClose() {
        return this.element.hidden;
    }
}

class HamburgerMenu {
    hamburgerLines;
    menu;

    constructor(hamburgerLines, menu) {
        this.hamburgerLines = hamburgerLines;
        this.menu = menu;
    }

    static generate(menu) {
        return new HamburgerMenu(
            [
                new TopHamburgerLine(TopHamburgerLineElement, hamburgerMenuElement, -12),
                new MiddleHamburgerLine(MiddleHamburgerLineElement, hamburgerMenuElement, 0),
                new BottomHamburgerLine(bottomHamburgerLineElement, hamburgerMenuElement, 12)
            ],
            menu
        );
    }

    open() {
        this.hamburgerLines.forEach(a => a.animeOpen());
        this.menu.animeOpen();
    }

    close() {
        this.hamburgerLines.forEach(a => a.animeClose());
        this.menu.animeClose();
    }
}

function onClick() {
    if (menu.isClose()) hamburgerMenu.open();
    else hamburgerMenu.close();
}

function onResize() {
    if (hamburgerMenuElement === null) return;

    hamburgerMenu.close();
    clearTimeout(timeoutID);
    timeoutID = setTimeout(() => {
        hamburgerMenu = HamburgerMenu.generate(menu);
    }, 100);
}

const menu = new Menu(menuElement);
let hamburgerMenu = HamburgerMenu.generate(menu);
let timeoutID = 0;

window.onresize = () => onResize();
hamburgerMenuElement?.addEventListener('click', onClick);
