const headerMenuElement = document.getElementById('header-menu');
const hamburgerMenuElement = document.getElementById('hamburger-menu');
const topHamburgerLineElement = document.getElementById('top-hamburger-line');
const middleHamburgerLineElement = document.getElementById('middle-hamburger-line');
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

    animeOpen(duration) {
        this.element.animate({
                top: this.endRect.top + 'px'
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.animate({
                transform: 'rotate(45deg)'
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }

    animeClose(duration) {
        this.element.animate({
                top: this.startRect.top + 'px'
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.animate({
                transform: 'rotate(0)'
            }, {
                duration: duration,
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

    animeOpen(duration) {
        this.element.animate({
                opacity: 0
            }, {
                duration: duration,
                easing: 'ease-out',
                fill: 'forwards'
            }
        );
    }

    animeClose(duration) {
        this.element.animate({
                opacity: 1
            }, {
                duration: duration,
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

    animeOpen(duration) {
        this.element.animate({
                top: this.endRect.top + 'px'
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.animate({
                transform: 'rotate(-45deg)'
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }

    animeClose(duration) {
        this.element.animate({
                top: this.startRect.top + 'px'
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
        this.element.animate({
                transform: 'rotate(0)'
            }, {
                duration: duration,
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

    animeOpen(duration) {
        this.element.hidden = false;
        this.element.animate({
                opacity: 1
            }, {
                duration: duration,
                easing: 'linear',
                fill: 'forwards'
            }
        );
    }

    animeClose(duration) {
        this.element.animate({
                opacity: 0
            }, {
                duration: duration,
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

    open(duration) {
        this.hamburgerLines.forEach(a => a.animeOpen(duration));
        this.menu.animeOpen(duration);
    }

    close(duration) {
        this.hamburgerLines.forEach(a => a.animeClose(duration));
        this.menu.animeClose(duration);
    }
}

class Container {
    menu;
    hamburgerMenu;

    // NOTE:
    // グローバル変数に依存するのは良くないが、
    // Constructor の引数を多くするとコンテナのメリットが減るため、ここでは許容する
    constructor() {
        this.menu = new Menu(headerMenuElement);

        const topHamburgerLine = new TopHamburgerLine(
            topHamburgerLineElement,
            hamburgerMenuElement,
            /*Margin: */ -12
        );

        const middleHamburgerLine = new MiddleHamburgerLine(
            middleHamburgerLineElement,
            hamburgerMenuElement,
            /*Margin: */ 0
        );

        const bottomHamburgerLine = new BottomHamburgerLine(
            bottomHamburgerLineElement,
            hamburgerMenuElement,
            /*Margin: */ 12
        );

        this.hamburgerMenu = new HamburgerMenu([topHamburgerLine, middleHamburgerLine, bottomHamburgerLine], this.menu);
    }
}

function onClick() {
    if (container.menu.isClose()) container.hamburgerMenu.open(100);
    else container.hamburgerMenu.close(100);
}

function onLoad() {
    if (headerMenuElement === null) return;

    container = new Container(headerMenuElement);
    hamburgerMenuElement.addEventListener('click', onClick);
    window.addEventListener('resize', onResize);
}

function onResize() {
    container.hamburgerMenu.close(100);
    clearTimeout(timeoutID);
    // timeout: 100 は適当
    timeoutID = setTimeout(() => {
        container = new Container(headerMenuElement);
    }, 100);
}

let container;
let timeoutID = 0;

window.addEventListener('load', onLoad);
