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

    let data = JSON.parse(document.querySelector('input#data').value);
    editor.loadJSON(data);
});