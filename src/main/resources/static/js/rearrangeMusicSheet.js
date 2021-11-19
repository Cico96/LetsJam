document.addEventListener('DOMContentLoaded', () => {
    let container = document.getElementById("embed-example");
        let embed = new Flat.Embed(container, {
            score: "",
            height: "800px",
            embedParams: {
                mode: "edit",
                appId: "59e7684b476cba39490801c2",
                branding: false,
                controlsPosition: "top"
            }
        });
        embed.loadJSON(musicSheetData.content);
        console.log(musicSheet, musicSheetData);

        document.querySelector('#instrument-btn').addEventListener('click', () => {
            document.querySelector('.instruments-for-sheet').style.display = 'block';
            let divBtn = document.querySelector('div.button.wow.fadeInUp');
            if(divBtn.style.display === 'none'){
                divBtn.style.display = 'block';
            }else{
                divBtn.style.display = 'none';
            }
            document.querySelector('div.button.submit').style.display = 'block';
        });

        document.querySelector('div.button.submit').addEventListener('click', () => {
            addInstrumentsToEmbed(embed);
        });

        document.getElementById('rearrange-submit').addEventListener('click', () => {
            embed.getJSON().then( result => {
                document.getElementById('musicSheetContent').value = JSON.stringify(result);
                document.getElementById('createForm').submit();
            });
        });

        document.querySelectorAll('.visibilityToggle input').forEach(i=>{
                i.addEventListener('change', e=>{
                    if(e.target.checked){
                        console.log(e.target);
                        e.target.parentElement.classList.add("active");
                    }
                    document.querySelector('.visibilityToggle input:not(:checked)')
                        .parentElement.classList.remove("active");
                })
            });
});

function addInstrumentsToEmbed(embed) {
    let checkbox = document.querySelectorAll('input[type=checkbox]:checked');
    checkbox = Array.from(checkbox).map(instrument => instrument.getAttribute('name'));
    let formData = new FormData();
    formData.append("instruments", JSON.stringify(checkbox));
    embed.getJSON().then( result => {
        formData.append("content", JSON.stringify(result));
        return fetch('/musicsheets/addInstrumentsToScore', {
                    method: "POST",
                    ContentType: "application/json",
                    body: formData
                }).then((response) => {
                    return response.json();
                }).then((data) => {
                    embed.loadJSON(data);
                })
    });

}
