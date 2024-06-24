export async function requestPostOrPut(url, data, httpMethod) {
  const request = new Request(url, {
    method: httpMethod,
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  

  try {
    const response = await fetch(request);
    if (!response.ok) {
      throw {
        message: response.message,
        statusText: response.statusText,
      };
    }

    const contentLength = response.headers.get('Content-Length');
    if (response.status !== 204 && contentLength !== '0') {
      const result = await response.json();
      return result;
    }
      // Return an empty object or whatever makes sense for your application
      return {};
    

  } catch (error) {
    throw error;
  }
}

// request Method for GET and DELETE requests.

export async function requestGetOrDelete(url, httpMethod) {
  const request = new Request(url, {
    method: httpMethod,
    headers: {
      "Content-Type": "application/json",
    },
  });

  try {
    const response = await fetch(request);
    if (!response.ok) {
      throw {
        message: response.message,
        statusText: response.statusText,
      };
    }
    const result = await response?.json();
    return result;
    
  } catch (error) {
    throw error;
  }
}
