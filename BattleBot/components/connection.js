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

  return response.json();
}

async function sendGetRequest(url, params = {}) {
  const queryString = new URLSearchParams(params).toString();
  const fullUrl = queryString ? `${url}?${queryString}` : url;

  const response = await fetch(fullUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(errorText || `HTTP Error ${response.status}`);
  }

  return response.json();
}

async function parseResponse(response) {
  const contentType = response.headers.get('content-type');
  if (contentType && contentType.includes('application/json')) {
    return await response.json();
  } else {
    return await response.text();
  }
}

module.exports = { sendRequest, sendGetRequest };