const basicUrl = 'http://localhost:8081/api/v1/';

async function sendRegister(discordId, allyCode) {
  const url = `${basicUrl}register`;
  const data = {
    discordId,
    allyCode
  };

  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    });

    if (!response.ok) {
      const errorData = await parseResponse(response);

      const errorMessage = (errorData) ? errorData : `HTTP Error ${response.status}`;

      throw new Error(errorMessage);
    }

    return "Zostałeś zarejestrowany";

  } catch (error) {
    return error.message;
  }
}

async function parseResponse(response) {
  const contentType = response.headers.get('content-type');
  if (contentType && contentType.includes('application/json')) {
    return await response.json();
  } else {
    return await response.text();
  }
}

module.exports = { sendRegister };