const basicUrl = 'http://localhost:0/api/v1/';

async function sendRequest(url,method,data) {
  const response = await fetch(url, {
    method: method,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });

  if (!response.ok) {
    const errorData = await parseResponse(response);

    const errorMessage = (errorData) ? errorData : `HTTP Error ${response.status}`;

    throw new Error(errorMessage);
  }

  return response.data;
}

async function parseResponse(response) {
  const contentType = response.headers.get('content-type');
  if (contentType && contentType.includes('application/json')) {
    return await response.json();
  } else {
    return await response.text();
  }
}

module.exports = { sendRequest };