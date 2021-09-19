document.addEventListener('DOMContentLoaded', ()=> {

    // Create embed in the `embed-example` div. By default the embed will fit its container
    let container = document.getElementById("embed-example");
    console.log(container);
    let embed = new Flat.Embed(container, {
        // The score hosted on Flat we use here as template.
        // You can also use `embed.loadMusicXML(score)` to load your MsuicXML on the fly:
        // https://flat.io/developers/docs/embed/javascript.html#loadmusicxmlscore-mixed-promisevoid-error
        score: "",
        // The embed configuration parameters
        height: "500px",
        embedParams: {
            mode: "edit",
            appId: "59e7684b476cba39490801c2",
            // Customization: https://flat.io/developers/docs/embed/url-parameters.html
            branding: false,
            controlsPosition: "top"
        }
    });

    // showEditor();

    document.getElementById('file-input').addEventListener('change', (e) => {
        uploadFile(e,embed)
    }, false);
    chooseIfCreateOrUpload();
});

function chooseIfCreateOrUpload() {
    let selectedOption = 'crea';

    let alert = document.createElement('div')
    let title = document.createElement('h3')
    title.innerHTML='Scegli cosa fare'

    let content = document.createElement('div')
    let select = document.createElement('select')
    select.classList.add('slct')
    let optionCrea = document.createElement('option')
    optionCrea.value = 'crea'
    optionCrea.innerHTML = 'crea'
    optionCrea.classList.add('opt')
    let optionCarica = document.createElement('option')
    optionCarica.classList.add('opt')
    optionCarica.value = 'carica'
    optionCarica.innerHTML = 'carica'

    select.addEventListener('change', () => {
        selectedOption = select.value;
    })

    let ConfirmButton = document.createElement('button')
    ConfirmButton.classList.add('create-btn')
    ConfirmButton.innerHTML= 'conferma'
    ConfirmButton.addEventListener('click', () => {
        alert.style.display = 'none'
        let create = document.getElementById('create')
        let upload = document.getElementById('upload')
        if (selectedOption === 'carica') {
            create.style.visibility = 'visible'
            upload.style.display= 'none'
        } else if (selectedOption === 'crea') {
            create.style.display = 'none'
            upload.style.display= 'flex'
        }
    })

    alert.classList.add('choose-alert')


    select.appendChild(optionCrea)
    select.appendChild(optionCarica)
    content.appendChild(select)
    alert.appendChild(title)
    alert.appendChild(content)
    alert.appendChild(ConfirmButton)

    let parent = document.getElementById('flat-wrap')
    parent.appendChild(alert)

    return selectedOption;

}

function uploadFile(e, embed) {

    let file = e.target.files[0];
    if (!file) {
        return;
    }

    let reader = new FileReader();
    reader.onload = function(e) {
        let contents = e.target.result;
        // Create embed in the `embed-example` div. By default the embed will fit its container
        let container = document.getElementById("embed-example");
        console.log(container);
        let embed = new Flat.Embed(container, {
            // The score hosted on Flat we use here as template.
            // You can also use `embed.loadMusicXML(score)` to load your MsuicXML on the fly:
            // https://flat.io/developers/docs/embed/javascript.html#loadmusicxmlscore-mixed-promisevoid-error
            score: "",
            // The embed configuration parameters
            height: "500px",
            embedParams: {
                mode: "edit",
                appId: "59e7684b476cba39490801c2",
                // Customization: https://flat.io/developers/docs/embed/url-parameters.html
                branding: false,
                controlsPosition: "top"
            }
        });

        embed.loadMusicXML(contents);
        // embed.loadJSON(contents);
        console.log(contents);

        embed.on('scoreLoaded', () => {
            embed.getJSON().then(async (score) => {
                let formData = new FormData();
                formData.append("score", JSON.stringify(score));
                return await fetch('/musicsheets/analyze', {
                    method: "POST",
                    ContentType: "application/json",
                    body: formData
                }).then((response) => {
                    return response.json()
                }).then((data) => {
                    console.log(data)
                });
            })
        })

        let exportFile = function(buffer, mimeType, ext) {
            var blobUrl = window.URL.createObjectURL(
                new Blob([buffer], {
                    type: mimeType
                })
            );
            var a = document.createElement("a");
            a.href = blobUrl;
            a.download = "exported-score." + ext;
            document.body.appendChild(a);
            a.style = "display: none";
            a.click();
            a.remove();
        };

        document.getElementById("export-xml").addEventListener("click", function() {
            embed.getMusicXML({ compressed: true }).then(function(buffer) {
                exportFile(buffer, "application/vnd.recordare.musicxml+xml", "mxl");
            });
        });

        document.getElementById("export-pdf").addEventListener("click", function() {
            embed.print()
        });

        document.getElementById("export-png").addEventListener("click", function() {
            embed.getJSON().then(function (data) {
                //console.log(JSON.stringify(data));
                console.log(data);
                //Scorro le parti
                let parti = data["score-partwise"].part;
                for (let x = 0; x < parti.length; x++) {
                    let parte = parti[x];
                    let note = parte.measure;
                    for (let i = 0; i < note.length; i++) {
                        //Controllo se la parte contiene una tablatura
                        if(note[i].attributes[0]["staff-details"] != undefined && note[i].attributes[0]["staff-details"]["staff-tuning"] != undefined){
                            note[i].attributes[0]["staff-details"] = 5;
                            delete note[i].attributes[0]["staff-details"]["staff-tuning"];
                            //Cancello le note della tablatura
                            for (let y = 0; y < note[i].note.length; y++) {
                                delete note[i].note[y].notations.technical;
                            }
                        }
                    }
                }

                console.log(data);
                embed.loadJSON(data);
                alert("Guarda attentamente");
                //exportFile(JSON.stringify(data), "application/json", "json");
                //console.log(note[0].note[0].notations);
            })
        });
    };
    reader.readAsText(file);



}