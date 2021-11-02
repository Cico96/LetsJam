document.addEventListener("DOMContentLoaded", () => {
    let wrapper = document.getElementById('newAdmins')
    let amichetti = document.querySelectorAll('[data-user]')
    amichetti.forEach((amico) => {
        amico.parentNode.parentNode.addEventListener('click', function () {
            let newAdmin = document.createElement('input')
            newAdmin.style.height= '40px'
            newAdmin.style.width= '40px'
            newAdmin.style.color= '#ffffff'
            let div = document.createElement('div')
            div.style.paddingTop = '16px'
            div.style.display = 'flex'
            div.style.alignItems = 'center'
            let label = document.createElement('label')
            label.innerHTML = amico.previousElementSibling.innerHTML;
            label.style.color = '#ffffff'
            label.style.marginLeft = '24px'
            newAdmin.setAttribute('type', 'checkbox')
            newAdmin.setAttribute('value', amico.dataset.user)
            newAdmin.setAttribute('checked', 'true')
            div.appendChild(newAdmin);
            div.appendChild(label);
            wrapper.appendChild(div)
            console.log(newAdmin)
        })
    })

});