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

    chooseIfCreateOrUpload(embed);
});

function chooseIfCreateOrUpload(embed) {
    let selectedOption = 'crea';
    let select = document.getElementById('select')
    let create = document.getElementById('create')
    let instrumentForSheet = document.getElementById('instrumentForSheet')
    let fileForSheet = document.getElementById('fileForSheet')

    select.addEventListener('change', () => {
        selectedOption = select.value;
    })

    let ConfirmButton = document.getElementById('confirmFirst')
    ConfirmButton.addEventListener('click', () => {

        // choose.style.display = 'none'
        if (selectedOption === 'crea') {
            // create.style.display= 'flex'
            let instrumentForSheetStyle = window.getComputedStyle(instrumentForSheet);
            if (instrumentForSheetStyle.display === 'none') {
                instrumentForSheet.style.display = 'flex'
                fileForSheet.style.display = 'none'
            } else {
                instrumentForSheet.style.display = 'none'
            }
        } else if (selectedOption === 'carica') {
            let fileStyle = window.getComputedStyle(fileForSheet);
            if (fileStyle.display === 'none') {
                instrumentForSheet.style.display = 'none'
                fileForSheet.style.display = 'block'
                fileForSheet.addEventListener('change', (e) => {
                        uploadFile(e,embed)
                    }, false);
            }
        }
    })
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
                    document.getElementById('upload').style.display= 'flex'
                    document.getElementById('choose').style.display='none'
                });
            })
        })
    };
    reader.readAsText(file);
}