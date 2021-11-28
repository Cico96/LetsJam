document.addEventListener('DOMContentLoaded', () => {

    const person = document.querySelectorAll('.person');
    person.forEach( p => {
        p.addEventListener('click', () => {
            document.querySelector('.chat-container').style.display = 'inline';
        });
    });

    fetch('/findConversations', {
        method: "POST",
        ContentType: "application/json",
    }).then(response => {
       return response.json();
    }).then(console.log);

    fetch('/getUsersNotYetTalking', {
        method: "POST",
        ContentType: "application/json",
    }).then(response => {
        return response.json();
    });


    const formData = new FormData();
    formData.append("content", "prova");
    formData.append("conversationId", "4");
    fetch('/addMessage', {
        method: "POST",
        ContentType: "application/json",
        body: formData
    }).then(response => {
        return response.json();
    });

    const body = new FormData();
    body.append("username", "sfranzi");
    fetch('/addConversation', {
        method: "POST",
        ContentType: "application/json",
        body: body
    }).then(response => {
        return response.json();
    }).then(console.log);
});