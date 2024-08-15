const inputElements = [
    document.getElementById('equipmentName'),
    document.getElementById('category'),
    document.getElementById('isSetLendingPeriod'),
    document.getElementById('lendingPeriod'),
    document.getElementById('notificationDate'),
    document.getElementById('remark')
];

const editButton = document.getElementById('editButton');
const completeButton = document.getElementById('completeButton');

document.addEventListener('click', (event) => {
    if (event.target === editButton || event.target === completeButton) {
        inputActivate();
    }
});

inputDeactivate();

function inputDeactivate() {
    setInputDisabled(true);
}

function inputActivate() {
    setInputDisabled(false);
}

function setInputDisabled(disabled) {
    inputElements.forEach(element => element.disabled = disabled);
}
