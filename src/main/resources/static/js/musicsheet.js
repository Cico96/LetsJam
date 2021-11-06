document.addEventListener('DOMContentLoaded', ()=>{
    let container = document.getElementById('embed');
    let editor = new Flat.Embed(container, {
        score: "",
        height: "1000px",
        embedParams: {
            mode: "view",
            appId: "59e7684b476cba39490801c2",
            branding: false,
            controlsDisplay: true,
            controlsPanel: false,
            controlsPosition: "top",
            hideFlatPlayback: false,
            layout: 'page'
        }
    });

    let data = JSON.parse(musicSheetData.content);
    editor.loadJSON(data);

    document.querySelectorAll('.instrument').forEach(i =>{
        i.addEventListener('click', async ()=>{
            let partList = Object.values(musicSheetData.instrumentMapping);
            let disabledInstruments = document.querySelectorAll('.disabled');

            if(i.classList.contains('disabled')) i.classList.remove('disabled');
            else {
                //Check to deny user from disabling all instruments
                if(disabledInstruments.length == partList.length-1) return;
                i.classList.add('disabled');
            }

            let partToRemove = []; 
            document.querySelectorAll('.disabled').forEach(i => partToRemove.push(i.getAttribute('partId')));
            partToRemove.forEach(p =>{
                partList.splice(partList.findIndex(part => part == p), 1);
            });
            
            let formData = new FormData();
            formData.append("partList", JSON.stringify(partList));
            formData.append("musicSheetId", musicSheetData.id);
            return await fetch('/musicsheets/getScoreWithOnlyParts', {
                method: "POST",
                ContentType: "multipart/form-data",
                processData: false,
                body: formData
            }).then((response) => {
                return response.json()
            }).then((data) => {
                editor.loadJSON(data);
            });
        });
    });


    document.getElementById("downloadXml").addEventListener("click", function() {
        editor.getMusicXML().then(function(buffer) {
            exportFile(buffer, "application/xml", "musicxml");
        });
    });

    document.getElementById("downloadPng").addEventListener("click", function() {
        editor.getPNG({
            result: 'dataURL',
            layout: 'page',
            dpi:300    
        }).then(function(buffer) {
            var a = document.createElement("a");
            a.href = buffer;
            a.download = musicSheetTitle+".png";
            a.style = "display: none";
            document.body.appendChild(a);
            a.click();
            a.remove();
        });
    });

    document.getElementById("downloadPdf").addEventListener("click", function() {
        editor.print();
    });

    document.querySelectorAll('.respond-button').forEach(b =>{
        b.addEventListener('click',e =>{
            e.preventDefault();
            console.log(e.target.parentElement.parentElement.parentElement);
            addRespondtextBox(e.target.parentElement.parentElement.parentElement);
        });
    });

    document.querySelectorAll('.write-comment input').forEach(el =>{
        el.addEventListener('keyup', (e)=>{
            if(e.keyCode == 13){
                let lastComment = document.querySelector('.comments-container').children;
                lastComment = lastComment[lastComment.length-2];
                addComment(el, lastComment, false);
            }
        })
    });
});

var exportFile = function(buffer, mimeType, ext) {
    var blobUrl = window.URL.createObjectURL(
        new Blob([buffer], {
        type: mimeType
        })
    );
    var a = document.createElement("a");
    a.href = blobUrl;
    a.download = musicSheetTitle+"." + ext;
    a.style = "display: none";
    document.body.appendChild(a);
    a.click();
    a.remove();
};


function addRespondtextBox(el){
    document.querySelectorAll('.write-comment').forEach(el => el.style.display='none');
    let container = document.createElement('div');
    container.classList.add('write-comment');
    container.style.marginLeft='7%';
    let userImage = document.createElement('div');
    userImage.classList.add('user-image');
    userImage.append("\u00A0");
    let input = document.createElement('input');
    input.setAttribute('type', 'text');
    input.setAttribute('placeholder', 'Scrivi il tuo commento qui');
    input.style.flex='17';
    input.addEventListener('keyup', e =>{
        if(e.keyCode === 13){
            addComment(input, el, true);
        }
    });
    container.appendChild(userImage);
    container.appendChild(input);

    el.parentNode.insertBefore(container, el.nextSibling);
}

function addComment(input, parent, isResponse){

    let comment = document.createElement('div');
    comment.classList = (isResponse) ? 'comment response d-flex align-items-center justify-content-start' : 'comment d-flex align-items-center justify-content-start';
    let userImage = document.createElement('div');
    userImage.classList.add('user-image');
    userImage.append("\u00A0");
    let textContainer = document.createElement('div');
    textContainer.classList='d-flex flex-column align-items-start justify-content-start';
    textContainer.style.flex='17';
    let username = document.createElement('h6');
    username.classList = 'user-name';
    username.innerText= 'Fracchicco Sfranzi';
    let p = document.createElement('p');
    p.classList = 'comment-content';
    p.innerText = input.value;

    let commentActions=document.createElement('div');
    commentActions.classList='d-flex align-items-center justify-content-start comment-actions';
    commentActions.style.gap='10px';

    let respond = document.createElement('a');
    respond.classList='respond-button';
    respond.innerText="Rispondi";
    respond.href='';
    respond.addEventListener('click',e =>{
        e.preventDefault();
        console.log(e.target.parentElement.parentElement.parentElement);
        addRespondtextBox(e.target.parentElement.parentElement.parentElement);
    });

    commentActions.appendChild(respond);

    textContainer.appendChild(username);
    textContainer.appendChild(p);
    textContainer.appendChild(commentActions);

    comment.appendChild(userImage);
    comment.appendChild(textContainer);

    if(isResponse) input.parentElement.remove();
    else input.parentElement.style.display='none';
    input.value='';
    parent.parentNode.insertBefore(comment, parent.nextSibling);
    document.querySelectorAll('.write-comment').forEach(el => el.style.display='flex');
}