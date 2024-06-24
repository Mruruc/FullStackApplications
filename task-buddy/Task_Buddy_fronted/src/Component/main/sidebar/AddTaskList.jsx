import { useState } from 'react';

import postRequest from '../postRequest.js';

const url = 'http://localhost:8080/api/tasksList';

const AddTaskList = () => {
  const [taskList, setTaskList] = useState('');

  const [isError, setIsError] = useState(null);

  const [isSent, setIsSent] = useState(0);

  const handleTaskList = (e) => {
    setTaskList(e.target.value);
  };

  const saveTaskList = (e) => {
    e.preventDefault();

    postRequest(url, { listName: taskList }, setIsSent, setIsError);

    setTaskList('');
  };

  return (
    <div>
      <form className='form' onSubmit={saveTaskList}>
        <div className='form-control'>
          <label htmlFor='taskList' className='form-label'>
            New Task List
          </label>
        </div>

        <div className='form-control'>
          <input
            type='text'
            value={taskList}
            className='form-input'
            onChange={handleTaskList}
          />
        </div>
        <button type='submit' className='btn'>
          Save
        </button>
      </form>

      <div className='message'>
        {isSent === 200 ? (
          <h2>{taskList} Created &#9989;</h2>
        ) : (
          <div>Hey there</div>
        )}
        {isError && <h2>{isError}</h2>}
      </div>
    </div>
  );
};

export default AddTaskList;
