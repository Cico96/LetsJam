document.addEventListener("DOMContentLoaded", ()=>{

    document.querySelector('.sort-direction').addEventListener('click', (e)=>{
        console.log(e.target);
        let input = document.querySelector('input[name="sortDirection"]');
        input.value = (input.value == 'ASC') ? 'DESC' : 'ASC';
        document.querySelector('#sidebar-form').submit();
    });

    document.querySelectorAll('#sidebar-form input').forEach(function (el){
        el.addEventListener('change', e => {
            document.querySelector('#sidebar-form').submit();
        });
    });

});