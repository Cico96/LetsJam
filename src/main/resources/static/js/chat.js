document.addEventListener('DOMContentLoaded', () => {

    const person = document.querySelectorAll('.person');
    person.forEach( p => {
        p.addEventListener('click', () => {
            document.querySelector('.chat-container').style.display = 'inline';
        });
    });


    fetch('/getUsersNotYetTalking', {
        method: "POST",
        ContentType: "application/json",
    }).then(response => {
        return response.json();
    });

    //document.getElementById('send-button').addEventListener('click', addMessage);


    fetch('/findConversations', {

               method: "POST",
               ContentType: "application/json",
           }).then(response => {
              return response.json()
           }).then((result) => {
                result.forEach( r=> {
                    const userDiv = document.createElement('div');
                    userDiv.setAttribute("conversationid", r.conversationId);
                    userDiv.classList = "user";
                    const userName = document.createElement('p');
                    const userText = document.createElement('span')
                    userText.classList = "name";
                    userName.classList = "name-time";
                    userName.innerHTML = r.username;
                    userName.style.pointerEvents = "none";
                    userName.appendChild(userText);
                    userDiv.appendChild(userName);
                    document.getElementById('users-container').appendChild(userDiv);
                    userDiv.addEventListener('click', (e) => {
                        showConversation(e);
                    });
                });
           });



    /*const body = new FormData();
    body.append("username", "mattyu1996");
    fetch('/addConversation', {
        method: "POST",
        ContentType: "application/json",
        body: body
    }).then(response => {
        return response.json();
    }).then(console.log);*/

});

function showConversation(e){

    let conversationId = document.getElementById('current-conversation');
    conversationId.setAttribute("conversationid", e.target.getAttribute("conversationid"));
    console.log(conversationId);
    let spanName = document.createElement('span');
    spanName.classList = "name";
    spanName.innerHTML = document.querySelector('p.name-time').innerText;
    document.querySelector('div.selected-user').appendChild(spanName);

    /*const formData = new FormData();
    formData.append("content", content);
    formData.append("conversationId", conversationId);
    fetch('/addMessage', {
       method: "POST",
       ContentType: "application/json",
       body: formData
    }).then(response => {
       return response.json();
    }).then(console.log);*/
}

/*function addMessage(){

   const username = document.getElementById('select').value;
    const content = document.getElementById('text-area').value;

}*/






