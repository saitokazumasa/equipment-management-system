const menuElement = document.getElementById('menu');
const menuOpenButtonElement = document.getElementById('menu-open-button');
const menuCloseButtonElement = document.getElementById('menu-close-button');

function onMenuOpenButtonClick(event) {
    menuElement.hidden = false;
}

function onMenuCloseButtonClick(event) {
    menuElement.hidden = true;
}

menuOpenButtonElement?.addEventListener('click', onMenuOpenButtonClick);
menuCloseButtonElement?.addEventListener('click', onMenuCloseButtonClick);