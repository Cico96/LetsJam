document.addEventListener('DOMContentLoaded', () => {

    const person = document.querySelectorAll('.person');
    person.forEach( p => {
        p.addEventListener('click', () => {
            document.querySelector('.chat-container').style.display = 'inline';

        });
    });
});