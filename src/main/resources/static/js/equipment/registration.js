const hasLendingPeriodCheckBox = document.getElementById('hasLendingPeriod');
const hasNotificationDateCheckBox = document.getElementById('hasNotificationDate');

const lendingPeriodInput = document.getElementById('lendingPeriod');
const notificationDateInput =  document.getElementById('notificationDate');
const addButton = document.getElementById('addButton');

hasLendingPeriodCheckBox.addEventListener('change', (e) => {
    hasNotificationDateCheckBox.checked = e.target.checked;

    lendingPeriodInput.disabled = !hasNotificationDateCheckBox.checked;
    notificationDateInput.disabled = !hasNotificationDateCheckBox.checked;
});

addButton.addEventListener('click', () => {
    lendingPeriodInput.disabled = false;
    notificationDateInput.disabled = false;
});