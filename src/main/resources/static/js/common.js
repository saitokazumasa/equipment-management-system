const accountIconElement = document.getElementById('account-icon');
const accountMenuElement = document.getElementById('account-menu');

function onClickAccountIcon(event) {
    accountMenuElement.hidden = !accountMenuElement.hidden;
}

accountIconElement?.addEventListener('click', onClickAccountIcon);