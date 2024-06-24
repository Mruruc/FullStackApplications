import axios from 'axios';

const postRequest = async (url, data, setIsSent, setIsError) => {
  try {
    const response = await axios.post(url, { ...data });
    console.log(response.status);
    //console.log(response)
    setIsSent(response.status);
    setIsError(null);

  } catch (error) {
    //if request was made and the server responded

    if (error.response && error.response.status !== 201) {
      setIsError(
        `Expected Response Status 201. \n The Actual ${error.response.status}. \n About Header: ${error.response.headers}`
      );
    }

    //the request was made but not response was received
    else if (error.request) {
      setIsError(
        `The Request was made but NO response was received ! \n
             Error Message: ${error.message}`
      );
    } else {
      // Something happened in setting up the request that triggered an Error;
      setIsError(
        ` Something happened in setting up the request that triggered an Error: ${error.message}`
      );
      console.log(error);
    }
  }
};

export default postRequest;
