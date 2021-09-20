document.addEventListener('DOMContentLoaded', () => {

    // Create embed in the `embed-example` div. By default the embed will fit its container
    let container = document.getElementById("embed-example");
    console.log(container);
    let embed = new Flat.Embed(container, {
        // The score hosted on Flat we use here as template.
        // You can also use `embed.loadMusicXML(score)` to load your MsuicXML on the fly:
        // https://flat.io/developers/docs/embed/javascript.html#loadmusicxmlscore-mixed-promisevoid-error
        score: "",
        // The embed configuration parameters
        height: "800px",
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

        if (selectedOption === 'crea') {
            let instrumentForSheetStyle = window.getComputedStyle(instrumentForSheet);
            if (instrumentForSheetStyle.display === 'none') {
                instrumentForSheet.style.display = 'flex'
                fileForSheet.style.display = 'none'
            } else {
                createSheet(embed)
            }
        } else if (selectedOption === 'carica') {
            let fileStyle = window.getComputedStyle(fileForSheet);
            if (fileStyle.display === 'none') {
                instrumentForSheet.style.display = 'none'
                fileForSheet.style.display = 'block'
            } else {
                let file = fileForSheet.children[0].files[0];
                if (!file) {
                    return;
                }
                uploadFile(embed, file)
            }
        }
    })
    return selectedOption;

}

function uploadFile(embed, file) {

    let reader = new FileReader();
    reader.onload = function (e) {
        let contents = e.target.result;

        embed.loadMusicXML(contents);
        // embed.loadJSON(contents);
        // console.log(contents);

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
                    // console.log(data)
                    document.getElementById('upload').style.display = 'flex'
                    document.getElementById('choose').style.display = 'none'
                    document.getElementById('sheet-author').value = data.author
                    document.getElementById('sheet-title').value = data.title
                });
            })
        })
    };
    reader.readAsText(file);
}

function createSheet(embed) {
    let checkbox = document.querySelectorAll('input[type=checkbox]')
    let selectedIntruments = []
    checkbox.forEach((check) => {
        if (check.checked) {
            selectedIntruments.push(check.id)
        }
    })
    let formData = new FormData();
    formData.append("instruments", JSON.stringify(selectedIntruments));
    return fetch('/musicsheets/getEmptyScore', {
        method: "POST",
        ContentType: "application/json",
        body: formData
    }).then((response) => {
        return response.json()
    }).then((data) => {
        document.getElementById('create').style.display = 'flex'
        document.getElementById('choose').style.display = 'none'
        embed.loadJSON(data);
    });


}