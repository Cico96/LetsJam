document.addEventListener("DOMContentLoaded", ()=>{
    const btnsVerify = document.querySelectorAll('[data-verify]')
    btnsVerify.forEach((bt) => {
        const span = bt.querySelector('span')
        span.addEventListener('click', function() {
            const modal = document.querySelector('#confirm-verify-modal');
            modal.style.display = 'flex';
            const inpt = modal.querySelector('input')
            inpt.value = bt.dataset.verify;
            inpt.name = 'musicSeetId';
            console.log(inpt.name)
            console.log(inpt.value)
            // modal.querySelector('form').submit();
        })
    })
})