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
            createEmbed(embed);
        });
});

function createEmbed(embed){
    let checkbox = document.querySelectorAll('input[type=checkbox]:checked');
    checkbox.map(instrument => instrument.getAttribute('name'));
    console.log(checkbox);
}
