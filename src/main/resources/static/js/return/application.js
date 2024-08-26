const hasDamagedCheckBox = document.getElementById('hasDamaged');
const damagedReasonInput = document.getElementById('damagedReason');

hasDamagedCheckBox.addEventListener('click', (e) => {
    damagedReasonInput.disabled = !e.target.checked;
});
