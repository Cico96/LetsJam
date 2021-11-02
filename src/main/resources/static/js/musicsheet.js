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
