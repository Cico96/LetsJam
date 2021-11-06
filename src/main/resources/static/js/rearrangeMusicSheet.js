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
            document.querySelector('#instrument-list').style.display = 'block';
        })
});
