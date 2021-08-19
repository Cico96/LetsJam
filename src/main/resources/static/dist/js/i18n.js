/*
Script per la gestione del cambio di lingua
Richiede che nella pagina siano presenti:
1 elemento con classe "lang" che abbia come innerText il valore del locale corrente
almeno 1 elemento con classe "flag-container" che rappresenti la bandiera
*/
window.addEventListener('load', (event) => {
    let locale = document.querySelector('.lang');
    locale = locale.innerText;
    let toReplace = (locale == 'it') ? 'en' : 'it';
    document.querySelector('.flag-container').addEventListener('click', (event) =>{
        let url = window.location.href;
        if(url.search('lang') > 0){
            let newUrl = window.location.href.replace(locale, toReplace);
            location.href = newUrl;
        }
        else{
            let newUrl;
            if(window.location.href.search("\\?")){
                newUrl = window.location.href+"&lang="+toReplace;
            }
            else{
                newUrl = window.location.href+"?lang="+toReplace;
            } 
            location.href = newUrl;
        }
    });
});
