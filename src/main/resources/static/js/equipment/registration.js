const hasLendingPeriodCheckBox = document.getElementById('hasLendingPeriod');
const hasNotificationDateCheckBox = document.getElementById('hasNotificationDate');

const lendingPeriodInput = document.getElementById('lendingPeriod');
const notificationDateInput =  document.getElementById('notificationDate');

hasLendingPeriodCheckBox.addEventListener('change', (e) => {
    hasNotificationDateCheckBox.checked = e.target.checked;

    // TODO: ラベルが表示されているため、表示を整える際に一緒に直す。
    lendingPeriodInput.hidden = !hasNotificationDateCheckBox.checked;
    notificationDateInput.hidden = !hasNotificationDateCheckBox.checked;
});