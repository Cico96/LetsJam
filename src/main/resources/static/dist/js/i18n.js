//Script per la gestione del cambio di lingua
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
