document.addEventListener('DOMContentLoaded', ()=> {
    document.getElementById('file-input').addEventListener('change', readSingleFile, false);
});

function readSingleFile(e) {
    console.log('sono nel read single file');
    var file = e.target.files[0];
    if (!file) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function(e) {
        var contents = e.target.result;
        // Create embed in the `embed-example` div. By default the embed will fit its container
        var container = document.getElementById("embed-example");
        console.log(container);
        var embed = new Flat.Embed(container, {
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

        //embed.loadMusicXML(contents);
        embed.loadJSON(contents);
        console.log(contents);

        // Helper function to force downloading the exported file
        var exportFile = function(buffer, mimeType, ext) {
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

        // Export as MusicXML when the button is clicked
        // https://flat.io/developers/docs/embed/javascript.html#getmusicxmloptions-object-promisestringuint8array-error
        document.getElementById("export-xml").addEventListener("click", function() {
        embed.getMusicXML({ compressed: true }).then(function(buffer) {
            exportFile(buffer, "application/vnd.recordare.musicxml+xml", "mxl");
        });
        });

        // Export as MIDI when the button is clicked
        // https://flat.io/developers/docs/embed/javascript.html#getmidi-promiseuint8array-error
        document.getElementById("export-pdf").addEventListener("click", function() {
            embed.print()
        });

        // Export as PNG when the button is clicked
        // https://flat.io/developers/docs/embed/javascript.html#getpngoptions-object-promisestringuint8array-error
        document.getElementById("export-png").addEventListener("click", function() {
            /*embed.getPNG().then(function(buffer) {
                exportFile(buffer, "image/png", "png");
            });*/

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

