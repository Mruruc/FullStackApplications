import React, { useEffect, useState } from 'react';
import axios from 'axios';

const useGetRequest = (url) => {
  
  const [data, setData] = useState([]);
  const [isLoading, setLoading] = useState(true);
  const [isError, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        if(!url){
          setLoading(false);
          setError('URL is Undefined!');
          return;
        }
        const response = await axios.get(url);
        setData(response.data);
        setLoading(false);
        setError(null);
      } catch (error) {
        //if request was made and the server responded
        if (error.response && error.response.status !== 200) {
          setLoading(false);
          setError(`Expected Response Status 200. \n The Received ${error.response.status}.\
          About Header: ${error.response.header}`);
        }

        //the request was made but not response was received
        else if (error.request) {
          setLoading(false);
          setError(`The Request was made but No response was received ! \n 
          Error Message: ${error.message}!`);
        } else {
          // Something happened in setting up the request that triggered an Error
          setLoading(false);
          setError(
            ` Something happened in setting up the request that triggered an Error: ${error.message}`
          );
          console.log(error);
        }
      }
    };

    fetchData();
  }, [url]);

  return {
    data: data,
    isLoading: isLoading,
    isError: isError,
  };
};

export default useGetRequest;
