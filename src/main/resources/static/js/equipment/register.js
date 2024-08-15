const inputElements = [
    document.getElementById('lendingPeriod'),
    document.getElementById('notificationDate')
];

const checkbox = document.getElementById('isSetLendingPeriod')

checkbox.addEventListener('change', () => {
    if (checkbox.checked) {
        inputActivate();
    } else {
        inputDeactivate();
    }
});

inputDeactivate();

function inputDeactivate() {
    inputElements.forEach(element => element.disabled = true);
}

function inputActivate() {
    inputElements.forEach(element => element.disabled = false);
}
