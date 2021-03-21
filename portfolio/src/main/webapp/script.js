async function showRandomPhrase() {

    const responseFromServer = await fetch('/phrase');
    const phrases = await responseFromServer.json();

    const phrase = phrases[Math.floor(Math.random() * phrases.length)];


    const phraseContainer = document.getElementById('phraseContainer');
    phraseContainer.innerText = phrase

}

function loadMessages() {
  fetch('/getMessagesList')
    .then(response => response.json())
    .then((messages) => {
        const messagesElement = document.getElementById('messages');
        messages.forEach((message) => {
            messagesElement.appendChild(createMessageElement(message));
        })
    });
}

/** Creates an element that represents a message */
function createMessageElement(message) {
    const msgElement = document.createElement('tr');
    msgElement.className = 'message';

    const nameElement = document.createElement('td');
    nameElement.innerText = message.name;

    const emailElement = document.createElement('td');
    emailElement.innerText = message.email;

    const messageElement = document.createElement('td');
    messageElement.innerText = message.message;

    msgElement.appendChild(nameElement);
    msgElement.appendChild(emailElement);
    msgElement.appendChild(messageElement);

    
    return msgElement;
}
