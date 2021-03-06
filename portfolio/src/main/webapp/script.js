async function showRandomPhrase() {

    const responseFromServer = await fetch('/phrase');
    const phrases = await responseFromServer.json();

    const phrase = phrases[Math.floor(Math.random() * phrases.length)];


    const phraseContainer = document.getElementById('phraseContainer');
    phraseContainer.innerText = phrase


}
