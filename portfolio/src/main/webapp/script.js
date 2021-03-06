async function showRandomPhrase() {
  const responseFromServer = await fetch('/phrase');
  const textFromResponse = await responseFromServer.text();

  const phraseContainer = document.getElementById('phraseContainer');
  phraseContainer.innerText = textFromResponse;
}
